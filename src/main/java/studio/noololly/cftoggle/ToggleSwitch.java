package studio.noololly.cftoggle;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ToggleSwitch extends Parent {

    private final BooleanProperty switchedOn = new SimpleBooleanProperty(false);

    private final TranslateTransition translationAnimation = new TranslateTransition(Duration.seconds(0.25));
    private final FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));

    private final ParallelTransition animation = new ParallelTransition(translationAnimation, fillAnimation);

    public BooleanProperty switchedOnProperty() {
        return switchedOn;
    }

    public ToggleSwitch() {
        Rectangle background = new Rectangle(100, 50);
        background.setArcWidth(50);
        background.setArcHeight(50);
        background.setFill(Color.WHITE);
        background.setStroke(Color.LIGHTGRAY);

        Circle trigger = new Circle(25);
        trigger.setCenterX(25);
        trigger.setCenterY(25);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(Color.LIGHTGRAY);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(2);
        trigger.setEffect(shadow);

        translationAnimation.setNode(trigger);
        fillAnimation.setShape(background);

        getChildren().addAll(background, trigger);

        switchedOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState;
            translationAnimation.setToX(isOn ? 100 - 50 : 0);
            fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
            fillAnimation.setToValue(isOn ? Color.LIGHTGREEN : Color.WHITE);

            animation.play();
        });

        setOnMouseClicked(event -> switchedOn.set(!switchedOn.get()));

    }
}
