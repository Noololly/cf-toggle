package studio.noololly.cftoggle;

import javafx.application.Application;
import studio.noololly.cftoggle.gui.ApplicationMain;
import studio.noololly.cftoggle.backend.TerminalCommunicator;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Launcher {
    public static void main(String[] args){
        Application.launch(ApplicationMain.class, args);
    }
}
