package frontend.util;

import javafx.scene.control.RadioButton;
import model.player.Piece;
import model.player.Player;

public class BoardField extends RadioButton {
    private Piece piece;
    private Player player;
    public BoardField(Piece piece, String color) {
        super();
        this.piece = piece;
        this.setStyle("-fx-color: " + color);
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
