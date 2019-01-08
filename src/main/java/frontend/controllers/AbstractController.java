package frontend.controllers;

import frontend.util.SceneController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import model.game.AbstractGame;
import model.player.Player;

/***
 * An abstract base for all other controllers.
 */
public abstract class AbstractController {
    /***
     * Current game.
     */
    protected static AbstractGame game;
    /***
     * This ui's player.
     */
    protected static Player thisPlayer;
    /***
     * Scene controller used to switch scenes.
     */
    protected SceneController sceneController;

    /***
     * We have to reference the same scene controller to all our controller.s
     * @param controller A main scene controller.
     */
    public void setSceneController(SceneController controller) {
        this.sceneController = controller;
    }

    /***
     * Shows an alert.
     * @param message Alert's message.
     */
    public void showAlert(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(message);
            alert.setTitle("Error");
            alert.show();
        });
    }

    /***
     * What happens in current scene when we switch to it.
     */
    public abstract void onSwitch();

    /***
     * Gets the game.
     * @return The game.
     */
    public static AbstractGame getGame() {
        return game;
    }

    /***
     * Gets this ui's player.
     * @return The player.
     */
    public static Player getThisPlayer() {
        return thisPlayer;
    }
}
