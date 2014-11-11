package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import spacetrader.SpaceTrader.ControlledScreen;

public class PlanetScreenController implements ControlledScreen, Initializable {
    @FXML private Button shipyardButton;
    @FXML private Label planetName;
    
    private ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    Player player = Context.getInstance().getPlayer();
    
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
    public final void initScreen() {
        universe = Context.getInstance().getUniverse();
        player = Context.getInstance().getPlayer();
        planetName.setText(player.getCurrentPlanet().getName().toUpperCase());
        if (player.getCurrentPlanet().getTechLevel() >= new Ship("flea").getMinTechLevel()) {
            shipyardButton.setDisable(false);
        } else {
            shipyardButton.setDisable(true);
        }
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
     * Transitions back to the solar system map screen.
     */
    public final void backAction() {
        controller.setScreen("SolarMap");
    }
    
    /**
     * Transitions to the market screen.
     */
    public final void marketAction() {
        controller.setScreen("Market");
    }
    
    /**
     * Transitions to the ship yard screen.
     */
    public final void shipyardAction() {
        controller.setScreen("Shipyard");
    }
    
    /**
     * Saves the game.
     */
    public final void saveAction(){
        Context.getInstance().saveContextBinary();
    }
}
