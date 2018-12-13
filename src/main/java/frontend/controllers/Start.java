package frontend.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class Start extends AbstractController {
    @FXML
    JFXButton upload;

    @FXML
    JFXButton start;

    @FXML
    JFXTextField nick;

    @FXML
    public void initialize() {
        //todo add listeners
        nick.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (nick.getText().trim().equals("")) {
                    start.setDisable(true);
                } else {
                    start.setDisable(false);
                }
            }
        });
        start.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

            }
        });
    }
}
