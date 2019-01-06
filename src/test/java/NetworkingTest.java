
import backend.GameSingleton;
import backend.socketing.MessageQueueSingleton;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import frontend.networking.Client;
import frontend.networking.MessageInterpreter;
import frontend.util.ControllerNetworkFacade;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class NetworkingTest {

    @Test
    public void testAddingPlayers()  {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "new-client");
        jsonObject.addProperty("content", "exampleNick");

        MessageInterpreter.getMessageQueue().add(jsonObject.toString());

        System.out.println("Starting client thread");
        try {
            new Client("localhost", 2137, 50).connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){}
        //Assert.assertEquals(1, GameSingleton.getGame().getPlayers().size());
    }
}
