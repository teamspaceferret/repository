package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import spacetrader.SpaceTrader.ControlledScreen;

public class StartScreenController implements ControlledScreen, Initializable {
    ScreensController controller;
    
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
     * Transition the screen from the start screen to the character creation screen.
     */
    public void newGameButtonAction() {
        controller.setScreen("CharacterCreation");
    }
    
    /**
     * Transition to the saved games screen in order to load a chosen game.
     */
    public void loadGameButtonAction() {
        Context.getInstance().getUniverse().generateUniverse();
        //System.out.println(Context.getInstance().getUniverse());
        Context.getInstance().getPlayer().setShip(new Ship("gnat"));
        //Set current location default
        Context.getInstance().getPlayer().setCurrentPlanet(
                Context.getInstance().getUniverse().getSolarSystems()[0].getPlanets()[0]);
        controller.setScreen("GalaxyMap");
    }
}
