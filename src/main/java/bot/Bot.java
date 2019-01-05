package bot;

import bot.networking.Client;

import java.io.IOException;

public class Bot {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: [host] [port]");
            return;
        }

        String host = args[0];
        int port;

        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Wrong port!");
            return;
        }

        try {
            Client client = new Client(host, port, 50);
            client.connect();
        } catch (IOException e) {
            System.out.println("Couldn't connect to the server");
        }
    }
}
