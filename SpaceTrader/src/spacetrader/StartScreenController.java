package spacetrader;

import spacetrader.SpaceTrader.ControlledScreen;

public class StartScreenController implements ControlledScreen {
    
    ScreensController controller;
    
    /**
     *
     * @param screenParent
     */
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
    public void newGameButtonAction() {
        controller.setScreen("CharacterCreation");
    }
    
    public void loadGameButtonAction() {
        System.out.println("Load Game button pressed");
    }   

}
