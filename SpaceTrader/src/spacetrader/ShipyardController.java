package spacetrader;

import java.net.URL;
import java.util.HashMap;
import java.util.AbstractMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    
    @FXML private Label playerCreditNum1; //for the credit count on upgrade ship page
    @FXML private Label playerCreditNum2; //for the credit cound on buy new ship page
    @FXML private Tab weaponsTab;
    @FXML private Tab shipTab;
    @FXML private TabPane tabPane;
    
    
    ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    Player player = Context.getInstance().getPlayer();
    Ship selectedToBuy;
    SoundManager soundManager = SoundManager.getSoundManager();
    
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
    //flea, gnat, firefly, mosquito, bumblebee
    public void initScreen() {
        soundManager.setPrevScreen("Shipyard");
        universe = Context.getInstance().getUniverse();
        player = Context.getInstance().getPlayer();
        player.addCredits(1000000);
        updateShipTextArea(currentShipStats, player.getShip());
        selectedToBuy = null;
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
        if (player.getCurrentPlanet().getTechLevel() < new Ship("mosquito").getMinTechLevel()) {
            gnatStats.setDisable(true);
            fireflyStats.setDisable(true);
            mosquitoStats.setDisable(true);
            bumblebeeStats.setDisable(true);
        } else {
            gnatStats.setDisable(false);
            fireflyStats.setDisable(false);
            mosquitoStats.setDisable(false);
            bumblebeeStats.setDisable(false);
        }
        updatePrices();
    }
    
    /**
     * Initializes the controller class.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    private HashMap mapIntsToFields() {
        HashMap<Integer, TextField> fieldMap = new HashMap();
        fieldMap.put(0, playerWeapon1);
        fieldMap.put(1, playerWeapon2);
        fieldMap.put(2, playerWeapon3);
        fieldMap.put(3, playerShield1);
        fieldMap.put(4, playerShield2);
        fieldMap.put(5, playerFuel);
        fieldMap.put(6, playerCargoSlots);
        return fieldMap;
    }
    
    private HashMap mapIntsToLabels() {
        HashMap<Integer, Label> labelMap = new HashMap();
        labelMap.put(0, weapon1Cost);
        labelMap.put(1, weapon2Cost);
        labelMap.put(2, weapon3Cost);
        labelMap.put(3, shield1Cost);
        labelMap.put(4, shield2Cost);
        labelMap.put(5, fuelCost);
        labelMap.put(6, cargoSlotsCost);
        labelMap.put(7, fleaCost);
        labelMap.put(8, gnatCost);
        labelMap.put(9, fireflyCost);
        labelMap.put(10, mosquitoCost);
        labelMap.put(11, bumblebeeCost);
        labelMap.put(12, playerCreditNum1);
        labelMap.put(13, playerCreditNum2);
        return labelMap;
    }
    
    private void updatePrices() {
        HashMap<Integer, String> priceMap = new HashMap();
        HashMap labelMap = mapIntsToLabels();
        priceMap.put(0, "1000");
        priceMap.put(1, "1500");
        priceMap.put(2, "2000");
        priceMap.put(3, "2000");
        priceMap.put(4, "4000");
        priceMap.put(5, "" + player.getShip().getFuelCost());
        priceMap.put(6, "1000");
        priceMap.put(7, "" + new Ship("flea").getPrice());
        priceMap.put(8, "" + new Ship("gnat").getPrice());
        priceMap.put(9, "" + new Ship("firefly").getPrice());
        priceMap.put(10, "" + new Ship("mosquito").getPrice());
        priceMap.put(11, "" + new Ship("bumblebee").getPrice());
        for (int i = 0; i < 12; i++) {
            if (i == 5) {
                ((Label)labelMap.get(i)).setText(priceMap.get(i) + " / ea");
            } else {
                ((Label)labelMap.get(i)).setText(priceMap.get(i));
            }
        }
    }
    
    private void updateShipTextArea(TextArea field, Ship ship) {
        field.setText(ship.shipDescription());
        selectedToBuy = ship;
    }
    
    private void genericIncrement() {
        
    }
    
    private void genericDecrement() {
        
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
        updateShipTextArea(buyShipStats, new Ship("flea"));
    }
    
    public void gnatStats(){
        updateShipTextArea(buyShipStats, new Ship("gnat"));
    }
    
    public void fireflyStats(){
        updateShipTextArea(buyShipStats, new Ship("firefly"));
    }
    
    public void mosquitoStats(){
        updateShipTextArea(buyShipStats, new Ship("mosquito"));
    }
    
    public void bumblebeeStats(){
        updateShipTextArea(buyShipStats, new Ship("bumblebee"));
    }
    
    public void back(){
        controller.setScreen("PlanetScreen");
        buyShipStats.clear();
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(weaponsTab);
    }
    
    public void confirm(){
        
    }
    
    public void buyShip(){
        if (selectedToBuy == null) {
            buyShipStats.setText("Select a ship to buy by clicking one of the 5 buttons.");
        }
        if (selectedToBuy != null && selectedToBuy.getPrice() < player.getCredits()) {
            player.setShip(selectedToBuy);
            buyShipStats.setText(selectedToBuy.getType() + " purchased!");
        }
    }
 
}
