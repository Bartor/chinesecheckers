package frontend.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import frontend.util.ControllerNetworkFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import model.board.BasicBoard;
import model.board.BasicBoardMovement;
import model.game.BasicGame;
import model.player.Player;

public class Start extends AbstractController {
    @FXML
    JFXButton start;

    @FXML
    JFXTextField nick;

    @FXML
    JFXTextField adress;

    @Override
    public void onSwitch() {
    }

    @FXML
    public void initialize() {
        game = new BasicGame(new BasicBoardMovement(new BasicBoard()), 6);

        nick.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (nick.getText().trim().equals("") || adress.getText().trim().equals("")) {
                    start.setDisable(true);
                } else {
                    start.setDisable(false);
                }
            }
        });
        adress.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (nick.getText().trim().equals("") || adress.getText().trim().equals("")) {
                    start.setDisable(true);
                } else {
                    start.setDisable(false);
                }
            }
        });

        start.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    System.out.println("connecting to" + adress.getText());
                    ControllerNetworkFacade.connect(adress.getText(), nick.getText());
                } catch (Exception e) {
                    showAlert("Couldn't connect to host");
                    e.printStackTrace();
                }
                thisPlayer = new Player(nick.getText());
                try {
                    while (game.getBoardMovementInterface().getBoard().getPositions() == null) {
                        //fix this later
                        System.out.println("loading board...");
                        Thread.sleep(100);
                    }
                    sceneController.switchScene("pregame");
                } catch (Exception e) {
                    showAlert(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}
