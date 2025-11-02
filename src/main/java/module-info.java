module studio.noololly.cftoggle {
    requires javafx.controls;
    requires javafx.fxml;

    exports studio.noololly.cftoggle.gui;
    opens studio.noololly.cftoggle.gui to javafx.fxml;
    exports studio.noololly.cftoggle;
    opens studio.noololly.cftoggle to javafx.fxml;
}