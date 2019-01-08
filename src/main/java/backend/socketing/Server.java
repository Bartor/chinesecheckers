package backend.socketing;

import backend.GameSingleton;
import backend.interpreter.MessageInterpreter;
import com.google.gson.JsonObject;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/***
 * Holds clients.
 */
public class Server {
    /***
     * Port we connect at.
     */
    private int port;

    /***
     * Creates a new server on given port.
     * @param port Port to create the server at.
     */
    public Server (int port) {
        this.port = port;
    }

    /***
     * Start a server, each client is an instance of {@link ServerClient} in separate thread.
     */
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
            new ServerClient(socket, GameSingleton.getGame().getNextId()).start();
        }
    }
}
