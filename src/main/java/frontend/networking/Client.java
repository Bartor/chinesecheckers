package frontend.networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/***
 * This class doesn't have to exist really but I want an additional layer of separation from thread.
 */
public class Client {
    private int port;
    private String host;
    private int refreshTime;

    public Client(String host, int port, int refreshTime) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        try {
            Socket socket = new Socket(host, port);
            new ClientThread(socket, refreshTime).run();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}