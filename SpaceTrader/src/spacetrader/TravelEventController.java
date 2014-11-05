package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import spacetrader.SpaceTrader.ControlledScreen;

public class TravelEventController implements ControlledScreen, Initializable {
    @FXML private TextArea description;
    
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
        //display flavor text of the chosen event
        description.setText(randomEvent());
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
     * Selects a random event to happen; effects applied in randomTravelEvent()
     * @return the text output by what occurred during the event
     */
    public String randomEvent() {
        return TravelEvent.NONE.randomTravelEvent();
    }
    
    /**
     * Continues to the planet.
     */
    public void continueAction() {
        controller.setScreen("PlanetScreen");
    }
    
}
