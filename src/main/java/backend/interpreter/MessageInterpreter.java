package backend.interpreter;

import backend.GameSingleton;
import backend.socketing.MessageQueueSingleton;
import backend.socketing.Server;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import frontend.controllers.Game;
import model.exceptions.CannotAddPlayerException;
import model.player.Player;

import java.util.ArrayList;
import java.util.List;

/***
 * Modifies state of game in the GameSingleton and adds proper messages to message Queue.
 */
public class MessageInterpreter {
    public static void interpret(String message) {
        String type = new JsonParser().parse(message).getAsJsonObject().get("type").getAsString();

        /*
         * message is a JSON
         * {
         *      "type": "new-client" | "move",
         *      "content": ... ,
         *      "to": "all" | id
         * }
         */

        switch (type) {
            case "new-client": {
                //content - String: nickname
                String nick = new JsonParser().parse(message).getAsJsonObject().get("content").getAsString();
                Player player = new Player(nick);
                int id = -1;
                try {
                    id = GameSingleton.getGame().addPlayer(player);
                } catch (CannotAddPlayerException e) {
                    e.printStackTrace();
                }
                GameSingleton.getGame().createArmy(player);

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("type", "new-client");

                JsonArray arr = new JsonArray();
                arr.add(nick);
                arr.add(id);

                jsonObject.add("content", arr);
                jsonObject.addProperty("to", "all");

                MessageQueueSingleton.getMessages().add(jsonObject.toString());
                break;
            }
            case "move": {
                //content - [[int, int], [int, int]] ([oldPosition, newPosition])
                break;
            }
            case "ready": {
                String from = new JsonParser().parse(message).getAsJsonObject().get("from").getAsString();

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("type", "ready");
                jsonObject.addProperty("content", from);
                jsonObject.addProperty("to", "all");

                MessageQueueSingleton.getMessages().add(jsonObject.toString());
                if (GameSingleton.readyPlayer()) {
                    JsonObject start = new JsonObject();
                    start.addProperty("type", "start-game");
                    start.addProperty("content", "");
                    start.addProperty("to", "all");

                    MessageQueueSingleton.getMessages().add(start.toString());
                }
                break;
            }
        }
    }
}
