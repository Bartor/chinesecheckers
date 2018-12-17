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
        this.setStyle("-fx-color: " + color);
    }

    public void setPosition(PiecePosition position) {
        this.position = position;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }
    public Piece getPiece() {
        return piece;
    }
    public PiecePosition getPosition() {
        return position;
    }
}
