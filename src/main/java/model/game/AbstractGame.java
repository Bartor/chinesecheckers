package model.game;

import model.board.BoardMovementInterface;
import model.exceptions.CannotAddPlayerException;
import model.exceptions.NoSuchPlayerException;
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
        //ids in correct order (1, 4), (1, 2, 4, 5) and (1, 2, 3, 4, 5, 6) only
        //finally order (1,4,2,5,3,6)
        player.setId(((2*(players.size()+5)-1)+5*(int)Math.pow(-1, (players.size()+5)))/4);
        players.add(player);
        return player.getId();
    }

    public void addPlayerWithId(Player player) throws CannotAddPlayerException {
        if (players.size() == limit) throw new CannotAddPlayerException("Maximum players threshold reached");
        if (players.contains(player)) throw new CannotAddPlayerException("Player already there");
        players.add(player);
    }

    public int getLimit() {
        return limit;
    }

    public Player getPlayerById(int id) throws NoSuchPlayerException {
        for (Player player : this.players) {
            if (player.getId() == id) return player;
        }
        throw new NoSuchPlayerException("There is no such player in this game");
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