package model.board;

import model.exceptions.MoveNotAllowedException;
import model.player.Piece;
import model.player.PiecePosition;

/***
 * Interface for all movements.
 */
public interface BoardMovementInterface {
    /***
     * Checks if a piece is on a winning zone.
     * @param piece A piece to check.
     * @return True if it is, false otherwise.
     */
    boolean onWinZone(Piece piece);

    /***
     * Moves a pieces.
     * @param piece Piece to be moved.
     * @param newPosition Position it is moved to.
     * @throws MoveNotAllowedException If you cannot make that move.
     */
    void makeMove(Piece piece, PiecePosition newPosition) throws MoveNotAllowedException;

    /***
     * Returns all possible moves for a particular piece.
     * @param piece Piece we are checking.
     * @return Array of PiecePosition moves.
     */
    PiecePosition[] getMoves(Piece piece);

    /***
     * Returns board used by this movement.
     * @return Board.
     */
    BoardInterface getBoard();
}