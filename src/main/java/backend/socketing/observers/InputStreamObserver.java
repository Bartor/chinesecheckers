package backend.socketing.observers;

import frontend.controllers.Game;

public class InputStreamObserver implements StreamObserverInterface {
    private Game game;

    public InputStreamObserver (Game game) {
        //he has to update the game accordingly
        this.game = game;
    }

    public void update(String message) { //these are json
        System.out.println(message);
        //todo implement what it does
    }
}
