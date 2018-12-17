package frontend.controllers;

import com.jfoenix.controls.JFXButton;
import frontend.util.BoardField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.player.Piece;
import model.player.PiecePosition;
import model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game extends AbstractController {
    private List<BoardField> fields = new ArrayList<BoardField>();

    @FXML
    VBox boardBox;

    @FXML
    Label player;

    @FXML
    JFXButton map;

    @FXML
    public void initialize() {
        map.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Loading fields");
                loadFields();
            }
        });
    }

    private void loadFields() {
        int[][] boardFields = game.getBoardMovementInterface().getBoard().getBoardFields();
        for (int i = 0; i < boardFields.length; i++) {
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            for (int j = 0; j < boardFields[i].length; j++) {
                final BoardField button = new BoardField("white");
                button.setDisable(true);
                if (boardFields[i][j] == 0) {
                    button.setPosition(new PiecePosition(i, j));
                    hbox.getChildren().add(button);
                    fields.add(button);
                } else {
                    PiecePosition pos = new PiecePosition(i, j);
                    boolean n = false;
                    switch (boardFields[i][j]) {
                        case 1: {
                            Player tempPlayer = game.getPlayerById(1);
                            button.setPlayer(tempPlayer);
                            button.setPosition(pos);
                            button.setPiece(tempPlayer.getArmy().getPieceByPosition(pos));
                            button.setStyle("-fx-color: blue");
                            break;
                        }
                        case 2: {
                            Player tempPlayer = game.getPlayerById(2);
                            button.setPlayer(tempPlayer);
                            button.setPosition(pos);
                            button.setPiece(tempPlayer.getArmy().getPieceByPosition(pos));
                            button.setStyle("-fx-color: yellow;");
                            break;
                        }
                        case 3: {
                            Player tempPlayer = game.getPlayerById(3);
                            button.setPlayer(tempPlayer);
                            button.setPosition(pos);
                            button.setPiece(tempPlayer.getArmy().getPieceByPosition(pos));
                            button.setStyle("-fx-color: green;");
                            break;
                        }
                        case 4: {
                            Player tempPlayer = game.getPlayerById(4);
                            button.setPlayer(tempPlayer);
                            button.setPosition(pos);
                            button.setPiece(tempPlayer.getArmy().getPieceByPosition(pos));
                            button.setStyle("-fx-color: red;");
                            break;
                        }
                        case 5: {
                            Player tempPlayer = game.getPlayerById(5);
                            button.setPlayer(tempPlayer);
                            button.setPosition(pos);
                            button.setPiece(tempPlayer.getArmy().getPieceByPosition(pos));
                            button.setStyle("-fx-color: purple;");
                            break;
                        }
                        case 6: {
                            Player tempPlayer = game.getPlayerById(6);
                            button.setPlayer(tempPlayer);
                            button.setPosition(pos);
                            button.setPiece(tempPlayer.getArmy().getPieceByPosition(pos));
                            button.setStyle("-fx-color: orange;");
                            break;
                        }
                        default: {
                            n = true;
                            break;
                        }
                    }
                    if (!n) {
                        button.setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                move(button);
                            }
                        });
                        fields.add(button);
                        hbox.getChildren().add(button);
                    }
                }
            }
            boardBox.getChildren().add(hbox);
        }
        nextTurn();
    }

    private void nextTurn() {
        if (game.getTurn() == thisPlayer.getId()) {
            for (BoardField field : fields) {
                if (field.getPlayer() == thisPlayer) {
                    field.setDisable(false);
                }
            }
        }
    }

    private void move(BoardField field) {
        if (game.getTurn() == thisPlayer.getId()) {
            PiecePosition[] moves = game.getBoardMovementInterface().getMoves(field.getPiece());
            System.out.println("Printing possible moves:");
            for (PiecePosition move : moves) {
                System.out.println("Move: " + move);
                for (BoardField boardField : fields) {
                    if (boardField.getPosition().getCol() == move.getCol() && boardField.getPosition().getRow() == move.getRow()) {
                        boardField.setDisable(false);
                    } else {
                        boardField.setDisable(true);
                    }
                }
            }
        }
    }
}
