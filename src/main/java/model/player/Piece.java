package model.player;


import model.board.BasicBoard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Piece {
    private PiecePosition position;

    public Piece(PiecePosition startingPosition) {
        this.position = startingPosition;
    }

    public PiecePosition getPosition() {
        return position;
    }
}
