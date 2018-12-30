package backend.socketing;

import backend.interpreter.MessageInterpreter;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/***
 * A thread for a single client.
 */
public class ServerClient implements Runnable {
    private Socket socket;
    private List<String> sent = new ArrayList<>();
    private List<String> toSend = new ArrayList<>();

    public ServerClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br;
        PrintWriter pr;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pr = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String message;
        while (true) {
            try {
                message = br.readLine(); //when first started, waits for client to send his nickname
                MessageInterpreter.interpret(message);
                for (String msg : MessageQueueSingleton.getMessages()) {
                    if (!this.sent.contains(msg)) {
                        toSend.add(msg);
                    }
                }
                if (toSend.size() == 0) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("type", "no-changes");
                    jsonObject.addProperty("content", "");
                    jsonObject.addProperty("to", "");
                    pr.println(jsonObject.toString());
                } else {
                    pr.println(toSend.get(0));
                    sent.add(toSend.get(0));
                    toSend.remove(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
