package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import spacetrader.SpaceTrader.ControlledScreen;

public class MarketController implements ControlledScreen, Initializable {
    @FXML private Button confirmButton;
    @FXML private Button backButton;
    @FXML private Label creditLabel;
    @FXML private Label cargoLabel;
    @FXML private TextField usersWater;
    @FXML private TextField tradersWater;
    
    
    ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    SolarSystem currentlySelected;
    
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
     * Sets the current available credits to be displayed
     */
    public void currentCredits() {
        creditLabel.setText("Credits: " + "1000"); //add current credits here!
    }
    
    /**
     * Sets the current available cargo space to be displayed
     */
    public void currentCargo() {
        cargoLabel.setText("Cargo Bays: " + "num" + "/" + "total"); //add current/total here!
    }
    
    public void waterIncrement() {
        //add to usersWater
        //dec tradersWater
        //dec user's credits by water price
        //dec cargo space
    }
    public void waterDecrement() {
        //dec usersWater
        //add to tradersWater
        //incr user's credits by water price
        //incr cargo space
    }
    
    public void confirmAction() {
        //do stuff
    }
    
    public void backAction() {
        //controller.setScreen("PlanetScreen");
    }
    
}
