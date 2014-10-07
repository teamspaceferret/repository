package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import spacetrader.SpaceTrader.ControlledScreen;

public class GalaxyMapController implements ControlledScreen, Initializable {
    @FXML private Button travelButton;
    @FXML private Label fuelLabel;
    @FXML private TextArea description;
    @FXML private Canvas canvas;
    
    ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    Player player = Context.getInstance().getPlayer();
    
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
        drawSolarSystems();
        this.fuelLabel.setText("Fuel: " + String.valueOf(this.player.getShip().getFuelLevel()));
        setDescription(player.getCurrentPlanet().getParentSolarSystem());
        Context.getInstance().setFocus(player.getCurrentPlanet().getParentSolarSystem());
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
     * Draws current solar systems on galaxy map
     */
    public void drawSolarSystems() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Clear canvas
        gc.clearRect(0, 0, 300, 300);
        
        for (SolarSystem solarSystem : universe.getSolarSystems()) {
            Boolean isClose = false;
            for (Planet planet : solarSystem.getPlanets()) {
                if (player.getAbsoluteLocation().distanceTo(planet.getAbsoluteLocation())
                    < 5*this.player.getShip().getFuelLevel()) {
                    isClose = true;
                }
            }
            if (isClose) {
                gc.setFill(Color.GREEN);
            } else {
                gc.setFill(Color.RED);
            }
            gc.fillOval(solarSystem.getCoords().getX(), solarSystem.getCoords().getY(), 10, 10);
        }
        
        //draw current system in gold
        gc.setFill(Color.GOLD);
        gc.fillOval(player.getCurrentPlanet().getParentSolarSystem().getCoords().getX(), 
            player.getCurrentPlanet().getParentSolarSystem().getCoords().getY(), 10, 10);
        //draw range circle
        gc.setStroke(Color.BLACK);
        gc.strokeOval(player.getAbsoluteLocation().getX()
                - 5*this.player.getShip().getMaxFuelLevel() + 5,
                player.getAbsoluteLocation().getY()
                        - 5*this.player.getShip().getMaxFuelLevel() + 5,
                10*this.player.getShip().getMaxFuelLevel(),
                10*this.player.getShip().getMaxFuelLevel());
    }
    
    /**
     * Checks mouseclicks for if they are on a solar system or not
     * @param event the mouseclick
     */
    public void onMouseClick(MouseEvent event) {
        //shouldnt loop over all solarSystems after it's found the clicked one
        for (SolarSystem solarSystem : this.universe.getSolarSystems()) {
            if (event.getX() <= solarSystem.getCoords().getX() + 10
                    && event.getX() >= solarSystem.getCoords().getX()
                    && event.getY() <= solarSystem.getCoords().getY() + 10
                    && event.getY() >= solarSystem.getCoords().getY()) {
                this.setDescription(solarSystem);
                Context.getInstance().setFocus(solarSystem);
            }
        }
    }
    
    /**
     * Sets the text field to describe the selected solar system
     * @param solarSystem the system being described
     */
    public void setDescription(SolarSystem solarSystem) {
        //draw indicator of currently selected one
        this.description.setText("Name: " + solarSystem.getName() + "\n"
                + "Coords: " + solarSystem.getCoords());
    }
    
    /**
     * Travels to the currently selected system
     */
    public void selectSystem() {
        SolarSystem focus = Context.getInstance().getFocus();
        if (focus != null) {
            this.controller.setScreen("SolarMap");
        } else {
            this.description.setText("Please select a system.");
        }
    }
}
