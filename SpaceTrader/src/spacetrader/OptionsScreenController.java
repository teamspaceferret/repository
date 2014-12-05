/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import spacetrader.SpaceTrader.ControlledScreen;

/**
 * FXML Controller class
 *
 * @author Cora
 */
public class OptionsScreenController implements ControlledScreen, Initializable {
    @FXML private MenuItem toggleBGButton;
    @FXML private MenuItem toggleSEButton;
    
    private ScreensController controller;
    private SoundManager soundManager = SoundManager.getSoundManager();
    private String currentBGID;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }

    @Override
    public final void initScreen() {
        System.out.println(soundManager.getCurrentBG());
        currentBGID = soundManager.getCurrentBG();
        //if null, then wat?
    }
    
    public void toggleBackgroundMusic(){
        if(!soundManager.getBGMuted()){
            soundManager.muteBackgroundMusic(currentBGID);
            toggleBGButton.setText("Play Background Music");
        } else {
            soundManager.unMuteBackgroundMusic(currentBGID);
            toggleBGButton.setText("Mute Background Music");
        }
        soundManager.playSEWithCheck(SoundManager.CLICKID, SoundManager.CLICKPATH);
    }
    
    public void toggleSoundEffects(){
        if(!soundManager.getSEMuted()){
            soundManager.muteSoundEffects();
            toggleSEButton.setText("Play Sound Effects");
        } else {
            soundManager.unMuteSoundEffects();
            toggleSEButton.setText("Mute Sound Effects");
        }
        soundManager.playSEWithCheck(SoundManager.CLICKID, SoundManager.CLICKPATH);
    }
    
    public void backAction(){
        soundManager.playSEWithCheck(SoundManager.CLICKID, SoundManager.CLICKPATH);
        controller.setScreen(soundManager.getPrevScreen());
    }
    
}
