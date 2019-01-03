package model.player;

import model.exceptions.NoSuchPieceException;

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

    public Piece getPieceByPosition(PiecePosition position) throws NoSuchPieceException {
        for (Piece piece : this.pieces) {
            if (piece.getPosition().equals(position)) return piece;
        }
        throw new NoSuchPieceException("There is no such piece");
    }

    public void setId(int id) {
        this.id = id;
        for (Piece piece : this.pieces) {
            piece.setId(id);
        }
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
