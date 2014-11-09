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
    
    private ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    Player player = Context.getInstance().getPlayer();
    
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
    public final void initScreen() {
        //display flavor text of the chosen event
        description.setText(randomEvent());
    }
    
    /**
     * Initializes the controller class.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(final URL location,
            final ResourceBundle resources) { }
    
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
