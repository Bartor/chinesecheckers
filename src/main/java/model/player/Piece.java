package model.player;

import com.sun.istack.internal.NotNull;

/***
 * Represents a single piece.
 */
public class Piece {
    /***
     * Piece's position.
     */
    private PiecePosition position;
    /***
     * Id of piece's owner.
     */
    private int id;

    /***
     * Create a new piece with given position.
     * @param startingPosition Position it starts at.
     */
    public Piece(@NotNull PiecePosition startingPosition) {
        this.position = startingPosition;
    }

    /***
     * Gets piece's position.
     * @return
     */
    public PiecePosition getPosition() {
        return position;
    }

    /***
     * Sets pieces position.
     * @param position
     */
    public void setPosition(@NotNull PiecePosition position) {
        this.position = position;
    }

    /***
     * Sets id.
     * @param id Id to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /***
     * Gets id.
     * @return Id.
     */
    public int getId() {
        return this.id;
    }
}
