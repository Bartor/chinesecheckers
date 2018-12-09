package model.board;

import model.exceptions.MoveNotAllowedException;
import model.player.PiecePosition;
import model.player.Piece;

public interface BoardMovementInterface {
    void makeMove(Piece piece, PiecePosition newPosition) throws MoveNotAllowedException;
    PiecePosition[] getMoves(Piece piece);
}