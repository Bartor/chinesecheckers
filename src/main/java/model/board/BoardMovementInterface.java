package model.board;

import model.player.PiecePosition;
import model.player.Piece;

public interface BoardMovementInterface {
    PiecePosition makeMove(Piece piece, PiecePosition newPosition);
    int[][] getMoves();
}