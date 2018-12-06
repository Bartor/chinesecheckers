package model.board;

import model.player.PiecePosition;
import model.player.Piece;

public class BasicBoardMovement implements BoardMovementInterface {
    private BoardInterdace board;

    public BasicBoardMovement(BoardInterdace board) {
        this.board = board;
    }

    public PiecePosition makeMove(Piece piece, PiecePosition newPosition) {
        //todo implement
        return null;
    }

    public int[][] getMoves() {
        //todo implement
        return new int[0][];
    }
}
