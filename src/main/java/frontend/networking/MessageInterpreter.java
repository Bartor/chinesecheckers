package frontend.networking;
import com.google.gson.JsonParser;
import frontend.controllers.AbstractController;
import frontend.util.NetworkControllerFacade;

import java.util.ArrayList;
import java.util.List;

public class MessageInterpreter {
    private static List<String> messageQueue = new ArrayList<>();
    private static NetworkControllerFacade controllerFacade;

    public static void interpret(String message) {
        String type = new JsonParser().parse(message).getAsJsonObject().get("type").getAsString();
        //todo interpret this
    }

    public static void setController(AbstractController controller) {
        MessageInterpreter.controllerFacade = new NetworkControllerFacade(controller);
    }

    public static List<String> getMessageQueue() {
        return messageQueue;
    }
}