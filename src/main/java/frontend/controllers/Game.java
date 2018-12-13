package frontend.controllers;

import com.jfoenix.controls.JFXButton;
import frontend.util.BoardField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.player.Piece;
import model.player.PiecePosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game extends AbstractController {
    List<BoardField> fields = new ArrayList<BoardField>();

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

    public void loadFields() {
        int[][] boardFields = game.getBoardMovementInterface().getBoard().getBoardFields();
        for (int i = 0; i < boardFields.length; i++) {
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            for (int j = 0; j < boardFields[i].length; j++) {
                RadioButton button = new RadioButton();
                button.setDisable(true);
                if (boardFields[i][j] == 0) {
                    hbox.getChildren().add(button);
                } else {
                    switch (boardFields[i][j]) {
                        case 1: {
                            button.setStyle("-fx-color: blue");
                            break;
                        }
                        case 2: {
                            button.setStyle("-fx-color: yellow;");
                            break;
                        }
                        case 3: {
                            button.setStyle("-fx-color: green;");
                            break;
                        }
                        case 4: {
                            button.setStyle("-fx-color: red;");
                            break;
                        }
                        case 5: {
                            button.setStyle("-fx-color: purple;");
                            break;
                        }
                        case 6: {
                            button.setStyle("-fx-color: orange;");
                            break;
                        }
                        default: {
                            button = null;
                            break;
                        }
                    }
                    if (button != null) hbox.getChildren().add(button);
                }
            }
            boardBox.getChildren().add(hbox);
        }
    }
}
