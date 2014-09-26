package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import spacetrader.SpaceTrader.ControlledScreen;

public class PlanetScreenController implements ControlledScreen, Initializable {
    @FXML private Button marketButton;
    @FXML private Button backButton;
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
    
    public void marketAction() {
        controller.setScreen("Market");
    }
    
    public void backAction() {
        controller.setScreen("SolarMap");
    }
}
