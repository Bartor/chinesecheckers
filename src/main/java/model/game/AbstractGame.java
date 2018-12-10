package model.game;

import model.board.BoardMovementInterface;
import model.player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGame {
    protected List<Player> players;
    protected BoardMovementInterface boardMovementInterface;

    public BoardMovementInterface getBoardMovementInterface() {
        return boardMovementInterface;
    }

    public void addPlayer(Player player) {
        if (players == null) players = new ArrayList<Player>();
        if (players.contains(player)) return;
        players.add(player);
    }

    abstract public boolean hasWon(Player player);
}
