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
        this.refreshTime = refreshTime;
    }

    public void connect() throws IOException {
        try {
            Socket socket = new Socket(host, port);

            new ClientThread(socket, refreshTime).run();
        } catch (IOException e) {
            throw e;
        }
    }
}