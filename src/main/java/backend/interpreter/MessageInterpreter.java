package backend.interpreter;

import backend.GameSingleton;
import backend.socketing.MessageQueueSingleton;
import backend.socketing.Server;
import com.google.gson.JsonParser;
import model.exceptions.CannotAddPlayerException;
import model.player.Player;

public class MessageInterpreter {
    private static Server server;

    public MessageInterpreter(Server server) {
        MessageInterpreter.server = server;
    }

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
                String content = new JsonParser().parse(message).getAsJsonObject().get("content").getAsString();
                Player player = new Player(content);
                try {
                    GameSingleton.getGame().addPlayer(player);
                } catch (CannotAddPlayerException e) {
                    e.printStackTrace();
                }
                GameSingleton.getGame().createArmy(player);

                MessageQueueSingleton.getMessages().add(message);
                break;
            }
            case "move": {
                //content - [[int, int], [int, int]] ([oldPosition, newPosition])
                break;
            }
        }
    }
}
