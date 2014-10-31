package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import spacetrader.SpaceTrader.ControlledScreen;

public class StartScreenController implements ControlledScreen, Initializable {
    ScreensController controller;
    @FXML private MenuItem optionsButton;
    
    SoundManager soundManager = SoundManager.getSoundManager();
    private boolean bgMuted = soundManager.getBGMuted();
    private boolean seMuted = soundManager.getBGMuted();
    
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
        seMuted = soundManager.getSEMuted();
        bgMuted = soundManager.getBGMuted();
        soundManager.setPrevScreen("StartScreen");
        
        optionsButton.setAccelerator(new KeyCodeCombination(KeyCode.ESCAPE));
        
        //loop isn't perfect transition. Fix that.
        soundManager.playBGWithCheck(SoundManager.STARTSCREENID, SoundManager.STARTSCREENPATH);     
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
        soundManager.playSEWithCheck(SoundManager.CLICKID,SoundManager.CLICKPATH);
        controller.setScreen("CharacterCreation");
    }
    
    /**
     * Loads the saved game state and transitions to the appropriate screen.
     */
    public void loadGameButtonAction() {
        soundManager.playSEWithCheck(SoundManager.CLICKID,SoundManager.CLICKPATH);
        Context.getInstance().loadContextBinary();
        controller.setScreen("PlanetScreen");
    }
    
    public void optionsActions(){
        controller.setScreen("OptionsScreen");
    }
}
