package bot.networking;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/***
 * Thread connecting to server. Receives info and passes it to the MessageInterpreter, which handles it.
 * Reads upcoming client-side data from MessagesInterpreter's messageQueue and sends it to server.
 */
public class ClientThread extends Thread {
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
        System.out.println("Client thread running");

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
                System.out.println("Sending " + MessageInterpreter.getMessageQueue().get(0));
                pr.println(MessageInterpreter.getMessageQueue().get(0));
                MessageInterpreter.getMessageQueue().remove(0);
            } else {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("type", "fetch");
                jsonObject.addProperty("content", "");
                System.out.println("Sending " + jsonObject.toString());
                pr.println(jsonObject.toString());
            }

            pr.flush();

            try {
                line = br.readLine();
                System.out.println("Received " + line);
                MessageInterpreter.interpret(line);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                System.out.println("Sleeping...");
                //configurable thread refresh time
                Thread.sleep(refreshTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
