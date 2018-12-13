package model.board;

import model.exceptions.MoveNotAllowedException;
import model.player.PiecePosition;
import model.player.Piece;

public interface BoardMovementInterface {
    boolean onWinZone(Piece piece);
    void makeMove(Piece piece, PiecePosition newPosition) throws MoveNotAllowedException;
    PiecePosition[] getMoves(Piece piece);
    BoardInterdace getBoard();
}