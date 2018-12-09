package model.player;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private List<Piece> pieces;
    private int id;

    public Army(PiecePosition[] startingPositions) {
        pieces = new ArrayList<Piece>();
        for (PiecePosition position : startingPositions) {
            pieces.add(new Piece(position));
        }
    }

    public void setId(int id) {
        this.id = id;
        for (Piece piece : this.pieces) {
            piece.setId(id);
        }
    }
}
