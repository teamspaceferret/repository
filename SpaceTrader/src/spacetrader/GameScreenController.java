package spacetrader;

import spacetrader.SpaceTrader.ControlledScreen;

public class GameScreenController implements ControlledScreen {
    
    ScreensController controller;
    
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

}
