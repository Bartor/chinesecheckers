package backend.interpreter;

import backend.GameSingleton;
import backend.socketing.MessageQueueSingleton;
import backend.socketing.Server;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import frontend.controllers.Game;
import model.exceptions.CannotAddPlayerException;
import model.exceptions.MoveNotAllowedException;
import model.exceptions.NoSuchPieceException;
import model.exceptions.NoSuchPlayerException;
import model.player.Piece;
import model.player.PiecePosition;
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
                JsonArray arr = new JsonParser().parse(message).getAsJsonObject().get("content").getAsJsonArray();
                int from = new JsonParser().parse(message).getAsJsonObject().get("from").getAsInt();

                int[] moveOld = new int[2];
                int[] moveNew = new int[2];

                moveOld[0] = arr.get(0).getAsJsonArray().get(0).getAsInt();
                moveOld[1] = arr.get(0).getAsJsonArray().get(1).getAsInt();

                moveNew[0] = arr.get(1).getAsJsonArray().get(0).getAsInt();
                moveNew[1] = arr.get(1).getAsJsonArray().get(1).getAsInt();

                try {
                    Piece piece;
                    try {
                        System.out.println("Trying to move from " + moveOld[0] + ", " + moveOld[1] + " by player " + from);
                        piece = GameSingleton.getGame().getPlayerById(from).getArmy().getPieceByPosition(new PiecePosition(moveOld[0], moveOld[1]));
                    } catch (NoSuchPieceException e) {
                        e.printStackTrace();
                        return;
                    }
                    GameSingleton.getGame().getBoardMovementInterface().makeMove(piece, new PiecePosition(moveNew[0], moveNew[1]));

                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("type", "make-move");
                    jsonObject.add("content", new JsonParser().parse(message).getAsJsonObject().get("content").getAsJsonArray());
                    jsonObject.addProperty("to", "all");

                    MessageQueueSingleton.getMessages().add(jsonObject.toString());
                } catch (NoSuchPlayerException e) {
                    e.printStackTrace();
                } catch (MoveNotAllowedException e) {
                    //todo add WRONG MOVE here
                    e.printStackTrace();
                }


                //TODO ADD NEXT TURN HERE
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("type", "next-turn");
                jsonObject.addProperty("content", 234234);

                break;
            }
            case "ready": {
                int from = new JsonParser().parse(message).getAsJsonObject().get("from").getAsInt();

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("type", "ready");
                jsonObject.addProperty("content", from);
                jsonObject.addProperty("to", "all");

                MessageQueueSingleton.getMessages().add(jsonObject.toString());
                if (GameSingleton.readyPlayer(from)) {
                    JsonObject start = new JsonObject();
                    start.addProperty("type", "start-game");
                    start.addProperty("content", "");
                    start.addProperty("to", "all");

                    GameSingleton.getGame().setTurn(1);

                    JsonObject turn = new JsonObject();
                    turn.addProperty("type", "next-turn");
                    turn.addProperty("content", 1);
                    turn.addProperty("to", "all");

                    MessageQueueSingleton.getMessages().add(start.toString());
                    MessageQueueSingleton.getMessages().add(turn.toString());
                }
                break;
            }
        }
    }
}
