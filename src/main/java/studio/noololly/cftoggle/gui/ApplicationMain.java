package studio.noololly.cftoggle.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import studio.noololly.cftoggle.backend.BackendMain;

public class ApplicationMain extends Application {

    @Override
    public void start(Stage stage) {
        AppUI ui = new AppUI();

        stage.setTitle("Cloudflare Toggle");
        stage.setScene(new Scene(ui.getRoot()));
        stage.show();

        try {
            BackendMain backend = new BackendMain(ui.getToggle());
        } catch (Exception e) {
            System.out.println("Oh something real bad happened!" + e);
        }
    }
}
