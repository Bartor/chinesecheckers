package backend;

import model.board.BoardMovementInterface;
import model.game.AbstractGame;
import model.game.BasicGame;
import model.player.Player;

import java.util.HashSet;
import java.util.Set;

/***
 * Hold synchronized game instance for all threads.
 */
public class GameSingleton {
    /***
     * Movement used by this class.
     */
    private static BoardMovementInterface boardMovementInterface;
    /***
     * Maximum number of players.
     */
    private static int limit;
    /***
     * Instance of a game.
     */
    private static AbstractGame game;
    /***
     * Players that are ready.
     */
    private static Set<Integer> readiedPlayers;
    /***
     * Players that already won.
     */
    private static Set<Player> alreadyWon;

    /***
     * To initialize this class with proper data.
     * @param boardMovementInterface Movement to use.
     * @param limit Number of players.
     */
    public GameSingleton(BoardMovementInterface boardMovementInterface, int limit) {
        GameSingleton.boardMovementInterface = boardMovementInterface;
        GameSingleton.limit = limit;
        readiedPlayers = new HashSet<>();
        alreadyWon = new HashSet<>();
    }

    /***
     * Gets the winners.
     * @return Winners.
     */
    public static Set<Player> getWinners() {
        return alreadyWon;
    }

    /***
     * Gets a synchronized instance of the game being played.
     * @return The game.
     */
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

    /***
     * Readies a player.
     * @param id Id of a player to ready.
     * @return True, if all players have been readied, false otherwise.
     */
    public static boolean readyPlayer(int id) {
        readiedPlayers.add(id);
        return readiedPlayers.size() == limit;
    }
}
