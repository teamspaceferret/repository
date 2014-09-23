package spacetrader;

import spacetrader.SpaceTrader.ControlledScreen;

public class StartScreenController implements ControlledScreen {
    
    ScreensController controller;
    
    /**
     * Sets the screen parent
     * @param screenParent the screen parent
     */
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
    /**
     * Transitions the screen from the start screen to the character creation screen.
     */
    public void newGameButtonAction() {
        controller.setScreen("CharacterCreation");
    }
    
    /**
     * Transitions to the saved games screen in order to load a chosen game.
     */
    public void loadGameButtonAction() {
        Context.getInstance().getUniverse().generateUniverse();
        controller.setScreen("GameScreen");
    }
}
