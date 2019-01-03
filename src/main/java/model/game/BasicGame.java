package model.game;

import com.sun.istack.internal.NotNull;
import model.board.BoardMovementInterface;
import model.player.Army;
import model.player.Piece;
import model.player.PiecePosition;
import model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class BasicGame extends AbstractGame {
    public BasicGame(@NotNull BoardMovementInterface boardMovementInterface, int playersLimit) {
        this.boardMovementInterface = boardMovementInterface;
        this.limit = playersLimit;
    }

    @Override
    public boolean hasWon(Player player) {
        for (Piece piece : player.getArmy().getPieces()) {
            if (!this.boardMovementInterface.onWinZone(piece)) return false;
        }
        return true;
    }

    @Override
    public void createArmy(Player player) {
        int boardFields[][] = this.boardMovementInterface.getBoard().getBoardFields();
        List<PiecePosition> startingPositions = new ArrayList<PiecePosition>();
        for (int i = 0; i < boardFields.length; i++) {
            for (int j = 0; j < boardFields[i].length; j++) {
                if (boardFields[i][j] == player.getId()) {
                    startingPositions.add(new PiecePosition(i, j));
                }
            }
        }
        PiecePosition[] pos = new PiecePosition[startingPositions.size()];
        for (int i = 0; i < startingPositions.size(); i++) {
            pos[i] = startingPositions.get(i);
        }
        Army army = new Army(pos);
        player.setArmy(army);
    }
}
