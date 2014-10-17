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
    
    SoundManager soundManager = Context.getInstance().getSoundManager();
    
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
        soundManager = Context.getInstance().getSoundManager();
        //Context.getInstance().getSoundManager().playBackgroundWithIntro("OpenInitial", "OpenLoop");
        if(soundManager.getBackgroundMusic("OpenLoop") != null){
            if(soundManager.getBackgroundMusic("OpenLoop").isPlaying()){
                
            } else {
                
                soundManager.playBackgroundMusic("OpenLoop");
            }
        } else {
            soundManager.loadBackgroundMusic("OpenInitial", "resources/OpenInitial.wav");
            soundManager.loadBackgroundMusic("OpenLoop", "resources/OpenLoop.wav");
            //Context.getInstance().getSoundManager().playBackgroundWithIntro("OpenInitial", "OpenLoop");
            soundManager.playBackgroundMusic("OpenLoop");
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
        Context.getInstance().loadContextBinary();
        controller.setScreen("PlanetScreen");
    }
    
    public void toggleBackgroundAction(){
        
    }
    
    public void toggleSoundEffectsAction(){
        
    }
    
    public void toggleAllMusicAction(){
        
    }
}
