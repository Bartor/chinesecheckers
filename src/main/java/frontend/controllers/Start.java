package frontend.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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

public class Start extends AbstractController {
    private File file = null;

    @FXML
    JFXButton upload;

    @FXML
    JFXButton start;

    @FXML
    JFXTextField nick;

    @FXML
    JFXTextField adress;

    @FXML
    Label map;

    @FXML
    public void initialize() {
        //todo add listeners
        nick.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (nick.getText().trim().equals("") || adress.getText().trim().equals("") || file == null) {
                    start.setDisable(true);
                } else {
                    start.setDisable(false);
                }
            }
        });
        adress.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (nick.getText().trim().equals("") || adress.getText().trim().equals("") || file == null) {
                    start.setDisable(true);
                } else {
                    start.setDisable(false);
                }
            }
        });
        start.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                for (int i = 0; i < 6; i++) {
                    Player player = new Player(nick.getText().trim());
                    //todo implement it properly
                    //net = new Network("a", 1);
                    //setGame(net.getGame());
                    int thisId = 0;
                    try {
                        thisId = game.addPlayer(player);
                        game.createArmy(player);
                    } catch (CannotAddPlayerException e) {
                        showAlert(e.getMessage());
                    }
                    //still to do
                    if (thisId != 0) {
                        try {
                            thisPlayer = game.getPlayerById(thisId);
                        } catch (NoSuchPlayerException e) {
                            e.printStackTrace();
                        }
                    }
                }
                game.setTurn(6);
                try {
                    sceneController.switchScene("game");
                } catch (Exception e) {
                    showAlert(e.getMessage());
                }
            }
        });
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
        });
    }
}
