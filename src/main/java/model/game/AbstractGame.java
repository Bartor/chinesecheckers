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
    protected int limit;

    public BoardMovementInterface getBoardMovementInterface() {
        return boardMovementInterface;
    }

    public void addPlayer(Player player) throws CannotAddPlayerException {
        if (players.size() == limit) throw new CannotAddPlayerException("Maximum players threshold reached");
        if (players.contains(player)) return;
        if (players.size() == 0) {
            try {
                player.setId(1);
                players.add(player);
                return;
            } catch (PlayerNotFullyInitializedException e) {
                throw new CannotAddPlayerException("Given player has not been initialized");
            }
        }
        try {
            player.setId(players.get(players.size() - 2).getId() + 1);
        } catch (PlayerNotFullyInitializedException e) {
            throw new CannotAddPlayerException("Given player has not been initialized");
        }
        players.add(player);
    }

    abstract public boolean hasWon(Player player);
}