package backend;

import model.board.BoardMovementInterface;
import model.game.AbstractGame;
import model.game.BasicGame;

/***
 * Hold synchronized game instance for all threads.
 * Isn't really used by all threads tho, but whatever.
 */
public class GameSingleton {
    private static BoardMovementInterface boardMovementInterface;
    private static int limit;
    private static AbstractGame game;
    private static int readiedPlayers;

    public GameSingleton(BoardMovementInterface boardMovementInterface, int limit) {
        GameSingleton.boardMovementInterface = boardMovementInterface;
        GameSingleton.limit = limit;
        readiedPlayers = 0;
    }

    public static AbstractGame getGame() {
        if (game == null) {
            synchronized (GameSingleton.class) {
                if (game == null) {
                    game = new BasicGame(boardMovementInterface, limit);
                }
            }
        }
        return game;
    }

    public static boolean readyPlayer() {
        return ++GameSingleton.readiedPlayers == limit;
    }
}
