package studio.noololly.cftoggle.backend;

import javafx.beans.property.ReadOnlyBooleanProperty;
import studio.noololly.cftoggle.gui.ToggleSwitch;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class BackendMain {
    private final TerminalCommunicator comm = new TerminalCommunicator();
    private boolean toggleState = false;

    private void attachToggle(ToggleSwitch toggle) {
        ReadOnlyBooleanProperty prop = toggle.switchedOnProperty();

        prop.addListener((observable, oldValue, newValue) -> {
            toggleState = newValue;
        });

        toggleState = toggle.isSwitchedOn();
    }

    public BackendMain(ToggleSwitch toggle) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        attachToggle(toggle);
    }
}
