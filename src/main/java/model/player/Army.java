package model.player;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private List<Piece> pieces;

    public Army(PiecePosition[] startingPositions) {
        pieces = new ArrayList<Piece>();
        for (PiecePosition position : startingPositions) {
            pieces.add(new Piece(position));
        }
    }
}
