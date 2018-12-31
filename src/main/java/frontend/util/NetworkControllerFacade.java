package frontend.util;

import frontend.controllers.AbstractController;
import frontend.controllers.Game;
import frontend.controllers.Pregame;
import model.exceptions.CannotAddPlayerException;
import model.exceptions.CorruptedFileException;
import model.exceptions.MoveNotAllowedException;
import model.exceptions.NoSuchPlayerException;
import model.player.Piece;
import model.player.PiecePosition;
import model.player.Player;

/***
 * I have to use some patterns, so that's a pattern to make communication between network and controllers a bit simpler.
 */
public class NetworkControllerFacade {
    private AbstractController controller;

    /***
     * Starts up this facade with a controller.
     * @param controller Controller.
     */
    public NetworkControllerFacade(AbstractController controller) {
        this.controller = controller;
    }

    public void loadMap(String[][] map) {
        try {
            AbstractController.getGame().getBoardMovementInterface().getBoard().loadBoard(map);
        } catch (CorruptedFileException e) {
            controller.showAlert(e.getMessage());
            e.printStackTrace();
        }
    }

    /***
     * Make a move on the board.
     * @param oldPos Starting position of a piece, [x, y].
     * @param newPos Ending position of a piece, [x, y].
     */
    public void makeMove(int[] oldPos, int[] newPos) {
        if (controller.getClass() == Game.class) {
            Piece piece;
            try {
                piece = AbstractController.getGame().getPlayerById(AbstractController.getGame().getTurn()).getArmy().getPieceByPosition(new PiecePosition(oldPos[0], oldPos[1]));
            } catch (NoSuchPlayerException e) {
                controller.showAlert(e.getMessage());
                return;
            }
            try {
                AbstractController.getGame().getBoardMovementInterface().makeMove(piece, new PiecePosition(newPos[0], newPos[1]));
            } catch (MoveNotAllowedException e) {
                controller.showAlert(e.getMessage());
            }
        }
    }

    /***
     * Switches a turn.
     * @param id Id of a player that turn we switch to.
     */
    public void nextTurn(int id) {
        AbstractController.getGame().setTurn(id);
        if (controller.getClass() == Game.class) {
            ((Game) controller).nextTurn();
        }
    }

    /***
     * Pre-game method. Adds a player to the game.
     * @param nick The nickname of a player.
     * @param id Player's id.
     */
    public void addPlayer(String nick, int id) {
        if (controller.getClass() == Pregame.class) {
            Player player = new Player(nick);
            player.setId(id);
            AbstractController.getGame().createArmy(player);
            try {
                AbstractController.getGame().addPlayer(player);
            } catch (CannotAddPlayerException e) {
                controller.showAlert(e.getMessage());
                e.printStackTrace();
            }
            ((Pregame) controller).addPlayer(nick, id);
        } else {
            System.out.println("Wrong controller state");
        }
    }

    public void ready(int id) {
        if (controller.getClass() == Pregame.class) {
            ((Pregame) controller).readyPlayer(id);
        } else {
            System.out.println("Wrong controller state");
        }
    }

    /***
     * Method used to switch between pre-game and game.
     */
    public void startGame() { //pass new game controller here
        if (controller.getClass() == Pregame.class) {
            ((Pregame) controller).startGame();
        } else {
            System.out.println("Wrong controller state");
        }
    }

    public void alert(String message) {
        controller.showAlert(message);
    }
}
