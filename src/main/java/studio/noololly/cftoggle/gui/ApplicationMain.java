package studio.noololly.cftoggle.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;


public class ApplicationMain extends Application {

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(300, 300);

        Rectangle bg = new Rectangle(300, 300);
        bg.setFill(Color.BLACK);

        ToggleSwitch toggle = new ToggleSwitch();
        toggle.setLayoutX(100.5);
        toggle.setLayoutY(137.5);

        Text text = new Text();
        text.setFont(Font.font(24));
        text.setFill(Color.WHITE);
        text.textProperty().bind(Bindings.when(toggle.switchedOnProperty()).then("ON").otherwise("OFF")); //TODO make this reflect system status. Binding to a status variable??
        text.fillProperty().bind(Bindings.when(toggle.switchedOnProperty()).then(Color.web("#D46300")).otherwise(Color.WHITE));
        text.setY(88);

        Runnable updateX = () -> {
            double width = root.getWidth();
            double textWidth = text.getBoundsInLocal().getWidth();
            text.setX((width - textWidth) / 2.0);
        };

        /* Resizing goop to keep text centred */
        root.widthProperty().addListener((obs, oldV, newV) -> Platform.runLater(updateX));
        text.textProperty().addListener((obs, oldV, newV) -> Platform.runLater(updateX));
        text.fontProperty().addListener((obs, oldV, newV) -> Platform.runLater(updateX));

        root.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                Platform.runLater(updateX);
            }
        });

        root.getChildren().addAll(bg, text, toggle);

        return root;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Cloudflare Toggle");
        stage.setScene(new Scene(createContent()));
        stage.show();
    }
}
