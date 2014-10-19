package spacetrader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.media.AudioClip;
import spacetrader.SpaceTrader.ControlledScreen;

public class StartScreenController implements ControlledScreen, Initializable {
    ScreensController controller;
    @FXML private MenuItem toggleBG;
    @FXML private MenuItem toggleSE;
    @FXML private MenuItem toggleAll;
    
    SoundManager soundManager = Context.getInstance().getSoundManager();
    private AudioClip currentBG;
    private boolean bgMuted = soundManager.getBGMuted();
    private boolean seMuted = soundManager.getBGMuted();
    private String bgId = "OpenLoop";
    
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
            
        //Context.getInstance().getSoundManager().playBackgroundWithIntro("OpenInitial", "OpenLoop");
        /*
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
        */
        soundManager.playBGWithCheck("OpenLoop", "resources/OpenLoop.wav");
        currentBG = soundManager.getBackgroundMusic("OpenLoop");
        
        
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
        //toggleBG.setText("Mute Background Music");
        //soundManager.getbgMuted
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
        //soundManager.getSEMuted
        if(!seMuted){
            soundManager.muteSoundEffects();
            toggleSE.setText("Play Sound Effects");
        } else {
            soundManager.unMuteSoundEffects();
            toggleSE.setText("Mute Sound Effects");
        }
        
        seMuted = !seMuted;
    }
    
    public void toggleAllMusicAction(){
        
    }
}
