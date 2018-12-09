package model.player;


import model.board.BasicBoard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Piece {
    private PiecePosition position;
    private int id;

    public Piece(PiecePosition startingPosition) {
        this.position = startingPosition;
    }

    private ArrayList<PiecePosition> possibleMoves;

    //mocno niepotymalne i dość brzydkie, ale powinno działać
    //jeszcze nad tym pomyślę i zdebadżeruję

   private void getPossibleMoves(BasicBoard board) {
       // x - column in order, y - row
       int y = position.getRow();
       int x = position.getCol();
       Set<PiecePosition> tempSet = new HashSet<PiecePosition>();
       if(board.fieldNotNull(x+1,y)  && board.getPositions()[x+1][y]==0)
               possibleMoves.add(new PiecePosition(y, x+1));
       if(board.fieldNotNull(x-1,y)  && board.getPositions()[x-1][y]==0)
           possibleMoves.add(new PiecePosition(y, x-1));
       if(board.fieldNotNull(x-1,y+1)  && board.getPositions()[x-1][y+1]==0)
           possibleMoves.add(new PiecePosition(y+1, x-1));
       if(board.fieldNotNull(x,y+1)  && board.getPositions()[x][y+1]==0)
           possibleMoves.add(new PiecePosition(y+1, x));
       if(board.fieldNotNull(x-1,y-1)  && board.getPositions()[x-1][y-1]==0)
           possibleMoves.add(new PiecePosition(y-1, x-1));
       if(board.fieldNotNull(x,y-1)  && board.getPositions()[x][y-1]==0)
           possibleMoves.add(new PiecePosition(y-1, x));
       getMovesByOverJumping(board, tempSet, x, y);
       possibleMoves.addAll(tempSet);

   }

    private void getMovesByOverJumping(BasicBoard board, Set<PiecePosition> tempSet, int x, int y) {

        if(board.fieldNotNull(x+1,y) && board.getPositions()[x+1][y]!=0 &&
                board.fieldNotNull(x+2,y) && board.getPositions()[x+2][y]==0 ) {
            if(!tempSet.add(new PiecePosition(y, x+2)))
                return;
            getMovesByOverJumping(board, tempSet,x+2, y);
        }
        if(board.fieldNotNull(x-1,y) && board.getPositions()[x-1][y] !=0 &&
                board.fieldNotNull(x-2,y) && board.getPositions()[x-2][y] ==0 ) {
            if(!tempSet.add(new PiecePosition(y, x - 2)))
                return;
            getMovesByOverJumping(board, tempSet,x-2, y);
        }
        if( board.fieldNotNull(x-1,y) && board.getPositions()[x-1][y] !=0 &&
                board.fieldNotNull(x-2,y) && board.getPositions()[x-2][y] ==0 ) {
            if(!tempSet.add(new PiecePosition(y, x-2)))
                return;
            getMovesByOverJumping(board, tempSet,x-2, y);
        }
        if(board.fieldNotNull(x-1,y+1) && board.getPositions()[x-1][y+1]!=0 &&
                board.fieldNotNull(x-1,y+2) && board.getPositions()[x-1][y+2] ==0 ) {
            if(!tempSet.add(new PiecePosition(y+2, x-1)))
                return;
            getMovesByOverJumping(board, tempSet, x-1, y+2);
        }
        if(board.fieldNotNull(x-1,y-1) && board.getPositions()[x-1][y-1]!=0 &&
                board.fieldNotNull(x-1,y-2) && board.getPositions()[x-1][y-2] ==0 ) {
            if(!tempSet.add(new PiecePosition(y-2, x-1)))
                return;
            getMovesByOverJumping(board, tempSet, x-1, y-2);
        }
        if( board.fieldNotNull(x,y-1) && board.fieldNotNull(x+1,y-2) &&
               board.getPositions()[x][y-1]!=0 && board.getPositions()[x+1][y-2] ==0 ) {
            if(!tempSet.add(new PiecePosition(y-2, x+1)))
                return;
            getMovesByOverJumping(board, tempSet, x+1, y-2);
        }


    }

    public void setId(int id) {
        this.id = id;
    }
}
