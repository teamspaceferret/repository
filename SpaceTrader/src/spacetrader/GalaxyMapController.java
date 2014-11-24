package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import spacetrader.SpaceTrader.ControlledScreen;

public class GalaxyMapController implements ControlledScreen, Initializable {
    @FXML private Canvas canvas;
    @FXML private TextArea description;
    //@FXML private MenuItem optionsButton;

    Universe universe = Context.getInstance().getUniverse();
    private SoundManager soundManager = SoundManager.getSoundManager();

    private ScreensController controller;
    private Player player;

    
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
        soundManager.setPrevScreen("GalaxyMap");
        //optionsButton.setAccelerator(new KeyCodeCombination(KeyCode.ESCAPE));
        universe = Context.getInstance().getUniverse();
        
        player = Context.getInstance().getPlayer();
        soundManager.setVolumeBG(.1);
        soundManager.playBGWithCheck("ComputerBeep", "resources/ComputerBeep.wav");
        drawSolarSystems();
        SolarSystem ss = player.getCurrentPlanet().getParentSolarSystem();
        setDescription(ss);
        Context.getInstance().setFocus(ss);
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
     * Draws current solar systems on galaxy map
     */
    public void drawSolarSystems() {
        Boolean isClose;
        Coordinate coords;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        SolarSystem parentSolarSystem;
        SolarSystem[] solarSystems;
        
        // Clear canvas
        gc.clearRect(0, 0, Context.BOUNDARY_VISIBLE, Context.BOUNDARY_VISIBLE);
        
        solarSystems = Context.getInstance().getUniverse().getSolarSystems();
        
        for (SolarSystem solarSystem : solarSystems) {
            isClose = false;
            for (Planet planet : solarSystem.getPlanets()) {
                coords = player.getCurrentPlanet().getCoords();
                
                if (coords.distanceTo(planet.getCoords())
                    < Context.DISTANCE_TO_FUEL_RATIO
                        * player.getShip().getFuelLevel()) {
                    isClose = true;
                }
            }
            
            if (isClose) {
                gc.setFill(Color.GREEN);
            } else {
                gc.setFill(Color.RED);
            }
            
            gc.fillOval(solarSystem.getCoords().getX(),
                    solarSystem.getCoords().getY(), Context.DOT_SIZE,
                    Context.DOT_SIZE);
        }
        
        // Draw current system in gold
        parentSolarSystem = player.getCurrentPlanet().getParentSolarSystem();
        
        gc.setFill(Color.GOLD);
        gc.fillOval(parentSolarSystem.getCoords().getX(), 
            parentSolarSystem.getCoords().getY(),
            Context.DOT_SIZE, Context.DOT_SIZE);
        
        // Draw range circle
        gc.setStroke(Color.BLACK);
        gc.strokeOval(player.getCurrentPlanet().getCoords().getX()
                - player.getShip().getRange() / 2 + 5,
                player.getCurrentPlanet().getCoords().getY()
                        - player.getShip().getRange() / 2 + 5,
                player.getShip().getRange(),
                player.getShip().getRange());
    }
    
    /**
     * Checks if the user clicked a solar system.
     * @param event click event
     */
    public final void onMouseClick(final MouseEvent event) {
        SolarSystem[] solarSystems;
        
        solarSystems = Context.getInstance().getUniverse().getSolarSystems();
        
        //shouldnt loop over all solarSystems after it's found the clicked one
        for (SolarSystem solarSystem : solarSystems) {
            if (event.getX() <= solarSystem.getCoords().getX()
                    + Context.DOT_SIZE
                    && event.getX() >= solarSystem.getCoords().getX()
                    && event.getY() <= solarSystem.getCoords().getY()
                    + Context.DOT_SIZE
                    && event.getY() >= solarSystem.getCoords().getY()) {

                soundManager.playSoundEffect(SoundManager.CLICKID); //change to a beep sound instead
                setDescription(solarSystem);
                Context.getInstance().setFocus(solarSystem);
            }
        }
    }
    
    /**
     * Sets the text field to describe the selected solar system.
     * @param solarSystem the system being described
     */
    public final void setDescription(final SolarSystem solarSystem) {
        SolarSystem currentSolarSystem;
        
        currentSolarSystem = player.getCurrentPlanet().getParentSolarSystem();
        
        //draw indicator of currently selected one
        String string = "Name: " + solarSystem.getName() + "\nCoords: "
                + solarSystem.getCoords();
        
        if (solarSystem.equals(currentSolarSystem)) {
            string += "\n\nCurrent solar system";
        }
        
        description.setText(string);
    }
    
    /**
     * Travels to the currently selected system.
     */
    public final void selectSystem() {
        SolarSystem focus = Context.getInstance().getFocus();
        if (focus != null) {
            soundManager.playSoundEffect(SoundManager.CLICKID);
            controller.setScreen("SolarMap");

        } else {
            description.setText("Please select a system.");
        }
    }
}
