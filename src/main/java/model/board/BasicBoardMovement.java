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
        if (onWinZone(piece)) throw new MoveNotAllowedException("You can't go out the winning zone");
        PiecePosition[] positions = getMoves(piece);
        for(PiecePosition position : positions) {
            if (newPosition.equals(position)) {
                this.board.setPositions(piece.getPosition().getRow(), piece.getPosition().getCol(), 0);
                this.board.setPositions(newPosition.getRow(), newPosition.getCol(), piece.getId());
                piece.setPosition(newPosition);
                return;
            }
        }
        throw new MoveNotAllowedException("That move is not allowed for given piece");
    }

    /***
     * Gets all possible moves for a given piece, including jumping (single jumps only)
     * @param piece A piece we want to get moves for
     * @return An array of possible positions
     */
    public PiecePosition[] getMoves(Piece piece) {
        // x - column in order, y - row
        int y = piece.getPosition().getRow();
        int x = piece.getPosition().getCol();
        PiecePosition[] possibleMoves;
        Set<PiecePosition> tempSet = new HashSet<PiecePosition>();
        if(board.fieldNotNull(x+1,y)  && board.getPositions()[x+1][y]==0)
            tempSet.add(new PiecePosition(y, x+1));
        if(board.fieldNotNull(x-1,y)  && board.getPositions()[x-1][y]==0)
            tempSet.add(new PiecePosition(y, x-1));
        if(board.fieldNotNull(x-1,y+1)  && board.getPositions()[x-1][y+1]==0)
            tempSet.add(new PiecePosition(y+1, x-1));
        if(board.fieldNotNull(x,y+1)  && board.getPositions()[x][y+1]==0)
            tempSet.add(new PiecePosition(y+1, x));
        if(board.fieldNotNull(x-1,y-1)  && board.getPositions()[x-1][y-1]==0)
            tempSet.add(new PiecePosition(y-1, x-1));
        if(board.fieldNotNull(x,y-1)  && board.getPositions()[x][y-1]==0)
            tempSet.add(new PiecePosition(y-1, x));
        getMovesByOverJumping(tempSet, x, y);
        possibleMoves=tempSet.toArray(new PiecePosition[0]);
        return possibleMoves;
    }

    /**
     * Subfunction for getMoves(), dealing with jumping
     * @param tempSet Temporary set of PiecePositions
     * @param x Position's column
     * @param y Position's row
     */
    private void getMovesByOverJumping(Set<PiecePosition> tempSet, int x, int y) {

        if(board.fieldNotNull(x+1,y) && board.getPositions()[x+1][y]!=0 &&
                board.fieldNotNull(x+2,y) && board.getPositions()[x+2][y]==0 ) {
            if(!tempSet.add(new PiecePosition(y, x+2)))
                return;
            getMovesByOverJumping(tempSet,x+2, y);
        }
        if(board.fieldNotNull(x-1,y) && board.getPositions()[x-1][y] !=0 &&
                board.fieldNotNull(x-2,y) && board.getPositions()[x-2][y] ==0 ) {
            if(!tempSet.add(new PiecePosition(y, x - 2)))
                return;
            getMovesByOverJumping(tempSet,x-2, y);
        }
        if( board.fieldNotNull(x-1,y) && board.getPositions()[x-1][y] !=0 &&
                board.fieldNotNull(x-2,y) && board.getPositions()[x-2][y] ==0 ) {
            if(!tempSet.add(new PiecePosition(y, x-2)))
                return;
            getMovesByOverJumping(tempSet,x-2, y);
        }
        if(board.fieldNotNull(x-1,y+1) && board.getPositions()[x-1][y+1]!=0 &&
                board.fieldNotNull(x-1,y+2) && board.getPositions()[x-1][y+2] ==0 ) {
            if(!tempSet.add(new PiecePosition(y+2, x-1)))
                return;
            getMovesByOverJumping(tempSet, x-1, y+2);
        }
        if(board.fieldNotNull(x-1,y-1) && board.getPositions()[x-1][y-1]!=0 &&
                board.fieldNotNull(x-1,y-2) && board.getPositions()[x-1][y-2] ==0 ) {
            if(!tempSet.add(new PiecePosition(y-2, x-1)))
                return;
            getMovesByOverJumping(tempSet, x-1, y-2);
        }
        if( board.fieldNotNull(x,y-1) && board.fieldNotNull(x+1,y-2) &&
                board.getPositions()[x][y-1]!=0 && board.getPositions()[x+1][y-2] ==0 ) {
            if(!tempSet.add(new PiecePosition(y-2, x+1)))
                return;
            getMovesByOverJumping(tempSet, x+1, y-2);
        }
    }
}
