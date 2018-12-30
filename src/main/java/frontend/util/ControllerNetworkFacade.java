package frontend.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import frontend.networking.Client;
import frontend.networking.MessageInterpreter;

import java.io.IOException;

/***
 * Makes communication between controllers and network a bit easier.
 * MessageInterpreter has a public static method interpret, so that's why we don't really need to take it as an arg.
 */
public class ControllerNetworkFacade {
    /***
     * Tells server that we moved.
     * @param oldPos Position that we moved from, [x, y].
     * @param newPos Position that we moved to, [x, y].
     */
    public void moved(int[] oldPos, int[] newPos) {
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
        jsonObject.addProperty("content", jsonArray.toString());

        MessageInterpreter.getMessageQueue().add(jsonObject.toString());
    }

    public void connect(String addr, String nickname) throws Exception {
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

            new Client(addr, port, 100).connect();
        } catch (IOException e) {
            throw e;
        }
    }
}
