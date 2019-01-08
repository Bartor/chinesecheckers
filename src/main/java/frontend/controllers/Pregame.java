package frontend.controllers;

import com.jfoenix.controls.JFXButton;
import frontend.networking.MessageInterpreter;
import frontend.util.ControllerNetworkFacade;
import frontend.util.LobbyUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Pregame extends AbstractController {
    @FXML
    private VBox playerList;

    @FXML
    JFXButton readyButton;

    @Override
    public void onSwitch() {
        MessageInterpreter.spawnFacade(this);
    }

    @FXML
    public void initialize() {
        MessageInterpreter.spawnFacade(this);
        readyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControllerNetworkFacade.ready();
            }
        });
    }

    public void addPlayer(String nick, int id) {
        Node playerBox = LobbyUser.create(nick, id, false);

        Platform.runLater(() -> {
            playerList.getChildren().add(playerBox);
        });
        //System.out.println("PLAYER ADDED!");
    }

    public void readyPlayer(int id) {
        Platform.runLater(() -> {
            try {
                ((Text) sceneController.getScene("pregame").lookup("#" + id)).setText("ready");
                if (id == thisPlayer.getId()) readyButton.setDisable(true);
            } catch (Exception e) {
                showAlert(e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public void startGame() {
        Platform.runLater(() -> {
            try {
                sceneController.switchScene("game");
            } catch (Exception e) {
                showAlert(e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
