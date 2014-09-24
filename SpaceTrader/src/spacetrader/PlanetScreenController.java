package spacetrader;

import javafx.fxml.FXML;
import spacetrader.SpaceTrader.ControlledScreen;

public class PlanetScreenController implements ControlledScreen {
    
    ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    SolarSystem currentlySelected;
    
    /**
     * 
     * @param screenParent
     */
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
    
}
