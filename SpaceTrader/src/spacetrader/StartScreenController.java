package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import spacetrader.SpaceTrader.ControlledScreen;

public class StartScreenController implements ControlledScreen, Initializable {
    ScreensController controller;
    @FXML private MenuItem toggleBG;
    @FXML private MenuItem toggleSE;
    
    SoundManager soundManager = SoundManager.getSoundManager();
    private boolean bgMuted = soundManager.getBGMuted();
    private boolean seMuted = soundManager.getBGMuted();
    private String bgId = "OpenInitial";
    
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
        if(seMuted){
            toggleSE.setText("Play Sound Effects");
        } else {
            toggleSE.setText("Mute Sound Effects");
        }
        
        if(bgMuted){
            toggleBG.setText("Play Background Music");
        } else {
            toggleBG.setText("Mute Background Music");
        }
        
        //loop isn't perfect transition. Fix that.
        soundManager.playBGWithCheck2("OpenInitial", "resources/OpenInitial.wav");
        soundManager.loadSoundEffect("Click", "resources/StepFast-2.wav");
        
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
        soundManager.playSoundEffect("Click");
        controller.setScreen("CharacterCreation");
    }
    
    /**
     * Loads the saved game state and transitions to the appropriate screen.
     */
    public void loadGameButtonAction() {
        soundManager.playSoundEffect("Click");
        Context.getInstance().loadContextBinary();
        controller.setScreen("PlanetScreen");
    }
    
    public void toggleBackgroundAction(){
        if(!bgMuted){
            soundManager.muteBackgroundMusic(bgId);
            toggleBG.setText("Play Background Music");
        } else {
            soundManager.unMuteBackgroundMusic(bgId);
            toggleBG.setText("Mute Background Music");
        }
        
        bgMuted = !bgMuted;
    }
    
    public void toggleSoundEffectsAction(){
        if(!seMuted){
            soundManager.muteSoundEffects();
            toggleSE.setText("Play Sound Effects");
        } else {
            soundManager.unMuteSoundEffects();
            toggleSE.setText("Mute Sound Effects");
        }
        
        seMuted = !seMuted;
    }
}
