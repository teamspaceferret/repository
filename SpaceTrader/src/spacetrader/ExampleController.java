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
    
    ScreensController controller;
    
    /**
     * Set the screen parent.
     * @param screenParent the screen parent
     */
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
    /**
     * Initializes the screen.
     */
    @Override
    public void initScreen() {
        
    }
    
    /**
     * Initializes the controller class.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     * Print example message to console.
     */
    public void exampleButtonAction() {
        exampleLabel.setText("Button clicked!");
    }
    
    /**
     * Transition to the start screen.
     */
    public void nextButtonAction() {
        controller.setScreen("StartScreen");
    }
}
