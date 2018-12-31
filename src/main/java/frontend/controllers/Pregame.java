package frontend.controllers;

import frontend.networking.MessageInterpreter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Pregame extends AbstractController {
    @FXML
    VBox playerList;

    @FXML
    public void initialize() {
        MessageInterpreter.spawnFacade(this);
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
        System.out.println("PLAYER ADDED!");
    }

    public void readyPlayer(int id) {
        Platform.runLater(() -> {
            try {
                ((Text) sceneController.getScene("pregame").lookup(String.valueOf(id))).setText("ready");
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
