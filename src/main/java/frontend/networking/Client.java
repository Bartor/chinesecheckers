package frontend.networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private int port;
    private String host;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        try {
            Socket socket = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
