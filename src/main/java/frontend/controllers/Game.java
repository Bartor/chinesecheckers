package frontend.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.board.BoardInterdace;

public class Game extends AbstractController {
    @FXML
    VBox boardBox;

    public void initialize() {
        int[][] boardFields = game.getBoardMovementInterface().getBoard().getBoardFields();
        for (int i = 0; i < boardFields.length; i++) {
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            for (int j  = 0; j < boardFields[i].length; j++) {
                RadioButton button = new RadioButton();
                if (boardFields[i][j] == 0) {
                    button.setDisable(true);
                    hbox.getChildren().add(button);
                } else {
                    switch (boardFields[i][j]) {
                        case 1: {
                            button.setStyle("-fx-selected-color: blue; -fx-unselected-color: blue; ");
                            break;
                        }
                        case 2: {
                            button.setStyle("-fx-selected-color: yellow; -fx-unselected-color: yellow; ");
                            break;
                        }
                        case 3: {
                            button.setStyle("-fx-selected-color: green; -fx-unselected-color: green; ");
                            break;
                        }
                        case 4: {
                            button.setStyle("-fx-selected-color: red; -fx-unselected-color: red; ");
                            break;
                        }
                        case 5: {
                            button.setStyle("-fx-selected-color: purple; -fx-unselected-color: purple; ");
                            break;
                        }
                        case 6: {
                            button.setStyle("-fx-selected-color: orange; -fx-unselected-color: orange; ");
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
        }
    }
}
