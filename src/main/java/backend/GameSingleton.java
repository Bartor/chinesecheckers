package backend;

import model.board.BoardMovementInterface;
import model.game.AbstractGame;
import model.game.BasicGame;
import model.player.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/***
 * Hold synchronized game instance for all threads.
 * Isn't really used by all threads tho, but whatever.
 */
public class GameSingleton {
    private static BoardMovementInterface boardMovementInterface;
    private static int limit;
    private static AbstractGame game;
    private static Set<Integer> readiedPlayers;
    private static Set<Player> alredyWon;

    public GameSingleton(BoardMovementInterface boardMovementInterface, int limit) {
        GameSingleton.boardMovementInterface = boardMovementInterface;
        GameSingleton.limit = limit;
        readiedPlayers = new HashSet<>();
        alredyWon = new HashSet<>();
    }
    public static Set<Player> getWinners(){
        return alredyWon;
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

    public static boolean readyPlayer(int id) {
        System.out.println(readiedPlayers.size() + " " + limit);
        readiedPlayers.add(id);
        return readiedPlayers.size() == limit;
    }
}
