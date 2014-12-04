package spacetrader;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import spacetrader.SpaceTrader.ControlledScreen;

public class SolarMapController implements ControlledScreen, Initializable {
    @FXML private Canvas canvas;
    @FXML private Label fuelLabel;
    @FXML private TextArea description;
    

    private SoundManager soundManager = SoundManager.getSoundManager();

    private ScreensController controller;
    private Player player = Context.getInstance().getPlayer();
    private Planet selectedPlanet;
    
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
        SolarSystem focus;
        
        focus = Context.getInstance().getFocus();
        
        player = Context.getInstance().getPlayer();
        soundManager.setPrevScreen("SolarMap");
        soundManager.setCurrentBG(SoundManager.COMPUTER_BEEP_ID);
        
        if(!soundManager.currentlyPlayingBGMusicID().equals(SoundManager.COMPUTER_BEEP_ID)){
            soundManager.playBGWithCheck(SoundManager.COMPUTER_BEEP_ID, SoundManager.COMPUTER_BEEP_PATH);
        }
        
        drawPlanets();
        this.fuelLabel.setText("Fuel: "
                + String.valueOf(this.player.getShip().getFuelLevel()));
        int[] stockReset = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        Context.getInstance().setStock(stockReset);
        selectedPlanet = player.getCurrentPlanet();
        if (selectedPlanet.getParentSolarSystem().equals(focus)) {        
            setDescription(player.getCurrentPlanet());
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
     * Draws the current solar systems on solar map.
     */
    public void drawPlanets() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Planet currentPlanet = player.getCurrentPlanet();
        SolarSystem solarSystem = Context.getInstance().getFocus();
        
        // Clear canvas
        gc.clearRect(0, 0, Context.BOUNDARY_VISIBLE, Context.BOUNDARY_VISIBLE);
        
        for (Planet planet : solarSystem.getPlanets()) {
            if (currentPlanet.getCoords().distanceTo(planet.getCoords())
                    < Context.DISTANCE_TO_FUEL_RATIO 
                    * this.player.getShip().getFuelLevel()) {
                gc.setFill(Color.GREEN);
            } else {
                gc.setFill(Color.RED);
            }
            
            gc.fillOval(Context.UNIVERSE_TO_SOLAR_SYSTEM_RATIO
                    * (planet.getCoords().getX()
                            - solarSystem.getCoords().getX())
                    + Context.BOUNDARY_VISIBLE / 2,
                        Context.UNIVERSE_TO_SOLAR_SYSTEM_RATIO
                                * (planet.getCoords().getY()
                                - solarSystem.getCoords().getY())
                                + Context.BOUNDARY_VISIBLE / 2,
                        Context.DOT_SIZE, Context.DOT_SIZE);
        }
             
        // Draw current planet in gold
        if (currentPlanet.getParentSolarSystem().equals(solarSystem)) {
            gc.setFill(Color.GOLD);
            gc.fillOval(Context.UNIVERSE_TO_SOLAR_SYSTEM_RATIO
                    * (player.getCurrentPlanet().getCoords().getX()
                            - solarSystem.getCoords().getX())
                    + Context.BOUNDARY_VISIBLE / 2, 
                Context.UNIVERSE_TO_SOLAR_SYSTEM_RATIO
                        * (player.getCurrentPlanet().getCoords().getY()
                                - solarSystem.getCoords().getY())
                        + Context.BOUNDARY_VISIBLE / 2,
                Context.DOT_SIZE, Context.DOT_SIZE);
        }
        
        // Draw range circle
        /*gc.setStroke(Color.BLACK);
        gc.strokeOval(this.player.getCurrentPlanet().getCoords().getX() - this.player.getShip().getRange()/2 + 5,
                this.player.getCurrentPlanet().getCoords().getY() - this.player.getShip().getRange()/2 + 5,
                20*this.player.getShip().getRange(),
                20*this.player.getShip().getRange());*/
    }
    
    /**
     * Checks if the user clicked a planet.
     * @param event click event
     */
    public final void onMouseClick(final MouseEvent event) {
        SolarSystem solarSystem = Context.getInstance().getFocus();
        
        for (Planet planet : Context.getInstance().getFocus().getPlanets()) {
            boolean a = event.getX() <= 10 * (planet.getCoords().getX()
                    - solarSystem.getCoords().getX()) + 150 + Context.DOT_SIZE;
            boolean b = event.getX() >= 10 * (planet.getCoords().getX()
                    - solarSystem.getCoords().getX()) + 150;
            boolean c = event.getY() <= 10 * (planet.getCoords().getY()
                    - solarSystem.getCoords().getY()) + 150 + Context.DOT_SIZE;
            boolean d = event.getY() >= 10 * (planet.getCoords().getY()
                    - solarSystem.getCoords().getY()) + 150;
            
            if (a && b && c && d) {
                selectedPlanet = planet;
                setDescription(selectedPlanet);
                soundManager.playSEWithCheck(SoundManager.MAP_SELECT_BEEP_ID, SoundManager.MAP_SELECT_BEEP_PATH);
            }
        }
    }
    
    /**
     * Selects a planet and travels to it if able.
     */
    public void selectPlanet() {
        Coordinate coords = player.getCurrentPlanet().getCoords();
        
        if (selectedPlanet == null) {
            description.setText("Please select a planet.");
        } else if (player.getShip().getRange()
                <= Context.FUEL_TO_DISTANCE_RATIO
                * (int) coords.distanceTo(selectedPlanet.getCoords())) {
            description.setText(this.selectedPlanet.getName()
                    + "is too further away than your ship's maximum range.");
        } else if (player.getShip().getFuelLevel()
                <= Context.FUEL_TO_DISTANCE_RATIO
                * (int) coords.distanceTo(selectedPlanet.getCoords())) {
            description.setText("You don't have enough fuel to travel to "
                    + selectedPlanet.getName() + ".");
        } else {
            travelToPlanet(selectedPlanet);
        }
    }
    
    /**
     * Sets the text field to describe the selected planet.
     * @param planet the planet being described
     */
    public void setDescription(Planet planet) {
        Coordinate coords = player.getCurrentPlanet().getCoords();
        
        String string = "Name: " + planet.getName() + "\nCoords: "
                + planet.getCoords() + "\nDistance: "
                + (int) coords.distanceTo(planet.getCoords())
                + "\nFuel required: "
                + Context.FUEL_TO_DISTANCE_RATIO 
                * (int) coords.distanceTo(planet.getCoords())
                + "\n" + "Tech level: "
                + Context.TECH_LEVELS[(planet.getTechLevel())]
                + "\nGovernment: " + planet.getGovernment().getName()
                + "\nPolice: " + planet.getGovernment().getPolice()
                + "\nPirates: " + planet.getGovernment().getPirate()                
                + "\nTraders: " + planet.getGovernment().getTrader()
                + "\nResources: " + planet.getResource().getName()
                + "\nEvent: " + planet.getEvent().getName() + "\n";

        
        if (planet.isEqual(player.getCurrentPlanet())) {
            string += "\nCurrent planet";
        }
        
        description.setText(string);
    }
    
    /**
     * Travels to the given planet.
     * @param planet destination planet
     */
    public final void travelToPlanet(final Planet planet) {
        Random rand;
        Planet currentPlanet = player.getCurrentPlanet();
        Coordinate coords = currentPlanet.getCoords();
        
        player.getShip().subtractFuel((int) Context.FUEL_TO_DISTANCE_RATIO
                * (int) coords.distanceTo(planet.getCoords()));
        player.setPreviousPlanet(player.getCurrentPlanet());
        player.setCurrentPlanet(planet);
        currentPlanet = player.getCurrentPlanet();
        if (!player.getPreviousPlanet().isEqual(currentPlanet)
                || (currentPlanet.getName().equals("Noobville")
                && currentPlanet.getMarket().getPrices()[0] == -1)) {
            currentPlanet.getMarket().setPrices();
            currentPlanet.getMarket().updateStock();
        }           

        //random events happen on the planet you go to, only if changing planets
        soundManager.playSEWithCheck(SoundManager.CLICKID, SoundManager.CLICKPATH);
        if (currentPlanet.isEqual(player.getPreviousPlanet())) {
            //dont do anything, you are already here
            controller.setScreen("PlanetScreen");
        } else {
            rand = new Random();
            if (rand.nextInt(3) == 1) {
                controller.setScreen("TravelEvent");
            } else {
                controller.setScreen("PlanetScreen");
            }
        }
    }
    
    /**
     * Transitions to the galaxy map screen.
     */
    public void backAction() {
        soundManager.playSEWithCheck(SoundManager.CLICKID, SoundManager.CLICKPATH);
        controller.setScreen("GalaxyMap");
    }
}
