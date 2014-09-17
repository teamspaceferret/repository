package spacetrader;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import spacetrader.SpaceTrader.ControlledScreen;

// NOTE: Don't forget to add entry to SpaceTrader.java!!!

public class ExampleController implements ControlledScreen {
    @FXML private Label exampleLabel;
    
    ScreensController controller;
    
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
    public void exampleButtonAction() {
        exampleLabel.setText("Button clicked!");
    }
    
    public void nextButtonAction() {
        controller.setScreen("StartScreen");
    }
}
