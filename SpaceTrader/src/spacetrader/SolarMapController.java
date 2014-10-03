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

public class SolarMapController implements ControlledScreen, Initializable {
    @FXML private Button travelButton;
    @FXML private Label fuelLabel;
    @FXML private TextArea description;
    @FXML private Canvas canvas;
    
    ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    Player player = Context.getInstance().getPlayer();
    Planet selectedPlanet;
    
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
        drawPlanets();
        this.fuelLabel.setText("Fuel: " + String.valueOf(this.player.getShip().getFuelLevel()));
        int[] stockReset = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        Context.getInstance().setStock(stockReset);
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
    public void drawPlanets() {
        SolarSystem solarSystem = Context.getInstance().getFocus();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Clear canvas
        gc.clearRect(0, 0, 300, 300);
        
        for (Planet planet : solarSystem.getPlanets()) {
            if (player.getAbsoluteLocation().distanceTo(planet.getAbsoluteLocation())
                    < 5*this.player.getShip().getFuelLevel()) {
                gc.setFill(Color.GREEN);
            } else {
                gc.setFill(Color.RED);
            }
            gc.fillOval(planet.getCoords().getX(), planet.getCoords().getY(), 10, 10);
        }
             
        // Draw current planet in gold
        if (player.getCurrentPlanet().getParentSolarSystem() == solarSystem) {
            gc.setFill(Color.GOLD);
            gc.fillOval(player.getCurrentPlanet().getCoords().getX(), 
                player.getCurrentPlanet().getCoords().getY(), 10, 10);
        }
    }
    
    /**
     * Sets the current available fuel value to be displayed
     */
    public void currentFuel() {
        fuelLabel.setText("Fuel: 1000"); //add fuel value here!
    }
    
    /**
     * Checks mouseclicks for if they are on a planet or not
     * @param event the mouseclick
     */
    public void onMouseClick(MouseEvent event) {
        for (Planet planet : Context.getInstance().getFocus().getPlanets()) {
            if ((event.getX() <= (planet.getCoords().getX() + 10))
                    && ((event.getX() >= (planet.getCoords().getX())))
                    && ((event.getY() <= (planet.getCoords().getY() + 10))
                    && (event.getY() >= (planet.getCoords().getY())))) {
                selectedPlanet = planet;
                setDescription(selectedPlanet);
            }
        }
    }
    
    /**
     * Sets the text field to describe the selected planet
     * @param planet the planet being described
     */
    public void setDescription(Planet planet) {
        description.setText("Name: " + planet.getName() + "\n"
                + "Coords: " + this.player.getCurrentPlanet().getCoords() + "\n"
                + "Distance: " + (int)this.player.getCurrentPlanet().distanceToPlanet(planet) + "\n"
                + "Fuel required: " + 2*(int)this.player.getCurrentPlanet().distanceToPlanet(planet));
    }
    
    /**
     * Travels to the currently selected planet
     */
    public void selectPlanet() {
        if (this.selectedPlanet == null) {
            this.description.setText("Please select a planet.");
        } else if (this.player.getShip().getFuelLevel() <= 2*(int)this.player.getCurrentPlanet().distanceToPlanet(this.selectedPlanet)) {
            this.description.setText("You don't have enough fuel to travel to " + this.selectedPlanet.getName() + ".");
        } else {
            this.player.getShip().subtractFuel(2*(int)this.player.getCurrentPlanet().distanceToPlanet(this.selectedPlanet));
            this.player.setCurrentPlanet(this.selectedPlanet);
            this.controller.setScreen("PlanetScreen");
        }
    }
    
    /**
     * Go back to the galaxy map
     */
    public void backAction() {
        controller.setScreen("GalaxyMap");
    }
}
