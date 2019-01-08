package frontend.controllers;

import com.jfoenix.controls.JFXButton;
import frontend.networking.MessageInterpreter;
import frontend.util.ControllerNetworkFacade;
import frontend.util.LobbyUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/***
 * Controls the lobby.
 */
public class Pregame extends AbstractController {
    /***
     * Players are listed here.
     */
    @FXML
    private VBox playerList;

    /***
     * Button used to declare yourself ready.
     */
    @FXML
    JFXButton readyButton;

    @Override
    public void onSwitch() {
        MessageInterpreter.spawnFacade(this);
    }

    /***
     * We add some listeners to the ready button.
     */
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

    /***
     * Adds a player to the ui.
     * @param nick Nickname of the player.
     * @param id Id of the player.
     */
    public void addPlayer(String nick, int id) {
        Node playerBox = LobbyUser.create(nick, id, false);

        Platform.runLater(() -> {
            playerList.getChildren().add(playerBox);
        });
    }

    /***
     * Readies a player in the ui.
     * @param id Id of a player to be readied.
     */
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

    /***
     * Switches to the game scene.
     */
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
