package frontend.networking;

import java.io.IOException;
import java.net.Socket;

/***
 * This class doesn't have to exist really but I want an additional layer of separation from thread.
 */
public class Client {
    /***
     * Port we connect through.
     */
    private int port;
    /***
     * Hostname.
     */
    private String host;
    /***
     * Client fetches information from server every {@link Client#refreshTime} ms.
     */
    private int refreshTime;

    /***
     * Creates a new client.
     * @param host The host.
     * @param port The port.
     * @param refreshTime The refreshtime.
     */
    public Client(String host, int port, int refreshTime) {
        this.host = host;
        this.port = port;
        this.refreshTime = refreshTime;
    }

    /***
     * Connects the client to the server.
     * @throws IOException When server couldn't be found.
     */
    public void connect() throws IOException {
        try {
            Socket socket = new Socket(host, port);

            new ClientThread(socket, refreshTime).start();
        } catch (IOException e) {
            throw e;
        }
    }
}