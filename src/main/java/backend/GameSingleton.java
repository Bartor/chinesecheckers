package backend;

import model.board.BoardMovementInterface;
import model.game.AbstractGame;
import model.game.BasicGame;

public class GameSingleton {
    private static BoardMovementInterface boardMovementInterface;
    private static int limit;
    private static AbstractGame game;


    public GameSingleton(BoardMovementInterface boardMovementInterface, int limit) {
        GameSingleton.boardMovementInterface = boardMovementInterface;
        GameSingleton.limit = limit;
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
}
