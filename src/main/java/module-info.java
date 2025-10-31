module studio.noololly.cftoggle {
    requires javafx.controls;
    requires javafx.fxml;


    opens studio.noololly.cftoggle to javafx.fxml;
    exports studio.noololly.cftoggle;
}