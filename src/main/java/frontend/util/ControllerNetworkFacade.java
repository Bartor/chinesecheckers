package frontend.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import frontend.controllers.AbstractController;
import frontend.networking.Client;
import frontend.networking.MessageInterpreter;
import model.player.Piece;
import model.player.PiecePosition;

import java.io.IOException;

/***
 * Makes communication between controllers and network a bit easier.
 * MessageInterpreter has a public static method interpret, so that's why we don't really need to take it as an arg.
 */
public class ControllerNetworkFacade {
    /***
     * Tells server that we are ready!
     */
    public static void ready() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "ready");
        jsonObject.addProperty("content", "");
        jsonObject.addProperty("from", AbstractController.getThisPlayer().getId()); //todo put id there somehow

        MessageInterpreter.getMessageQueue().add(jsonObject.toString());
    }

    /***
     * Tells server that we moved.
     * @param oldP Position that we moved from, [x, y].
     * @param newP Position that we moved to, [x, y].
     */
    public static void moved(PiecePosition oldP, PiecePosition newP) {
        int[] oldPos = new int[2];
        int[] newPos = new int[2];

        oldPos[0] = oldP.getRow();
        oldPos[1] = oldP.getCol();
        newPos[0] = newP.getRow();
        newPos[1] = newP.getCol();

        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        JsonArray oldp = new JsonArray();
        oldp.add(oldPos[0]);
        oldp.add(oldPos[1]);

        JsonArray newp = new JsonArray();
        newp.add(newPos[0]);
        newp.add(newPos[1]);

        jsonArray.add(oldp);
        jsonArray.add(newp);

        jsonObject.addProperty("type", "move");
        jsonObject.add("content", jsonArray);
        jsonObject.addProperty("from", AbstractController.getThisPlayer().getId());

        MessageInterpreter.getMessageQueue().add(jsonObject.toString());
    }

    public static void connect(String addr, String nickname) throws Exception {
        if (addr.split(":").length == 0) throw new Exception("That's pretty empty");
        String host = addr.split(":")[0];
        int port = addr.split(":").length == 1 ? 2137 : -1;
        if (port == -1) {
            try {
                port = Integer.parseInt(addr.split(":")[1]);
            } catch (NumberFormatException e) {
                throw new Exception("Wrong port");
            }
        }
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("type", "new-client");
            jsonObject.addProperty("content", nickname);

            MessageInterpreter.getMessageQueue().add(jsonObject.toString());

            System.out.println("Starting client thread");
            new Client(host, port, 1000).connect();
        } catch (IOException e) {
            throw e;
        }
    }
}
