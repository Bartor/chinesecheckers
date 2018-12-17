package model.game;

import model.board.BoardMovementInterface;
import model.exceptions.CannotAddPlayerException;
import model.exceptions.PlayerNotFullyInitializedException;
import model.player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGame {
    protected List<Player> players = new ArrayList<Player>();
    protected BoardMovementInterface boardMovementInterface;
    protected int turn;
    protected int limit;

    public BoardMovementInterface getBoardMovementInterface() {
        return boardMovementInterface;
    }

    public int addPlayer(Player player) throws CannotAddPlayerException {
        if (players.size() == limit) throw new CannotAddPlayerException("Maximum players threshold reached");
        if (players.contains(player)) return -1;
        if (players.size() == 0) {
            player.setId(1);
            players.add(player);
            return 1;
        }
        //todo fix ids to give (1, 4), (1, 2, 4, 5) and (1, 2, 3, 4, 5, 6) only
        player.setId(players.get(players.size() - 1).getId() + 1);
        players.add(player);
        return player.getId();
    }

    public int getLimit() {
        return limit;
    }

    public Player getPlayerById(int id) {
        for (Player player : this.players) {
            if (player.getId() == id) return player;
        }
        //todo add exception for no player
        return null;
    }

    public int getTurn() {
        return this.turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    abstract public void createArmy(Player player);

    abstract public boolean hasWon(Player player);
}