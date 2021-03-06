package backend.socketing;

import java.util.ArrayList;
import java.util.List;

/***
 * Message queue - provides quasi-asynchronous behaviour.
 * Tasks are added to the MessageQueue by the {@link backend.interpreter.MessageInterpreter} and then threads read it and send the messages to clients.
 */
public class MessageQueueSingleton {
    /***
     * Instance of the queue.
     */
    private static List<String> instance;

    /***
     * Gets synchronized queue.
     * @return Message queue.
     */
    public static List<String> getMessages() {
        if (instance == null) {
            synchronized (MessageQueueSingleton.class) {
                if (instance == null) {
                    instance = new ArrayList<>();
                }
            }
        }
        return instance;
    }
}
