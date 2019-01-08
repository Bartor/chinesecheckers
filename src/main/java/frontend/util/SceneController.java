package frontend.util;

import com.sun.istack.internal.NotNull;
import frontend.controllers.AbstractController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.util.HashMap;

/***
 * Utility class used to switch scenes.
 */
public class SceneController {
    /***
     * Main stage of the app.
     */
    private Stage stage;
    /***
     * A set of string - (scene, controller) pairs.
     */
    private HashMap<String, Pair<Scene, AbstractController>> scenes = new HashMap<>();

    /***
     * Creates a new scene controller.
     * @param stage Main app stage.
     */
    public SceneController(Stage stage) {
        this.stage = stage;
    }

    /***
     * Adds a new scene to the controller.
     * @param name Name which the scene is to be identified with.
     * @param scene The scene.
     * @param controller The scene controller.
     */
    public void addScene(String name, @NotNull Scene scene, @NotNull AbstractController controller) {
        this.scenes.put(name, new Pair<>(scene, controller));
    }

    /***
     * Changes current scene.
     * @param name Name used to identify the scene in {@link SceneController#scenes}.
     * @throws Exception If there is no screen with given name.
     */
    public void switchScene(String name) throws Exception {
        if (this.scenes.containsKey(name)) {
            this.stage.setScene(this.scenes.get(name).getKey());
            this.scenes.get(name).getValue().onSwitch();
        } else {
            throw new Exception("Scene does not exist");
        }
    }

    /***
     * Returns a particular scene.
     * @param name Name of searched scene.
     * @return Scene identified by the name.
     * @throws Exception If there is no screen with given name.
     */
    public Scene getScene(String name) throws Exception {
        if (this.scenes.containsKey(name)) return this.scenes.get(name).getKey();
        else throw new Exception("There is no such scene");
    }
}
