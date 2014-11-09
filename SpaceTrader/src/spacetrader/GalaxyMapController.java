package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import spacetrader.SpaceTrader.ControlledScreen;

public class GalaxyMapController implements ControlledScreen, Initializable {
    @FXML private Canvas canvas;
    @FXML private TextArea description;
    
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
        player = Context.getInstance().getPlayer();
        drawSolarSystems();
        setDescription(player.getCurrentPlanet().getParentSolarSystem());
        Context.getInstance().setFocus(player.getCurrentPlanet().getParentSolarSystem());
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
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Clear canvas
        gc.clearRect(0, 0, Context.BOUNDARY_VISIBLE, Context.BOUNDARY_VISIBLE);
        
        for (SolarSystem solarSystem : Context.getInstance().getUniverse().getSolarSystems()) {
            isClose = false;
            for (Planet planet : solarSystem.getPlanets()) {
                if (player.getCurrentPlanet().getCoords().distanceTo(planet.getCoords())
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
        gc.setFill(Color.GOLD);
        gc.fillOval(player.getCurrentPlanet().getParentSolarSystem().getCoords().getX(), 
            player.getCurrentPlanet().getParentSolarSystem().getCoords().getY(),
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
        //shouldnt loop over all solarSystems after it's found the clicked one
        for (SolarSystem solarSystem : Context.getInstance().getUniverse().getSolarSystems()) {
            if (event.getX() <= solarSystem.getCoords().getX()
                    + Context.DOT_SIZE
                    && event.getX() >= solarSystem.getCoords().getX()
                    && event.getY() <= solarSystem.getCoords().getY()
                    + Context.DOT_SIZE
                    && event.getY() >= solarSystem.getCoords().getY()) {
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
        //draw indicator of currently selected one
        String string = "Name: " + solarSystem.getName() + "\nCoords: "
                + solarSystem.getCoords();
        
        if (solarSystem.equals(player.getCurrentPlanet().getParentSolarSystem())) {
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
            controller.setScreen("SolarMap");
        } else {
            description.setText("Please select a system.");
        }
    }
}
