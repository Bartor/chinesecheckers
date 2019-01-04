package backend.socketing;

import backend.interpreter.MessageInterpreter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/***
 * A thread for a single client.
 */
public class ServerClient extends Thread {
    private Socket socket;
    private int id;

    private List<String> sent = new ArrayList<>();
    private List<String> toSend = new ArrayList<>();

    public ServerClient(Socket socket, int id) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Client connected");

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
                System.out.println("Reading" + message);
                MessageInterpreter.interpret(message);
                for (int i = sent.size() + toSend.size(); i < MessageQueueSingleton.getMessages().size(); i++) {
                    toSend.add(MessageQueueSingleton.getMessages().get(i));
                }
                if (toSend.size() == 0) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("type", "no-changes");
                    jsonObject.addProperty("content", "");
                    jsonObject.addProperty("to", "");

                    JsonArray a = new JsonArray();
                    a.add(jsonObject.toString());
                    System.out.println("NO CHANGES");
                    pr.println(a.toString());
                } else {
                    String to = new JsonParser().parse(toSend.get(0)).getAsJsonObject().get("to").getAsString();

                    while (!to.equals("all") && !to.equals(String.valueOf(id))) {
                        sent.add(toSend.remove(0));
                        to = new JsonParser().parse(toSend.get(0)).getAsJsonObject().get("to").getAsString();
                    }

                    JsonArray arr = new JsonArray();
                    for (String s : toSend) arr.add(s);

                    System.out.println("Sending " + arr.toString());
                    pr.println(arr.toString());
                    sent.addAll(toSend);
                    toSend.clear();
                }
                pr.flush();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
