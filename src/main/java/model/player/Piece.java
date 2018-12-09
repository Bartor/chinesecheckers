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

    public PiecePosition getPosition() {
        return position;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
