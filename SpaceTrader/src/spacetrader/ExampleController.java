package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import spacetrader.SpaceTrader.ControlledScreen;

// NOTE: Don't forget to add entry to SpaceTrader.java!!!

public class ExampleController implements ControlledScreen, Initializable {
    @FXML private Label exampleLabel;
    
    private ScreensController controller;
    
    /**
     * Set the screen parent.
     * @param screenParent the screen parent
     */
    @Override
    public final void setScreenParent(final ScreensController screenParent) {
        controller = screenParent;
    }
    
    /**
     * Initializes the screen.
     */
    @Override
    public final void initScreen() { }
    
    /**
     * Initializes the controller class.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(final URL location,
            final ResourceBundle resources) { }
    
    /**
     * Print example message to console.
     */
    public final void exampleButtonAction() {
        exampleLabel.setText("Button clicked!");
    }
    
    /**
     * Transition to the start screen.
     */
    public final void nextButtonAction() {
        controller.setScreen("StartScreen");
    }
}
