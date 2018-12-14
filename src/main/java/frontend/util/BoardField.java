package frontend.util;

import javafx.scene.control.RadioButton;
import model.player.Piece;
import model.player.Player;

public class BoardField extends RadioButton {
    private Piece piece;
    private Player player;
    public BoardField(String color) {
        super();
        this.setStyle("-fx-color: " + color);
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
    public boolean isMoveable(Player player) {
        return player == this.player;
    }
}
