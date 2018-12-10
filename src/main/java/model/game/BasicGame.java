package model.game;

import model.board.BoardMovementInterface;
import model.player.Piece;
import model.player.Player;

import java.util.List;

public class BasicGame extends AbstractGame {
    public BasicGame(BoardMovementInterface boardMovementInterface) {
        this.boardMovementInterface = boardMovementInterface;
    }

    @Override
    public boolean hasWon(Player player) {
        for (Piece piece : player.getArmy().getPieces()) {
            if (!this.boardMovementInterface.onWinZone(piece)) return false;
        }
        return true;
    }
}
