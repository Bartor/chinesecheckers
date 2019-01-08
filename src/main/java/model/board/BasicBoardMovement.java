package model.board;

import model.exceptions.MoveNotAllowedException;
import model.player.Piece;
import model.player.PiecePosition;

import java.util.HashSet;

/***
 * Basic board movement.
 */
public class BasicBoardMovement implements BoardMovementInterface {
    /***
     * Board on which we move.
     */
    private BoardInterface board;

    /***
     * Creates this class using given board.
     * @param board Board to use.
     */
    public BasicBoardMovement(BoardInterface board) {
        this.board = board;
    }

    /***
     * Utility function, checks if a given piece is on its winning zone.
     * @param piece A piece to be checked.
     * @return True if the piece is on the winning zone, false otherwise.
     */
    public boolean onWinZone(Piece piece) {
        int[][] layout = this.board.getBoardFields();
        int winningId = (piece.getId() + 2) % 6 + 1;
        return layout[piece.getPosition().getRow()][piece.getPosition().getCol()] == winningId;
    }

    /***
     * Moves a piece
     * @param piece Piece to be moved
     * @param newPosition A position piece is moved to
     * @throws MoveNotAllowedException Is newPosition isn't a valid position to move, an exception is thrown.
     */
    public void makeMove(Piece piece, PiecePosition newPosition) throws MoveNotAllowedException {
        Piece movedPiece = new Piece(newPosition);
        movedPiece.setId(piece.getId());
        if (onWinZone(piece) && !onWinZone(movedPiece))
            throw new MoveNotAllowedException("You can't go out the winning zone");
        PiecePosition[] positions = getMoves(piece);
        for (PiecePosition position : positions) {
            if (newPosition.compareTo(position) == 0) {
                this.board.setPositions(piece.getPosition().getRow(), piece.getPosition().getCol(), 0);
                this.board.setPositions(newPosition.getRow(), newPosition.getCol(), piece.getId());
                piece.setPosition(newPosition);
                return;
            }
        }
        throw new MoveNotAllowedException("That move is not allowed for given piece");
    }


    /***
     * Gets possible moves
     * @param piece A piece we want to get moves for
     * @return An array of possible positions
     */
    public PiecePosition[] getMoves(Piece piece) {

        int row = piece.getPosition().getRow();
        int col = piece.getPosition().getCol();
        PiecePosition[] possibleMoves;
        HashSet<PiecePosition> tempSet = new HashSet<>();

        if (row % 2 == 0) {
            if (board.fieldNotNull(row, col + 1) && board.getPositions()[row][col + 1] == 0)
                tempSet.add(new PiecePosition(row, col + 1));
            if (board.fieldNotNull(row - 1, col) && board.getPositions()[row - 1][col] == 0)
                tempSet.add(new PiecePosition(row - 1, col));
            if (board.fieldNotNull(row - 1, col - 1) && board.getPositions()[row - 1][col - 1] == 0)
                tempSet.add(new PiecePosition(row - 1, col - 1));
            if (board.fieldNotNull(row, col - 1) && board.getPositions()[row][col - 1] == 0)
                tempSet.add(new PiecePosition(row, col - 1));
            if (board.fieldNotNull(row + 1, col - 1) && board.getPositions()[row + 1][col - 1] == 0)
                tempSet.add(new PiecePosition(row + 1, col - 1));
            if (board.fieldNotNull(row + 1, col) && board.getPositions()[row + 1][col] == 0)
                tempSet.add(new PiecePosition(row + 1, col));
        } else {
            if (board.fieldNotNull(row, col + 1) && board.getPositions()[row][col + 1] == 0)
                tempSet.add(new PiecePosition(row, col + 1));
            if (board.fieldNotNull(row - 1, col + 1) && board.getPositions()[row - 1][col + 1] == 0)
                tempSet.add(new PiecePosition(row - 1, col + 1));
            if (board.fieldNotNull(row - 1, col) && board.getPositions()[row - 1][col] == 0)
                tempSet.add(new PiecePosition(row - 1, col));
            if (board.fieldNotNull(row, col - 1) && board.getPositions()[row][col - 1] == 0)
                tempSet.add(new PiecePosition(row, col - 1));
            if (board.fieldNotNull(row + 1, col) && board.getPositions()[row + 1][col] == 0)
                tempSet.add(new PiecePosition(row + 1, col));
            if (board.fieldNotNull(row + 1, col + 1) && board.getPositions()[row + 1][col + 1] == 0)
                tempSet.add(new PiecePosition(row + 1, col + 1));
        }
        getMovesByJump(new PiecePosition(row, col), tempSet);
        possibleMoves = tempSet.toArray(new PiecePosition[0]);
        return possibleMoves;
    }

    /**
     * Subfunction for getMoves(), dealing ONLY with jumping
     *
     * @param position A pieceposition we want to get moves for
     */
    private void getMovesByJump(PiecePosition position, HashSet<PiecePosition> tempSet) {

        int col = position.getCol();
        int row = position.getRow();
        if (row % 2 == 0) {
            if (board.fieldNotNull(row, col + 1) && board.getPositions()[row][col + 1] != 0 &&
                    board.fieldNotNull(row, col + 2) && board.getPositions()[row][col + 2] == 0) {
                if (tempSet.add(new PiecePosition(row, col + 2)))
                    getMovesByJump(new PiecePosition(row, col + 2), tempSet);
            }
            if (board.fieldNotNull(row - 1, col) && board.getPositions()[row - 1][col] != 0 &&
                    board.fieldNotNull(row - 2, col + 1) && board.getPositions()[row - 2][col + 1] == 0) {
                if (tempSet.add(new PiecePosition(row - 2, col + 1)))
                    getMovesByJump(new PiecePosition(row - 2, col + 1), tempSet);
            }
            if (board.fieldNotNull(row - 1, col - 1) && board.getPositions()[row - 1][col - 1] != 0 &&
                    board.fieldNotNull(row - 2, col - 1) && board.getPositions()[row - 2][col - 1] == 0) {
                if (tempSet.add(new PiecePosition(row - 2, col - 1)))
                    getMovesByJump(new PiecePosition(row - 2, col - 1), tempSet);
            }
            if (board.fieldNotNull(row, col - 1) && board.getPositions()[row][col - 1] != 0 &&
                    board.fieldNotNull(row, col - 2) && board.getPositions()[row][col - 2] == 0) {
                if (tempSet.add(new PiecePosition(row, col - 2)))
                    getMovesByJump(new PiecePosition(row, col - 2), tempSet);
            }
            if (board.fieldNotNull(row + 1, col - 1) && board.getPositions()[row + 1][col - 1] != 0 &&
                    board.fieldNotNull(row + 2, col - 1) && board.getPositions()[row + 2][col - 1] == 0) {
                if (tempSet.add(new PiecePosition(row + 2, col - 1)))
                    getMovesByJump(new PiecePosition(row + 2, col - 1), tempSet);
            }
            if (board.fieldNotNull(row + 1, col) && board.fieldNotNull(row + 2, col + 1) &&
                    board.getPositions()[row + 1][col] != 0 && board.getPositions()[row + 2][col + 1] == 0) {
                if (tempSet.add(new PiecePosition(row + 2, col + 1)))
                    getMovesByJump(new PiecePosition(row + 2, col + 1), tempSet);
            }
        } else {
            if (board.fieldNotNull(row, col + 1) && board.getPositions()[row][col + 1] != 0 &&
                    board.fieldNotNull(row, col + 2) && board.getPositions()[row][col + 2] == 0) {
                if (tempSet.add(new PiecePosition(row, col + 2)))
                    getMovesByJump(new PiecePosition(row, col + 2), tempSet);
            }
            if (board.fieldNotNull(row - 1, col + 1) && board.getPositions()[row - 1][col + 1] != 0 &&
                    board.fieldNotNull(row - 2, col + 1) && board.getPositions()[row - 2][col + 1] == 0) {
                if (tempSet.add(new PiecePosition(row - 2, col + 1)))
                    getMovesByJump(new PiecePosition(row - 2, col + 1), tempSet);
            }
            if (board.fieldNotNull(row - 1, col) && board.getPositions()[row - 1][col] != 0 &&
                    board.fieldNotNull(row - 2, col - 1) && board.getPositions()[row - 2][col - 1] == 0) {
                if (tempSet.add(new PiecePosition(row - 2, col - 1)))
                    getMovesByJump(new PiecePosition(row - 2, col - 1), tempSet);
            }
            if (board.fieldNotNull(row, col - 1) && board.getPositions()[row][col - 1] != 0 &&
                    board.fieldNotNull(row, col - 2) && board.getPositions()[row][col - 2] == 0) {
                if (tempSet.add(new PiecePosition(row, col - 2)))
                    getMovesByJump(new PiecePosition(row, col - 2), tempSet);
            }
            if (board.fieldNotNull(row + 1, col) && board.getPositions()[row + 1][col] != 0 &&
                    board.fieldNotNull(row + 2, col - 1) && board.getPositions()[row + 2][col - 1] == 0) {
                if (tempSet.add(new PiecePosition(row + 2, col - 1)))
                    getMovesByJump(new PiecePosition(row + 2, col - 1), tempSet);
            }
            if (board.fieldNotNull(row + 1, col + 1) && board.getPositions()[row + 1][col + 1] != 0 &&
                    board.fieldNotNull(row + 2, col + 1) && board.getPositions()[row + 2][col + 1] == 0) {
                if (tempSet.add(new PiecePosition(row + 2, col + 1)))
                    getMovesByJump(new PiecePosition(row + 2, col + 1), tempSet);
            }
        }
    }

    /***
     * Gets the board.
     * @return The board.
     */
    public BoardInterface getBoard() {
        return this.board;
    }
}
