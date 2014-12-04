package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import spacetrader.SpaceTrader.ControlledScreen;

public class EncounterController implements ControlledScreen, Initializable {
    ScreensController controller;
    private SoundManager soundManager = SoundManager.getSoundManager();
    
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
        soundManager.setPrevScreen("Encounter");
        soundManager.setCurrentBG(SoundManager.ENCOUNTER_BG_ID);
        soundManager.playBGWithCheck(SoundManager.ENCOUNTER_BG_ID, SoundManager.ENCOUNTER_BG_PATH);
    }
    
    /**
     * Initializes the controller class.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
