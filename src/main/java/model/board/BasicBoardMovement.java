package model.board;

import model.exceptions.MoveNotAllowedException;
import model.player.PiecePosition;
import model.player.Piece;

public class BasicBoardMovement implements BoardMovementInterface {
    private BoardInterdace board;

    public BasicBoardMovement(BoardInterdace board) {
        this.board = board;
    }

    /***
     * Moves a piece
     * @param piece Piece to be moved
     * @param newPosition A position piece is moved to
     * @throws MoveNotAllowedException Is newPosition isn't a valid position to move, an exception is thrown.
     */
    public void makeMove(Piece piece, PiecePosition newPosition) throws MoveNotAllowedException {
        throw new MoveNotAllowedException("");
        //todo implement
    }

    /***
     * Gets all possible moves for a given piece, including jumping (single jumps only)
     * @param piece A piece we want to get moves for
     * @return An array of possible positions
     */
    public PiecePosition[] getMoves(Piece piece) {
        //todo implement
        return null;
    }
}
