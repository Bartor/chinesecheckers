import backend.socketing.ServerClient;
import org.junit.Test;

import java.net.Socket;

public class ServCliTest {

    Socket socket;

    @Test
    public void servClTest(){
        socket = new Socket();
        ServerClient serverClient = new ServerClient(socket, 2);
        serverClient.run();
    }
}
