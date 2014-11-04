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
    @FXML private Canvas canvas;
    @FXML private Label fuelLabel;
    @FXML private TextArea description;
    
    ScreensController controller;
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
        player = Context.getInstance().getPlayer();
        drawPlanets();
        this.fuelLabel.setText("Fuel: "
                + String.valueOf(this.player.getShip().getFuelLevel()));
        int[] stockReset = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        Context.getInstance().setStock(stockReset);
        selectedPlanet = player.getCurrentPlanet();
        if (player.getCurrentPlanet().getParentSolarSystem().equals(Context.getInstance().getFocus())) {        
            setDescription(player.getCurrentPlanet());
        }
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
     * Draws the current solar systems on solar map.
     */
    public void drawPlanets() {
        SolarSystem solarSystem = Context.getInstance().getFocus();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Clear canvas
        gc.clearRect(0, 0, 310, 310);
        
        for (Planet planet : solarSystem.getPlanets()) {
            if (player.getAbsoluteLocation().distanceTo(planet.getAbsoluteLocation())
                    < 0.5*this.player.getShip().getFuelLevel()) {
                gc.setFill(Color.GREEN);
            } else {
                gc.setFill(Color.RED);
            }
            
            // TODO: Move computation to separate function
            gc.fillOval(10*(planet.getCoords().getX() - solarSystem.getCoords().getX()) + 150,
                        10*(planet.getCoords().getY() - solarSystem.getCoords().getY()) + 150,
                        10, 10);
        }
             
        // Draw current planet in gold
        if (player.getCurrentPlanet().getParentSolarSystem().equals(solarSystem)) {
            gc.setFill(Color.GOLD);
            gc.fillOval(10*(player.getCurrentPlanet().getCoords().getX() - solarSystem.getCoords().getX()) + 150, 
                10*(player.getCurrentPlanet().getCoords().getY() - solarSystem.getCoords().getY()) + 150, 10, 10);
        }
        
        /*// Draw range circle
        gc.setStroke(Color.BLACK);
        gc.strokeOval(this.player.getCurrentPlanet().getCoords().getX() - this.player.getShip().getRange()/2 + 5,
                this.player.getCurrentPlanet().getCoords().getY() - this.player.getShip().getRange()/2 + 5,
                20*this.player.getShip().getRange(),
                20*this.player.getShip().getRange());*/
    }
    
    
    /**
     * Checks if the user clicked a planet.
     * @param event click event
     */
    public void onMouseClick(MouseEvent event) {
        SolarSystem solarSystem = Context.getInstance().getFocus();
        
        for (Planet planet : Context.getInstance().getFocus().getPlanets()) {
            
            boolean a = event.getX() <= 10*(planet.getCoords().getX()
                    - solarSystem.getCoords().getX()) + 160;
            boolean b = event.getX() >= 10*(planet.getCoords().getX()
                    - solarSystem.getCoords().getX()) + 150;
            boolean c = event.getY() <= 10*(planet.getCoords().getY()
                    - solarSystem.getCoords().getY()) + 160;
            boolean d = event.getY() >= 10*(planet.getCoords().getY()
                    - solarSystem.getCoords().getY()) + 150;
            
            if (a && b && c && d) {
                selectedPlanet = planet;
                setDescription(selectedPlanet);
            }
        }
    }
    
    /**
     * TODO
     */
    public void selectPlanet() {
        if (this.selectedPlanet == null) {
            this.description.setText("Please select a planet.");
        } else if (this.player.getShip().getRange() <= 2*(int)this.player.getAbsoluteLocation().distanceTo(this.selectedPlanet.getAbsoluteLocation())) {
            this.description.setText(this.selectedPlanet.getName() + "is too further away than your ship's maximum range.");
        } else if (this.player.getShip().getFuelLevel() <= 2*(int)this.player.getAbsoluteLocation().distanceTo(this.selectedPlanet.getAbsoluteLocation())) {
            this.description.setText("You don't have enough fuel to travel to " + this.selectedPlanet.getName() + ".");
        } else {
            travelToPlanet(selectedPlanet);
        }
    }
    
    /**
     * Sets the text field to describe the selected planet.
     * @param planet the planet being described
     */
    public void setDescription(Planet planet) {
        String string = "Name: " + planet.getName() + "\nCoords: "
                + planet.getCoords() + "\nDistance: "
                + (int)this.player.getAbsoluteLocation().distanceTo(planet.getAbsoluteLocation())
                + "\nFuel required: "
                + 2*(int)this.player.getAbsoluteLocation().distanceTo(planet.getAbsoluteLocation())
                + "\n" + "Tech level: " + Context.TECH_LEVELS[(planet.getTechLevel())] + "\n"
                + "Government: " + planet.getGovernment().getName() + "\n"
                + "Police: " + planet.getGovernment().getPolice()
                + " Pirates: " + planet.getGovernment().getPirate()                
                + " Traders: " + planet.getGovernment().getTrader() + "\n"
                + "Resources: " + planet.getResource().getName() + "\n"
                + "Event: " + planet.getEvent().getName() + "\n";

        
        if (planet.equals(player.getCurrentPlanet())) {
            string += "\nCurrent planet";
        }
        
        description.setText(string);
    }
    
    /**
     * Travels to the given planet.
     * @param planet destination planet
     */
    public void travelToPlanet(Planet planet) {
        if (this.player.getCurrentPlanet().equals(planet)) {
            
        }
        this.player.getShip().subtractFuel(2*(int)this.player.getAbsoluteLocation().distanceTo(planet.getAbsoluteLocation()));
        this.player.setPreviousPlanet(this.player.getCurrentPlanet());
        this.player.setCurrentPlanet(planet);
        if (!this.player.getPreviousPlanet().equals(this.player.getCurrentPlanet())
                || (this.player.getCurrentPlanet().getName().equals("Noobville") 
                && this.player.getCurrentPlanet().getMarket().getPrices()[0] == -1)) {
            this.player.getCurrentPlanet().getMarket().setPrices();
            this.player.getCurrentPlanet().getMarket().updateStock();
        }           

        //random events happen on the planet you go to, only if changing planets
        if (this.player.getCurrentPlanet().equals(this.player.getPreviousPlanet())) {
            //dont do anything, you are already here
            this.controller.setScreen("PlanetScreen");
        } else if (!(this.player.getCurrentPlanet().equals(this.player.getPreviousPlanet()))) {
            //a random event happens!
            //1/3 encounters
            //this.controller.setScreen("Encounter");
            //2/3 random things
            this.controller.setScreen("TravelEvent");
        }
    }
    
    /**
     * Transitions to the galaxy map screen.
     */
    public void backAction() {
        controller.setScreen("GalaxyMap");
    }
}
