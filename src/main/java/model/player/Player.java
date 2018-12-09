package model.player;

import model.board.BoardInterdace;
import model.exceptions.PlayerNotFullyInitializedException;

public class Player {
    private String name;
    private Army army;
    private int id;

    public Player(String name) {
        this.name = name;
    }

    public void setId(int id) throws PlayerNotFullyInitializedException {
        this.id = id;
        if (this.army != null) {
            this.army.setId(id);
        } else {
            throw new PlayerNotFullyInitializedException("Canno set id without an army");
        }
    }

    /***
     * Checks if player has won.
     * @return True if he did, false if not.
     */
    public boolean won(BoardInterdace board) {
        for(Piece piece : army.getPieces()){
            if(board.getBoardFields()[piece.getPosition().getRow()][piece.getPosition().getCol()]!=(id+3)%6)
                return false;
        }
        return true;
    }
}
