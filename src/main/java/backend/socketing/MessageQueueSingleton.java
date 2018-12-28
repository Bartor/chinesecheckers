package backend.socketing;

import java.util.ArrayList;
import java.util.List;

public class MessageQueueSingleton {
    private static List<String> instance;
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
