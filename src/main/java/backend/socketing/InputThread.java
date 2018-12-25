package backend.socketing;

import backend.socketing.observers.StreamObserverInterface;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputThread extends Thread {
    private List<StreamObserverInterface> observers = new ArrayList<>();
    private InputStream in;

    public InputThread(InputStream in) {
        this.in = in;
    }

    public void addObserver(StreamObserverInterface observer) {
        this.observers.add(observer);
    }

    private void notify(String message) {
        for (StreamObserverInterface observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while (true) {
            try {
                line = br.readLine();
                notify(line);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
