package frontend.util;

import frontend.controllers.AbstractController;
import frontend.controllers.Game;

/***
 * I have to use some patterns, so that's a pattern to make communication between network and controllers a bit simpler.
 */
public class NetworkControllerFacade {
    private AbstractController controller;

    /***
     * Starts up this facade with a pre-game controller.
     * @param controller Pre-game controller.
     */
    public NetworkControllerFacade(AbstractController controller) {
        this.controller = controller;
    }

    /***
     * Make a move on the board.
     * @param oldPos Starting position of a piece, [x, y].
     * @param newPos Ending position of a piece, [x, y].
     */
    public void makeMove(int[] oldPos, int[] newPos) {

    }

    /***
     * Switches a turn.
     * @param id Id of a player that turn we switch to.
     */
    public void nextTurn(int id) {

    }

    /***
     * Pre-game method. Adds a player to the game.
     * @param nick The nickname of a player.
     */
    public void addPlayer(String nick, int id) {

    }

    /***
     * Method used to switch between pre-game and game.
     * @param gameController A new controller to be used in game state.
     */
    public void startGame(AbstractController gameController) { //pass new game controller here
        this.controller = gameController;
    }

    /***
     * Special case of a nextTurn() which tells the controller that it's your turn now.
     */
    public void yourTurn() {

    }
}
