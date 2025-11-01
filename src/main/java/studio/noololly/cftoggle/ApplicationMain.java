package studio.noololly.cftoggle;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
        toggle.setTranslateX(100);
        toggle.setTranslateY(100);

        Text text = new Text();
        text.setFont(Font.font(18));
        text.setFill(Color.WHITE);
        text.setTranslateX(100);
        text.setTranslateY(200);
        text.textProperty().bind(Bindings.when(toggle.switchedOnProperty()).then("ON").otherwise("OFF"));

        root.getChildren().addAll(bg, toggle, text);
        return root;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Cloudflare Toggle");
        stage.setScene(new Scene(createContent()));
        stage.show();
    }
}
