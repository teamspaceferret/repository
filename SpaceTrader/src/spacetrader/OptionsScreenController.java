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
    @FXML MenuItem toggleBGButton;
    @FXML MenuItem toggleSEButton;
    
    ScreensController controller;
    SoundManager soundManager;
    
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
    public void initScreen() {
        soundManager = SoundManager.getSoundManager();
    }
    
    public void toggleBackgroundMusic(){
        if(!soundManager.getBGMuted()){
            soundManager.muteBackgroundMusic(SoundManager.STARTSCREENID);
            toggleBGButton.setText("Play Background Music");
        } else {
            soundManager.unMuteBackgroundMusic(SoundManager.STARTSCREENID);
            toggleBGButton.setText("Mute Background Music");
        }
    }
    
    public void toggleSoundEffects(){
        if(!soundManager.getSEMuted()){
            soundManager.muteSoundEffects();
            toggleSEButton.setText("Play Sound Effects");
        } else {
            soundManager.unMuteSoundEffects();
            toggleSEButton.setText("Mute Sound Effects");
        }
    }
    
    public void backAction(){
        controller.setScreen(soundManager.getPrevScreen());
    }
    
}
