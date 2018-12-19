package model.board;

import model.exceptions.MoveNotAllowedException;
import model.player.PiecePosition;
import model.player.Piece;

import java.util.HashSet;
import java.util.Set;

public class BasicBoardMovement implements BoardMovementInterface {
    private BoardInterdace board;

    public BasicBoardMovement(BoardInterdace board) {
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
        if (layout[piece.getPosition().getRow()][piece.getPosition().getCol()] == winningId) {
            return true;
        }
        return false;
    }
    /***
     * Moves a piece
     * @param piece Piece to be moved
     * @param newPosition A position piece is moved to
     * @throws MoveNotAllowedException Is newPosition isn't a valid position to move, an exception is thrown.
     */
    public void makeMove(Piece piece, PiecePosition newPosition) throws MoveNotAllowedException {
        if (onWinZone(piece) && !onWinZone(new Piece(newPosition))) throw new MoveNotAllowedException("You can't go out the winning zone");
        PiecePosition[] positions = getMoves(piece);
        for(PiecePosition position : positions) {
            if (newPosition.compareTo(position)==0) {
                this.board.setPositions(piece.getPosition().getRow(), piece.getPosition().getCol(), 0);
                this.board.setPositions(newPosition.getRow(), newPosition.getCol(), piece.getId());
                piece.setPosition(newPosition);
                return;
            }
        }
        throw new MoveNotAllowedException("That move is not allowed for given piece");
    }

    /***
     * Moves a piece after first jump
     * @param piece Piece to be moved
     * @param newPosition A position piece is moved to
     * @throws MoveNotAllowedException
     */
    public void makeMoveByJump(Piece piece, PiecePosition newPosition) throws MoveNotAllowedException {
        if (onWinZone(piece) && !onWinZone(new Piece(newPosition))) throw new MoveNotAllowedException("You can't go out the winning zone");
        PiecePosition[] positions = getMovesByJump(piece);
        for(PiecePosition position : positions) {
            if (newPosition.compareTo(position)==0) {
                this.board.setPositions(piece.getPosition().getRow(), piece.getPosition().getCol(), 0);
                this.board.setPositions(newPosition.getRow(), newPosition.getCol(), piece.getId());
                piece.setPosition(newPosition);
                return;
            }
        }
        throw new MoveNotAllowedException("That move is not allowed for given piece");

    }

    /***
     * Gets possible moves without jumping
     * @param piece A piece we want to get moves for
     * @return An array of possible positions
     */
    public PiecePosition[] getMoves(Piece piece) {
        // x - column in order, y - row
        int row = piece.getPosition().getRow();
        int col = piece.getPosition().getCol();
        PiecePosition[] possibleMoves;
        Set<PiecePosition> tempSet = new HashSet<PiecePosition>();

        if(row%2==0){
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

        }
        else{
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

        possibleMoves=tempSet.toArray(new PiecePosition[0]);
        return possibleMoves;
    }

    /**
     * Subfunction for getMoves(), dealing ONLY with jumping
     * @param piece A piece we want to get moves for
     */
    public PiecePosition[] getMovesByJump(Piece piece) {

        int col = piece.getPosition().getCol();
        int row = piece.getPosition().getRow();
        Set<PiecePosition> tempSet = new HashSet<PiecePosition>();
        PiecePosition[] possibleMoves;

        if(row%2==0){
            if (board.fieldNotNull(row, col + 1) && board.getPositions()[row][col + 1] != 0 &&
                    board.fieldNotNull(row, col + 2) && board.getPositions()[row][col + 2] == 0) {
                tempSet.add(new PiecePosition(row, col + 2));
            }
            if (board.fieldNotNull(row - 1, col) && board.getPositions()[row - 1][col] != 0 &&
                    board.fieldNotNull(row - 2, col + 1) && board.getPositions()[row - 2][col + 1] == 0) {
                tempSet.add(new PiecePosition(row - 2, col + 1));
            }
            if (board.fieldNotNull(row - 1, col - 1) && board.getPositions()[row - 1][col - 1] != 0 &&
                    board.fieldNotNull(row - 2, col - 1) && board.getPositions()[row - 2][col - 1] == 0) {
                tempSet.add(new PiecePosition(row - 2, col - 1));
            }
            if (board.fieldNotNull(row, col - 1) && board.getPositions()[row][col - 1] != 0 &&
                    board.fieldNotNull(row, col - 2) && board.getPositions()[row][col - 2] == 0) {
                tempSet.add(new PiecePosition(row, col - 2));
            }
            if (board.fieldNotNull(row + 1, col - 1) && board.getPositions()[row + 1][col - 1] != 0 &&
                    board.fieldNotNull(row + 2, col - 1) && board.getPositions()[row + 2][col - 1] == 0) {
                tempSet.add(new PiecePosition(row + 2, col - 1));
            }
            if (board.fieldNotNull(row + 1, col) && board.fieldNotNull(row + 2, col + 1) &&
                    board.getPositions()[row + 1][col] != 0 && board.getPositions()[row + 2][col + 1] == 0) {
                tempSet.add(new PiecePosition(row + 2, col + 1));
            }
        }
        else {
            if (board.fieldNotNull(row, col + 1) && board.getPositions()[row][col + 1] != 0 &&
                    board.fieldNotNull(row, col + 2) && board.getPositions()[row][col + 2] == 0) {
                tempSet.add(new PiecePosition(row, col + 2));
            }
            if (board.fieldNotNull(row - 1, col + 1) && board.getPositions()[row - 1][col + 1] != 0 &&
                    board.fieldNotNull(row - 2, col + 1) && board.getPositions()[row - 2][col + 1] == 0) {
                tempSet.add(new PiecePosition(row - 2, col + 1));
            }
            if (board.fieldNotNull(row - 1, col) && board.getPositions()[row - 1][col] != 0 &&
                    board.fieldNotNull(row - 2, col - 1) && board.getPositions()[row - 2][col - 1] == 0) {
                tempSet.add(new PiecePosition(row - 2, col - 1));
            }
            if (board.fieldNotNull(row, col - 1) && board.getPositions()[row][col - 1] != 0 &&
                    board.fieldNotNull(row, col - 2) && board.getPositions()[row][col - 2] == 0) {
                tempSet.add(new PiecePosition(row, col - 2));
            }
            if (board.fieldNotNull(row + 1, col) && board.getPositions()[row + 1][col] != 0 &&
                    board.fieldNotNull(row + 2, col - 1) && board.getPositions()[row + 2][col - 1] == 0) {
                tempSet.add(new PiecePosition(row + 2, col - 1));
            }
            if (board.fieldNotNull(row + 1, col + 1) && board.fieldNotNull(row + 2, col + 1) &&
                    board.getPositions()[row + 1][col + 1] != 0 && board.getPositions()[row + 2][col + 1] == 0) {
                tempSet.add(new PiecePosition(row + 2, col + 1));
            }

        }
        possibleMoves = tempSet.toArray(new PiecePosition[0]);
        return possibleMoves;
    }

    public BoardInterdace getBoard() {
        return this.board;
    }
}
