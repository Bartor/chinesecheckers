package model.game;

import model.board.BoardMovementInterface;
import model.exceptions.CannotAddPlayerException;
import model.exceptions.NoSuchPlayerException;
import model.player.Player;

import java.util.ArrayList;
import java.util.List;

/***
 * Abstract class holding basic information about a game.
 */
public abstract class AbstractGame {
    /***
     * List of players.
     */
    protected List<Player> players = new ArrayList<Player>();
    /***
     * Movement scheme used by the game.
     */
    protected BoardMovementInterface boardMovementInterface;
    /***
     * Current turn in form of an id.
     */
    protected int turn;
    /**
     * Number of players this game is for.
     */
    protected int limit;

    /***
     * Adds a new player to the game.
     * @param player Player being added.
     * @return Id of the new player or -1 if player's already in the game.
     * @throws CannotAddPlayerException If game if full.
     */
    public int addPlayer(Player player) throws CannotAddPlayerException {
        if (players.size() == limit) throw new CannotAddPlayerException("Maximum players threshold reached");
        if (players.contains(player)) return -1;
        if (players.size() == 0) {
            player.setId(1);
            players.add(player);
            return 1;
        }
        //ids in correct order (1, 4), (1, 2, 4, 5) and (1, 2, 3, 4, 5, 6) only
        //finally order (1,4,2,5,3,6)
        player.setId(((2 * (players.size() + 5) - 1) + 5 * (int) Math.pow(-1, (players.size() + 5))) / 4);
        players.add(player);
        return player.getId();
    }

    /***
     * Gets the id of next player (if he was added to the game).
     * @return Next player's id.
     */
    public int getNextId() {
        return ((2 * (players.size() + 5) - 1) + 5 * (int) Math.pow(-1, (players.size() + 5))) / 4;
    }

    /***
     * Adds a player who already has an id.
     * @param player Player to be added.
     * @throws CannotAddPlayerException If game is full or he's already in the game.
     */
    public void addPlayerWithId(Player player) throws CannotAddPlayerException {
        if (players.size() == limit) throw new CannotAddPlayerException("Maximum players threshold reached");
        if (players.contains(player)) throw new CannotAddPlayerException("Player already there");
        players.add(player);
    }

    /***
     * Tries to fetch a player with given id.
     * @param id Id of a player.
     * @return Player with given id, if he's there.
     * @throws NoSuchPlayerException If there is no player with such id.
     */
    public Player getPlayerById(int id) throws NoSuchPlayerException {
        for (Player player : this.players) {
            if (player.getId() == id) return player;
        }
        throw new NoSuchPlayerException("There is no such player in this game");
    }

    /***
     * Gets current turn.
     * @return Current turn.
     */
    public int getTurn() {
        return this.turn;
    }

    /***
     * Sets current turn.
     * @param turn Turn to set.
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /***
     * Gets the list of players.
     * @return List of game's players.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /***
     * Gets the movement.
     * @return Movement instance used by this game.
     */
    public BoardMovementInterface getBoardMovementInterface() {
        return boardMovementInterface;
    }

    /***
     * Returns the number of players the game's for.
     * @return Number of players.
     */
    public int getLimit() {
        return limit;
    }

    /***
     * Abstract methos used to create an army for a player.
     * @param player Player whose army we're creating.
     */
    abstract public void createArmy(Player player);

    /***
     * Abstract method used to check if somebody won.
     * @param player Player being checked.
     * @return True, if he won, false otherwise.
     */
    abstract public boolean hasWon(Player player);

}