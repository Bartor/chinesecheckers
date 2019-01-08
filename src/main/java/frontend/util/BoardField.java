package frontend.util;

import javafx.scene.control.RadioButton;
import model.player.Piece;
import model.player.PiecePosition;
import model.player.Player;

public class BoardField extends RadioButton {
    private Piece piece;
    private Player player;
    private PiecePosition position;
    public BoardField(String color) {
        super();
        setFocusTraversable(false);
        this.setStyle("-fx-color: " + color + ";" +
                "-fx-background-color: #353535;" +
                "-fx-highlight-fill: #444444;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;");
    }

    public void setPosition(PiecePosition position) {
        this.position = position;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public Piece getPiece() {
        return piece;
    }
    public PiecePosition getPosition() {
        return position;
    }
}
