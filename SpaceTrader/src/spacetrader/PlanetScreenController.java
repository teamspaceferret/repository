package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import spacetrader.SpaceTrader.ControlledScreen;

public class PlanetScreenController implements ControlledScreen, Initializable {
    @FXML private Button backButton, marketButton, saveButton, shipyardButton;
    @FXML private Label planetName;
    
    ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    Player player = Context.getInstance().getPlayer();
    
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
        universe = Context.getInstance().getUniverse();
        player = Context.getInstance().getPlayer();
        planetName.setText(player.getCurrentPlanet().getName().toUpperCase());
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
     * Transitions back to the solar system map screen.
     */
    public void backAction() {
        controller.setScreen("SolarMap");
    }
    
    /**
     * Transitions to the market screen.
     */
    public void marketAction() {
        controller.setScreen("Market");
    }
    
    /**
     * Transitions to the ship yard screen.
     */
    public void shipyardAction() {
        //controller.setScreen("Shipyard");
    }
    
    public void saveAction(){
        Context.getInstance().saveContextBinary();
    }
}
