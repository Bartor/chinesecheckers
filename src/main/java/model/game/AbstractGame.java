package model.game;

import model.board.BoardMovementInterface;
import model.exceptions.CannotAddPlayerException;
import model.exceptions.PlayerNotFullyInitializedException;
import model.player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGame {
    protected List<Player> players;
    protected BoardMovementInterface boardMovementInterface;
    protected int limit;

    public BoardMovementInterface getBoardMovementInterface() {
        return boardMovementInterface;
    }

    public void addPlayer(Player player) throws CannotAddPlayerException {
        if (players.size() == limit) throw new CannotAddPlayerException("Maximum players threshold reached");
        if (players == null) {
            players = new ArrayList<Player>();
            try {
                player.setId(0);
            } catch (PlayerNotFullyInitializedException e) {
                throw new CannotAddPlayerException("Given player has not been initialized");
            }
        }
        if (players.contains(player)) return;
        try {
            player.setId(players.get(players.size() - 2).getId() + 1);
        } catch (PlayerNotFullyInitializedException e) {
            throw new CannotAddPlayerException("Given player has not been initialized");
        }
        players.add(player);
    }

    abstract public boolean hasWon(Player player);
}