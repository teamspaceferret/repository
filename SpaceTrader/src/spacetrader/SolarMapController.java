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
    @FXML private TextArea descriptions;
    @FXML private Canvas canvas;
    
    ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    Player player = Context.getInstance().getPlayer();
    Planet currentlySelected;
    
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
        int[] stockReset = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        Context.setStock(stockReset);
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
        GraphicsContext gc = canvas.getGraphicsContext2D();
        player.getCurrentSolar().toString();
        //Clears canvas
        gc.clearRect(0,0,300,300);
        
        for (Planet planet : player.getCurrentSolar().getPlanets()) {
            gc.setFill(Color.RED);
            gc.fillOval(planet.getCoords().getX(), planet.getCoords().getY(), 10, 10);
        }
             
        //draw current planet in gold
        gc.setFill(Color.GOLD);
        gc.fillOval(player.getCurrentPlanet().getCoords().getX(), 
            player.getCurrentPlanet().getCoords().getY(), 10, 10);
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
        for(int i = 0; i < player.getCurrentSolar().getPlanets().length; i++) {
            if(((event.getX() <= (player.getCurrentSolar().getPlanets()[i].getCoords().getX() + 10)) && 
                    ((event.getX() >= (player.getCurrentSolar().getPlanets()[i].getCoords().getX()))) &&
                    ((event.getY() <= (player.getCurrentSolar().getPlanets()[i].getCoords().getY() + 10)) &&
                    (event.getY() >= (player.getCurrentSolar().getPlanets()[i].getCoords().getY()))))) {
                currentlySelected = player.getCurrentSolar().getPlanets()[i];
                setDescription(currentlySelected);
            }
        }
    }
    
    /**
     * Sets the text field to describe the selected planet
     * @param planet the planet being described
     */
    public void setDescription(Planet planet) {
        //draw indicator of currently selected one
        descriptions.setText("Name: " + planet.getName() + "\n" +
            "Current system: " + player.getCurrentSolar() + "\n" +
            "Fuel required: " + "\n" + "\n" +
            "Flavor text?");
    }
    
    /**
     * Travels to the currently selected planet
     */
    public void selectPlanet() {
        if (currentlySelected != null) {
            player.setCurrentPlanet(currentlySelected);
            controller.setScreen("PlanetScreen");
        } else {
            System.out.println("Please select a planet.");
        }
    }
    
    /**
     * Travels back to the galaxy map
     */
    public void backAction() {
        controller.setScreen("GalaxyMap");
    }
}
