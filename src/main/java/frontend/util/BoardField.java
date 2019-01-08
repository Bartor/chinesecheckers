package frontend.util;

import javafx.scene.control.RadioButton;
import model.player.Piece;
import model.player.PiecePosition;

/***
 * Utility class, representing a single field on a board ui.
 */
public class BoardField extends RadioButton {
    /***
     * Piece on this field.
     */
    private Piece piece;
    /***
     * Position of this field.
     */
    private PiecePosition position;

    /***
     * Creates a new field with given color.
     * @param color Hex string of color (e.g. "#333" or "#0070ff").
     */
    public BoardField(String color) {
        super();
        setFocusTraversable(false);
        this.setStyle("-fx-color: " + color + ";" +
                "-fx-background-color: #353535;" +
                "-fx-highlight-fill: #444444;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;");
    }

    /***
     * Sets the position.
     * @param position The position to be set.
     */
    public void setPosition(PiecePosition position) {
        this.position = position;
    }

    /***
     * Sets the piece on this field.
     * @param piece The piece to be set.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /***
     * Gets the piece from this field.
     * @return Piece from this field.
     */
    public Piece getPiece() {
        return piece;
    }

    /***
     * Gets the position of this field.
     * @return Position of this field.
     */
    public PiecePosition getPosition() {
        return position;
    }
}
