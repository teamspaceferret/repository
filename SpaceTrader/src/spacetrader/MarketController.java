package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import spacetrader.SpaceTrader.ControlledScreen;

public class MarketController implements ControlledScreen, Initializable {
    @FXML private Button confirmButton;
    @FXML private Button backButton;
    @FXML private Label creditLabel;
    @FXML private Label cargoLabel;
    @FXML private TextField playersWater;
    @FXML private Label tradersWater;
    @FXML private Label waterPrice;
    @FXML private TextField playersFur;
    @FXML private Label tradersFur;
    @FXML private Label furPrice;
    @FXML private TextField playersFood;
    @FXML private Label tradersFood;
    @FXML private Label foodPrice;
    @FXML private TextField playersOre;
    @FXML private Label tradersOre;
    @FXML private Label orePrice;
    @FXML private TextField playersGames;
    @FXML private Label tradersGames;
    @FXML private Label gamesPrice;
    @FXML private TextField playersFirearms;
    @FXML private Label tradersFirearms;
    @FXML private Label firearmsPrice;
    @FXML private TextField playersMed;
    @FXML private Label tradersMed;
    @FXML private Label medPrice;
    @FXML private TextField playersMachines;
    @FXML private Label tradersMachines;
    @FXML private Label machinesPrice;
    @FXML private TextField playersNarcotics;
    @FXML private Label tradersNarcotics;
    @FXML private Label narcoticsPrice;
    @FXML private TextField playersRobots;
    @FXML private Label tradersRobots;
    @FXML private Label robotsPrice;
    
    
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
        currentCredits();
        currentCargo();
        //set playersGood to the amount they currently have
        //set tradersGood to the amount they currently have, or to "No trade" if thats true
        //display the prices for each good via their price Label
        tradersWater.setText("10");
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
     * Sets the current available credits to be displayed
     */
    public void currentCredits() {
        creditLabel.setText("Credits: " + player.getCredits());
    }
    
    /**
     * Sets the current available cargo space to be displayed
     */
    public void currentCargo() {
        int currentUsedCargo = player.getShip().getCurrentUsedCargoSlots();
        int maxCargoSlots = player.getShip().getMaxCargoSlots();
        cargoLabel.setText("Cargo Bay Slots: " + currentUsedCargo + "/" + maxCargoSlots);
    }
    
    public void waterIncrement() {
        //add to usersWater
        //dec tradersWater
        //dec user's credits by water price
        //dec cargo space
    }
    public void waterDecrement() {
        //dec usersWater
        //add to tradersWater
        //incr user's credits by water price
        //incr cargo space
    }
    public void furIncrement() {
        
    }
    public void furDecrement() {
        
    }
    public void foodIncrement() {
        
    }
    public void foodDecrement() {
        
    }
    public void oreIncrement() {
        
    }
    public void oreDecrement() {
        
    }
    public void gamesIncrement() {
        
    }
    public void gamesDecrement() {
        
    }
    public void firearmsIncrement() {
        
    }
    public void firearmsDecrement() {
        
    }
    public void medIncrement() {
        
    }
    public void medDecrement() {
        
    }
    public void machinesIncrement() {
        
    }
    public void machinesDecrement() {
        
    }
    public void narcoticsIncrement() {
        
    }
    public void narcoticsDecrement() {
        
    }
    public void robotsIncrement() {
        
    }
    public void robotsDecrement() {
        
    }
    
    public void confirmAction() {
        currentCargo();
        currentCredits();
        //do stuff
        //go back to PlanetScreen
    }
    
    public void backAction() {
        //IMPORTANT, dont save the transactions made
        controller.setScreen("PlanetScreen");
    }
    
}
