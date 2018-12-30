package frontend.util;

import frontend.controllers.AbstractController;
import frontend.controllers.Game;

/***
 * I have to use some patterns, so that's a pattern to make communication between network and controllers a bit simpler.
 */
public class NetworkControllerFacade {
    private AbstractController controller;

    public NetworkControllerFacade(AbstractController controller) {
        this.controller = controller;
    }

    public void makeMove(int[] oldPos, int[] newPos) {

    }

    public void nextTurn(int id) {

    }

    public void addPlayer(String nick) {

    }

    public void startGame(AbstractController gameController) { //pass new game controller here
        this.controller = gameController;
    }

    public void wonGame(int id) {

    }

    public void youtTurn() {

    }
}
