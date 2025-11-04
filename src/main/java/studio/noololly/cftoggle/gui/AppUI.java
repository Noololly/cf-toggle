package studio.noololly.cftoggle.gui;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AppUI {
    private final Pane root;
    protected final ToggleSwitch toggle;
    private final Text statusText;

    public AppUI() {
        root = new Pane();
        root.setPrefSize(300, 300);

        Rectangle bg = new Rectangle(300, 300);
        bg.setFill(Color.BLACK);

        toggle = new ToggleSwitch();
        toggle.setLayoutX(100.5);
        toggle.setLayoutY(137.5);

        statusText = new Text();
        statusText.setFont(Font.font(24));
        statusText.setFill(Color.WHITE);
        statusText.textProperty().bind(Bindings.when(toggle.switchedOnProperty()).then("ON").otherwise("OFF"));
        statusText.fillProperty().bind(Bindings.when(toggle.switchedOnProperty()).then(Color.web("#D46300")).otherwise(Color.WHITE));
        statusText.setY(88);

        Runnable updateX = () -> {
            double width = root.getWidth();
            double textWidth = statusText.getBoundsInLocal().getWidth();
            statusText.setX((width - textWidth) / 2.0);
        };

        /* Resizing goop to keep text centred */
        root.widthProperty().addListener((obs, oldV, newV) -> Platform.runLater(updateX));
        statusText.textProperty().addListener((obs, oldV, newV) -> Platform.runLater(updateX));
        statusText.fontProperty().addListener((obs, oldV, newV) -> Platform.runLater(updateX));
        root.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                Platform.runLater(updateX);
            }
        });

        root.getChildren().addAll(bg, statusText, toggle);
    }

    public Parent getRoot() {
        return root;
    }

    public ToggleSwitch getToggle() {
        return toggle;
    }

}
