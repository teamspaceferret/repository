package spacetrader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        //Context.getInstance().getSoundManager().playBackgroundWithIntro("OpenInitial", "OpenLoop");
        if(Context.getInstance().getSoundManager().getBackgroundMusic("OpenLoop") != null){
            if(Context.getInstance().getSoundManager().getBackgroundMusic("OpenLoop").isPlaying()){
                
            } else {
                
                Context.getInstance().getSoundManager().playBackgroundMusic("OpenLoop");
            }
        } else {
            Context.getInstance().getSoundManager().loadBackgroundMusic("OpenInitial", "resources/OpenInitial.wav");
            Context.getInstance().getSoundManager().loadBackgroundMusic("OpenLoop", "resources/OpenLoop.wav");
            //Context.getInstance().getSoundManager().playBackgroundWithIntro("OpenInitial", "OpenLoop");
            Context.getInstance().getSoundManager().playBackgroundMusic("OpenLoop");
        }
        
        
        //loop isn't perfect transition. Fix that.
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
     * Transitions to the character creation screen.
     */
    public void newGameButtonAction() {
        controller.setScreen("CharacterCreation");
    }
    
    /**
     * Loads the saved game state and transitions to the appropriate screen.
     */
    public void loadGameButtonAction() {
        Context.getInstance().getUniverse().generateUniverse();
        Context.getInstance().getPlayer().setShip(new Ship("gnat"));
        Context.getInstance().getPlayer().setCurrentPlanet(
                Context.getInstance().getUniverse().getSolarSystems()[0].getPlanets()[0]);
        controller.setScreen("GalaxyMap");
    }
}
