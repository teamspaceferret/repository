package spacetrader;

import spacetrader.SpaceTrader.ControlledScreen;

public class CharacterCreationController implements ControlledScreen {
    
    ScreensController controller;
    
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
}
