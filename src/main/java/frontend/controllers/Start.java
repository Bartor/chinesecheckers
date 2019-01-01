package frontend.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import frontend.util.ControllerNetworkFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import model.board.BasicBoard;
import model.board.BasicBoardMovement;
import model.board.BoardInterdace;
import model.board.BoardMovementInterface;
import model.exceptions.CannotAddPlayerException;
import model.exceptions.CorruptedFileException;
import model.exceptions.NoSuchPlayerException;
import model.game.BasicGame;
import model.player.Player;

import java.io.File;
import java.io.IOException;

public class Start extends AbstractController {
    @FXML
    JFXButton start;

    @FXML
    JFXTextField nick;

    @FXML
    JFXTextField adress;

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

        /* we won't need it really... just program the server properly now
        upload.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                FileChooser chooser = new FileChooser();
                File openedFile = chooser.showOpenDialog(sceneController.getStage());
                if (openedFile != null) {
                    file = openedFile;
                    BoardInterdace board = new BasicBoard();
                    try {
                        board.loadBoard(file);
                    } catch (CorruptedFileException e) {
                        file = null;
                        showAlert(e.getMessage());
                        return;
                    }
                    map.setText("Aktualnie używana mapa: " + file.getName());
                    BoardMovementInterface movementInterface = new BasicBoardMovement(board);
                    game = new BasicGame(movementInterface, 6);
                }
            }
        });*/
    }
}
