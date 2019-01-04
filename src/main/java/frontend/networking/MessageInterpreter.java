package frontend.networking;
import backend.GameSingleton;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import frontend.controllers.AbstractController;
import frontend.util.NetworkControllerFacade;
import model.exceptions.NoSuchPlayerException;

import java.util.ArrayList;
import java.util.List;

/***
 * Client-side interpreter; different from the server-side one.
 * Holds {@link MessageInterpreter#messageQueue} and interprets incoming messages from server.
 * Messages from client are added by the {@link frontend.util.ControllerNetworkFacade} AND DELETED by the {@link ClientThread}
 * when being send.
 */
public class MessageInterpreter {
    private static List<String> messageQueue = new ArrayList<>();
    private static NetworkControllerFacade controllerFacade;

    public static void interpret(String message) {

        String type = new JsonParser().parse(message).getAsJsonObject().get("type").getAsString();

        System.out.println("INTERPRETING " + type);

        switch (type) {
            case "load-map": {
                JsonArray arr = new JsonParser().parse(message).getAsJsonObject().get("content").getAsJsonArray();
                String[][] boardArray = new String[arr.size()][];
                int i = 0;
                for (JsonElement a : arr) {
                    boardArray[i] = new String[a.getAsJsonArray().size()];
                    int j = 0;
                    for (JsonElement e : a.getAsJsonArray()) {
                        boardArray[i][j] = e.getAsString();
                        j++;
                    }
                    i++;
                }
                controllerFacade.loadMap(boardArray);
                break;
            }
            case "new-client": {
                String nick = new JsonParser().parse(message).getAsJsonObject().get("content").getAsJsonArray().get(0).getAsString();
                int id = new JsonParser().parse(message).getAsJsonObject().get("content").getAsJsonArray().get(1).getAsInt();

                controllerFacade.addPlayer(nick, id);
                break;
            }
            case "ready": {
                int id = new JsonParser().parse(message).getAsJsonObject().get("content").getAsInt();

                controllerFacade.ready(id);
                break;
            }
            case "make-move": //bad idea?
            case "wrong-move": {
                if (type.equals("wrong-move")) {
                    controllerFacade.alert("Your move was illegal; rolling back");
                }

                JsonArray arr = new JsonParser().parse(message).getAsJsonObject().get("content").getAsJsonArray();
                int[] rollbackOld = new int[2];
                int[] rollbackNew = new int[2];

                rollbackOld[0] = arr.get(0).getAsJsonArray().get(0).getAsInt();
                rollbackOld[1] = arr.get(0).getAsJsonArray().get(1).getAsInt();

                rollbackNew[0] = arr.get(1).getAsJsonArray().get(0).getAsInt();
                rollbackNew[1] = arr.get(1).getAsJsonArray().get(1).getAsInt();

                controllerFacade.makeMove(rollbackOld, rollbackNew);
                break;
            }
            case "next-turn": {
                int id = new JsonParser().parse(message).getAsJsonObject().get("content").getAsInt();

                controllerFacade.nextTurn(id);
                break;
            }
            case "ok":
            case "no-changes": {
                //well that's ok
                break;
            }
            case "start-game": {
                controllerFacade.startGame();
                break;
            }
            case "bad-request": {
                controllerFacade.alert("Bad Request");
                break;
            }
            case "won": {
                try {
                    String winner = GameSingleton.getGame().getPlayerById(new JsonParser().parse(message).getAsJsonObject().get("content").getAsInt()).getName();
                    controllerFacade.alert("Player " + winner + " has won!");
                } catch (NoSuchPlayerException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "end": {
                controllerFacade.alert("Game finished");
                break;
            }
            default: {
                System.out.println("Malformed server response: " + type);
                break;
            }
        }
    }

    public static void spawnFacade(AbstractController controller) {
        MessageInterpreter.controllerFacade = new NetworkControllerFacade(controller);
    }

    public static List<String> getMessageQueue() {
        return messageQueue;
    }
}