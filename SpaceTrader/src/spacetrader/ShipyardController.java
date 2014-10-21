package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import spacetrader.SpaceTrader.ControlledScreen;

/**
 * FXML Controller class
 *
 * @author Stephanie
 */
public class ShipyardController implements Initializable, ControlledScreen {
    
    @FXML private Button plusWeapon1;
    @FXML private Button minusWeapon1;
    @FXML private Button plusWeapon2;
    @FXML private Button minusWeapon2;
    @FXML private Button plusWeapon3;
    @FXML private Button minusWeapon3;
    @FXML private Button plusShield1;
    @FXML private Button minusShield1;
    @FXML private Button plusShield2;
    @FXML private Button minusShield2;
    @FXML private Button plusFuel;
    @FXML private Button minusFuel;
    @FXML private Button plusCargoSpaces;
    @FXML private Button minusCargoSpaces;
    @FXML private Button ship1Stats;
    @FXML private Button ship2Stats;
    @FXML private Button ship3Stats;
    @FXML private Button ship4Stats;
    @FXML private Button ship5Stats;
    @FXML private Button ship6Stats;
    @FXML private Button confirm;
    @FXML private Button back;
    @FXML private Button buyShip;
    
    @FXML private TextArea buyShipStats;
    @FXML private TextArea currentShipStats;
    
    @FXML private TextField playerFuel;
    @FXML private TextField playerCargoSlots;
    @FXML private TextField playerWeapon1;
    @FXML private TextField playerWeapon2;
    @FXML private TextField playerWeapon3;
    @FXML private TextField playerShield1;
    @FXML private TextField playerShield2;
    
    @FXML private Label shipyardFuel; //?
    @FXML private Label shipyardCargoSlots; //?
    @FXML private Label shipyardWeapon1;
    @FXML private Label shipyardWeapon2;
    @FXML private Label shipyardWeapon3;
    @FXML private Label shipyardShield1;
    @FXML private Label shipyardShield2;
    
    @FXML private Label fuelCost; //?
    @FXML private Label cargoSlotsCost; //?
    @FXML private Label weapon1Cost;
    @FXML private Label weapon2Cost;
    @FXML private Label weapon3Cost;
    @FXML private Label shield1Cost;
    @FXML private Label shield2Cost;
    @FXML private Label ship1Cost;
    @FXML private Label ship2Cost;
    @FXML private Label ship3Cost;
    @FXML private Label ship4Cost;
    @FXML private Label ship5Cost;
    @FXML private Label ship6Cost;
    
    
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
        universe = Context.getInstance().getUniverse();
        player = Context.getInstance().getPlayer();
    }
    
    /**
     * Initializes the controller class.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void weapon1Increment(){
        
    }
    
    public void weapon1Decrement(){
        
    }
    
    public void weapon2Increment(){
        
    }
    
    public void weapon2Decrement(){
        
    }
    
    public void weapon3Increment(){
        
    }
    
    public void weapon3Decrement(){
        
    }
    
    public void shield1Increment(){
        
    }
    
    public void shield1Decrement(){
        
    }
    
    public void shield2Increment(){
        
    }
    
    public void shield2Decrement(){
        
    }
    
    public void fuelIncrement(){
        
    }
    
    public void fuelDecrement(){
        
    }
    
    public void cargoSlotsIncrement(){
        
    }
    
    public void cargoSlotsDecrement(){
        
    }
    
    public void ship1Stats(){
        
    }
    
    public void ship2Stats(){
        
    }
    
    public void ship3Stats(){
        
    }
    
    public void ship4Stats(){
        
    }
    
    public void ship5Stats(){
        
    }
    
    public void ship6Stats(){
        
    }
    
    public void back(){
        
    }
    
    public void confirm(){
        
    }
    
    public void buyShip(){
        
    }
 
}
