package frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChineseCheckersApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("views/start.fxml"));
            stage.setTitle("XD");
            stage.setScene(new Scene(root, 200, 200));
            stage.show();
        } catch (IOException e) { }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
