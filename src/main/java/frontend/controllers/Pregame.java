package frontend.controllers;

import com.jfoenix.controls.JFXButton;
import frontend.networking.MessageInterpreter;
import frontend.util.ControllerNetworkFacade;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Pregame extends AbstractController {
    @FXML
    private VBox playerList;

    @FXML
    JFXButton readyButton;

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
        VBox playerBox = new VBox();
        playerBox.setAlignment(Pos.CENTER);

        HBox nickIdBox = new HBox();

        Text playerNick = new Text(nick);
        Text playerId = new Text(String.valueOf(id));
        playerId.setStyle("-fx-font-size: 10px");

        nickIdBox.getChildren().add(playerNick);
        nickIdBox.getChildren().add(playerId);

        Text readyState = new Text("not ready");
        readyState.setId(String.valueOf(id));

        playerBox.getChildren().add(nickIdBox);
        playerBox.getChildren().add(readyState);

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
