package frontend.util;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LobbyUser {
    public static Node create(String nick, int id, boolean ready) {
        Text idText = new Text("ID: " + id);
        idText.setFont(Font.font("Consolas", 10));
        idText.setFill(Paint.valueOf("#dddddd"));

        Text nickText = new Text(nick);
        nickText.setFont(Font.font("Consolas", 12));
        nickText.setFill(Paint.valueOf("#dddddd"));

        Text readyText = new Text(ready ? "ready": "not ready");
        readyText.setFont(Font.font("Consolas", 10));
        readyText.setFill(Paint.valueOf("#dddddd"));

        readyText.setId(String.valueOf(id));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(idText, nickText, readyText);

        return vBox;
    }
}
