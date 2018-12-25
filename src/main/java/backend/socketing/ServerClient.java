package backend.socketing;

import java.io.*;
import java.net.Socket;


/***
 * Represents a single client. Has a separate thread for all the input (client may want to send several inputs
 * and not read anything).
 */
public class ServerClient {
    private Socket socket;
    private InputStream in;
    private OutputStream out;

    public ServerClient(Socket socket) {
        this.socket = socket;
    }

    public void writeToOutput(String message) {
        try {
            PrintWriter pr = new PrintWriter(out);
            pr.println(message);
            pr.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void initialize() {
        System.out.println("Client thread starting...");

        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        InputThread input = new InputThread(in);
        input.start();
        //writeToOutput("siema");
    }
}
