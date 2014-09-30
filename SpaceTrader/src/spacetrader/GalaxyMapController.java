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
    @FXML private TextArea descriptions;
    @FXML private Canvas canvas;
    
    ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    Player player = Context.getInstance().getPlayer();
    SolarSystem currentlySelected;
    
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
        currentFuel();
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
        //Clears canvas
        gc.clearRect(0,0,300,300);
        
        for (SolarSystem solarSystem : universe.getSolarSystems()) {
            gc.setFill(Color.RED);
            gc.fillOval(solarSystem.getCoords().getX(), solarSystem.getCoords().getY(), 10, 10);
        }
        
        //draw current system in gold
        gc.setFill(Color.GOLD);
        gc.fillOval(player.getCurrentSolar().getCoords().getX(), 
            player.getCurrentSolar().getCoords().getY(), 10, 10);
        //draw range circle
        gc.setStroke(Color.BLACK);
        gc.strokeOval(player.getCurrentSolar().getCoords().getX() - 75,
            player.getCurrentSolar().getCoords().getY() - 75,
            150, 150);
        
    }
    
    /**
     * Sets the current available fuel value to be displayed
     */
    public void currentFuel() {
        fuelLabel.setText("Fuel: "); //add fuel value here!
    }
    
    /**
     * Checks it currently selected system is in travel range
     * @param system the currently looked at system
     * @return whether it is in range
     */
    public boolean isInRange(SolarSystem system) {
        //check if ANY planets in this passed in system are in range
        return true;
    }
    
    /**
     * Checks mouseclicks for if they are on a solar system or not
     * @param event the mouseclick
     */
    public void onMouseClick(MouseEvent event) {
        //shouldnt loop over all solarSystems after it's found the clicked one
        for(int i = 0; i < universe.getSolarSystems().length; i++) {
            if(((event.getX() <= (universe.getSolarSystems()[i].getCoords().getX() + 10)) && 
                    ((event.getX() >= (universe.getSolarSystems()[i].getCoords().getX()))) &&
                    ((event.getY() <= (universe.getSolarSystems()[i].getCoords().getY() + 10)) &&
                    (event.getY() >= (universe.getSolarSystems()[i].getCoords().getY()))))) {
                currentlySelected = universe.getSolarSystems()[i];
                setDescription(currentlySelected);
            }
        }
    }
    
    /**
     * Sets the text field to describe the selected solar system
     * @param solarSystem the system being described
     */
    public void setDescription(SolarSystem solarSystem) {
        //draw indicator of currently selected one
        descriptions.setText("Name: " + solarSystem.getName());
    }
    
    /**
     * Travels to the currently selected system
     */
    public void selectSystem() {
        if (currentlySelected != null) {
            if (isInRange(currentlySelected)) {
                player.setCurrentSolar(currentlySelected);
                controller.setScreen("SolarMap");
            } else {
                System.out.println("That system is out of range! Please select a closer one.");
            }
        } else {
            System.out.println("Please select a system.");
        }
    }
}
