package backend.socketing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/***
 * Holds clients.
 */
public class Server {
    private int port;
    private List<ServerClient> clients = new ArrayList<>();

    public Server (int port) {
        this.port = port;
    }

    public void start() {
        Socket socket;
        ServerSocket server;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (true) {
            try {
                socket = server.accept();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            ServerClient temp = new ServerClient(socket);
            temp.initialize();
            clients.add(temp);
        }
    }
}
