package model.player;

import model.exceptions.NoSuchPieceException;

import java.util.ArrayList;
import java.util.List;

/***
 * Holds all of a player's pieces.
 */
public class Army {
    /***
     * Pieces list.
     */
    private List<Piece> pieces;
    /***
     * Id of a player holding this army.
     */
    private int id;

    /***
     * Create a new army on given positions.
     * @param startingPositions Position to start at.
     */
    public Army(PiecePosition[] startingPositions) {
        pieces = new ArrayList<Piece>();
        for (PiecePosition position : startingPositions) {
            pieces.add(new Piece(position));
        }
    }

    /***
     * Fetch a piece on a position.
     * @param position Position we're looking for.
     * @return Reference to piece on this position, if it exists.
     * @throws NoSuchPieceException If there is no piece in this army on that position.
     */
    public Piece getPieceByPosition(PiecePosition position) throws NoSuchPieceException {
        for (Piece piece : this.pieces) {
            if (piece.getPosition().equals(position)) return piece;
        }
        throw new NoSuchPieceException("There is no such piece");
    }

    /***
     * Sets ids of all pieces in this army.
     * @param id Id to be set.
     */
    public void setId(int id) {
        this.id = id;
        for (Piece piece : this.pieces) {
            piece.setId(id);
        }
    }

    /***
     * Returns all the pieces.
     * @return Pieces.
     */
    public List<Piece> getPieces() {
        return pieces;
    }
}
