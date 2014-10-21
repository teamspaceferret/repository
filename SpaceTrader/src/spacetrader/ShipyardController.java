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
    @FXML private Button fleaStats;
    @FXML private Button gnatStats;
    @FXML private Button fireflyStats;
    @FXML private Button mosquitoStats;
    @FXML private Button bumblebeeStats;
    //@FXML private Button ship6Stats;
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
    @FXML private Label fleaCost;
    @FXML private Label gnatCost;
    @FXML private Label fireflyCost;
    @FXML private Label mosquitoCost;
    @FXML private Label bumblebeeCost;
    //@FXML private Label ship6Cost;
    
    @FXML private Label playerCredtNum1; //for the credit count on upgrade ship page
    @FXML private Label playerCreditNum2; //for the credit cound on buy new ship page
    
    
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
    
    public void fleaStats(){
        
    }
    
    public void gnatStats(){
        
    }
    
    public void fireflyStats(){
        
    }
    
    public void mosquitoStats(){
        
    }
    
    public void bumblebeeStats(){
        
    }
    
    public void back(){
        controller.setScreen("PlanetScreen");
    }
    
    public void confirm(){
        
    }
    
    public void buyShip(){
        
    }
 
}
