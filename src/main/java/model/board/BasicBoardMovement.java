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
     * Moves a piece
     * @param piece Piece to be moved
     * @param newPosition A position piece is moved to
     * @throws MoveNotAllowedException Is newPosition isn't a valid position to move, an exception is thrown.
     */
    public void makeMove(Piece piece, PiecePosition newPosition) throws MoveNotAllowedException {
        int row=piece.getPosition().getRow();
        int col=piece.getPosition().getCol();
        PiecePosition[] possibleMoves = getMoves(piece);
        if(piece.getPosition().equals(newPosition))
            return;
        if(board.getBoardFields()[row][col]==(piece.getId()+3)%6 && board.getBoardFields()[newPosition.getRow()][newPosition.getCol()]!=(piece.getId()+3)%6)
            throw new MoveNotAllowedException("Cannot come back from oponnent's zone");
        for(PiecePosition position : possibleMoves){
            if(newPosition.equals(position)){
                board.setPositions(row, col, 0);
                board.setPositions(newPosition.getRow(), newPosition.getCol(), piece.getId());
                return;
            }
        }
        throw new MoveNotAllowedException("Move not allowed");
    }
    // TODO
    // makeMove powinien być w dwu wersjach, zależy czy pierwszy ruch, czy po skoku
    // zobaczę jak nam będzie wygodniej ( i czy make move jako cała seria ruchów, czy wywoływane kilka razy)

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

        if(board.fieldNotNull(x+1,y) && board.getPositions()[x+1][y]==0)
            tempSet.add(new PiecePosition(y, x+2));
        else if(board.fieldNotNull(x+1,y) && board.getPositions()[x+1][y]!=0 &&
                board.fieldNotNull(x+2,y) && board.getPositions()[x+2][y]==0 ) {
            tempSet.add(new PiecePosition(y, x+2));
        }
        if(board.fieldNotNull(x-2, y) && board.getPositions()[x-1][y]==0)
            tempSet.add(new PiecePosition(y, x+2));
        else if(board.fieldNotNull(x-1,y) && board.getPositions()[x-1][y] !=0 &&
                board.fieldNotNull(x-2,y) && board.getPositions()[x-2][y] ==0 ) {
            tempSet.add(new PiecePosition(y, x - 2));
        }
        if(board.fieldNotNull(x-1,y) && board.getPositions()[x+1][y]==0)
            tempSet.add(new PiecePosition(y, x+2));
        else if( board.fieldNotNull(x-1,y) && board.getPositions()[x+1][y] !=0 &&
                board.fieldNotNull(x+1,y+2) && board.getPositions()[x+1][y+2] ==0 ) {
            tempSet.add(new PiecePosition(y+2, x+1));
        }
        if(board.fieldNotNull(x-1, y+1) && board.getPositions()[x-1][y+1]==0)
            tempSet.add(new PiecePosition(y+1, x-1));
        else if(board.fieldNotNull(x-1,y+1) && board.getPositions()[x-1][y+1]!=0 &&
                board.fieldNotNull(x-1,y+2) && board.getPositions()[x-1][y+2] ==0 ) {
            tempSet.add(new PiecePosition(y+2, x-1));
        }
        if(board.fieldNotNull(x-1, y-1) && board.getPositions()[x-1][y-1]==0)
            tempSet.add(new PiecePosition(y-1, x-1));
        else if(board.fieldNotNull(x-1,y-1) && board.getPositions()[x-1][y-1]!=0 &&
                board.fieldNotNull(x-1,y-2) && board.getPositions()[x-1][y-2] ==0 ) {
            tempSet.add(new PiecePosition(y-2, x-1));
        }
        if(board.fieldNotNull(x, y-1) && board.getPositions()[x][y-1]==0)
            tempSet.add(new PiecePosition(y-1,x));
        else if( board.fieldNotNull(x,y-1) && board.fieldNotNull(x+1,y-2) &&
                board.getPositions()[x][y-1]!=0 && board.getPositions()[x+1][y-2] ==0 ) {
            tempSet.add(new PiecePosition(y-2, x+1));
        }
        possibleMoves=tempSet.toArray(new PiecePosition[0]);
        return possibleMoves;
    }

    /***
     * Look for possible moves after jump
     * @param piece A piece we want to get jumped
     * @return An array of possible positions after jump
     */
    public PiecePosition[] getMovesAfterJump(Piece piece) {

        int x=piece.getPosition().getCol();
        int y=piece.getPosition().getRow();
        Set<PiecePosition> tempSet = new HashSet<PiecePosition>();
        PiecePosition[] possibleMoves;

        if(board.fieldNotNull(x+1,y) && board.getPositions()[x+1][y]!=0 &&
                board.fieldNotNull(x+2,y) && board.getPositions()[x+2][y]==0 ) {
            tempSet.add(new PiecePosition(y, x+2));
        }
        if(board.fieldNotNull(x-1,y) && board.getPositions()[x-1][y] !=0 &&
                board.fieldNotNull(x-2,y) && board.getPositions()[x-2][y] ==0 ) {
            tempSet.add(new PiecePosition(y, x - 2));
        }
        if( board.fieldNotNull(x-1,y) && board.getPositions()[x+1][y] !=0 &&
                board.fieldNotNull(x+1,y+2) && board.getPositions()[x+1][y+2] ==0 ) {
            tempSet.add(new PiecePosition(y+2, x+1));
        }
        if(board.fieldNotNull(x-1,y+1) && board.getPositions()[x-1][y+1]!=0 &&
                board.fieldNotNull(x-1,y+2) && board.getPositions()[x-1][y+2] ==0 ) {
            tempSet.add(new PiecePosition(y+2, x-1));
        }
        if(board.fieldNotNull(x-1,y-1) && board.getPositions()[x-1][y-1]!=0 &&
                board.fieldNotNull(x-1,y-2) && board.getPositions()[x-1][y-2] ==0 ) {
            tempSet.add(new PiecePosition(y-2, x-1));
        }
        if( board.fieldNotNull(x,y-1) && board.fieldNotNull(x+1,y-2) &&
                board.getPositions()[x][y-1]!=0 && board.getPositions()[x+1][y-2] ==0 ) {
            tempSet.add(new PiecePosition(y-2, x+1));
        }
        possibleMoves=tempSet.toArray(new PiecePosition[0]);
        return possibleMoves;
    }
}
