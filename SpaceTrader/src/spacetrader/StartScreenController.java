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
    @FXML private MenuItem optionsButton;
    
    private ScreensController controller;
    private SoundManager soundManager = SoundManager.getSoundManager();
    private boolean bgMuted = soundManager.getBGMuted();
    private boolean seMuted = soundManager.getBGMuted();

    

    
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
    public void initScreen() {
        seMuted = soundManager.getSEMuted();
        bgMuted = soundManager.getBGMuted();
        soundManager.setPrevScreen("StartScreen");
        
        //try changing all vers of options screen control to check current screen and toggeling
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
    public void initialize(final URL location,
            final ResourceBundle resources) { }
    
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
