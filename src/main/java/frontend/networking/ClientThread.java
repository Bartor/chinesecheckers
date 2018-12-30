package frontend.networking;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/***
 * Thread connecting to server. Receives info and passes it to the MessageInterpreter, which handles it.
 * Reads upcoming client-side data from MessagesInterpreter's messageQueue and sends it to server.
 */
public class ClientThread implements Runnable {
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pr;

    private int refreshTime;

    public ClientThread(Socket socket, int refreshTime) {
        this.refreshTime = refreshTime;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pr = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line;
        while (true) {
            //we send it first
            if (MessageInterpreter.getMessageQueue().size() != 0) {
                pr.println(MessageInterpreter.getMessageQueue().get(0));
                MessageInterpreter.getMessageQueue().remove(0);
            } else {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("type", "fetch");
                jsonObject.addProperty("content", "");
                pr.println(jsonObject.toString());
            }

            try {
                line = br.readLine();
                MessageInterpreter.interpret(line);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                //configurable thread refresh time
                Thread.sleep(refreshTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
