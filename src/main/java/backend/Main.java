package backend;

import backend.socketing.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(1234);
        server.start();
    }
}
