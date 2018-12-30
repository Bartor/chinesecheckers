package frontend.controllers;

import frontend.util.ControllerNetworkFacade;
import frontend.util.SceneController;
import javafx.scene.control.Alert;
import model.game.AbstractGame;
import model.player.Player;

public abstract class AbstractController {
    protected static AbstractGame game;
    protected static Player thisPlayer;
    protected static int turn;
    protected SceneController sceneController;
    protected ControllerNetworkFacade controllerNetworkFacade;

    public void setSceneController(SceneController controller) {
        this.sceneController = controller;
    }

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setTitle("Error");
        alert.show();
    }
}
