package spacetrader;

import java.net.URL;
import java.util.HashMap;
import java.util.AbstractMap;
import java.util.ArrayList;
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
import java.util.ResourceBundle;

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
    @FXML private Button fillTank;
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
    
    @FXML private Label weapon1Name;
    @FXML private Label weapon2Name;
    @FXML private Label weapon3Name;
    @FXML private Label shield1Name;
    @FXML private Label shield2Name;
    
    @FXML private Label playerCreditNum1; //for the credit count on upgrade ship page
    @FXML private Label playerCreditNum2; //for the credit cound on buy new ship page
    @FXML private Tab weaponsTab;
    @FXML private Tab shipTab;
    @FXML private TabPane tabPane;
    
    
    ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    Player player = Context.getInstance().getPlayer();
    Ship selectedToBuy;
    
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
        universe = Context.getInstance().getUniverse();
        player = Context.getInstance().getPlayer();
        //this addCredits stuff is just to make sure we have plenty of money for the demo
        if (player.getCredits() < 1000000) {
            player.addCredits(1000000);
        }
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
        updateQuantities();
        
        weapon1Cost.setText("" + player.getCurrentPlanet().getShipyard().getWeaponStock()[0].getPrice());
        weapon2Cost.setText("" + player.getCurrentPlanet().getShipyard().getWeaponStock()[1].getPrice());
        weapon3Cost.setText("" + player.getCurrentPlanet().getShipyard().getWeaponStock()[2].getPrice());
        shield1Cost.setText("" + player.getCurrentPlanet().getShipyard().getShieldStock()[0].getPrice());
        shield2Cost.setText("" + player.getCurrentPlanet().getShipyard().getShieldStock()[1].getPrice());
        
        shipyardWeapon1.setText("" + player.getCurrentPlanet().getShipyard().getWeaponStock()[0].getDamage());
        shipyardWeapon2.setText("" + player.getCurrentPlanet().getShipyard().getWeaponStock()[1].getDamage());
        shipyardWeapon3.setText("" + player.getCurrentPlanet().getShipyard().getWeaponStock()[2].getDamage());
        shipyardShield1.setText("" + player.getCurrentPlanet().getShipyard().getShieldStock()[0].getHealth());
        shipyardShield2.setText("" + player.getCurrentPlanet().getShipyard().getShieldStock()[1].getHealth());
        
        weapon1Name.setText(player.getCurrentPlanet().getShipyard().getWeaponStock()[0].getName());
        weapon2Name.setText(player.getCurrentPlanet().getShipyard().getWeaponStock()[1].getName());
        weapon3Name.setText(player.getCurrentPlanet().getShipyard().getWeaponStock()[2].getName());
        shield1Name.setText(player.getCurrentPlanet().getShipyard().getShieldStock()[0].getName());
        shield2Name.setText(player.getCurrentPlanet().getShipyard().getShieldStock()[1].getName());
        
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
    * Helper method to map integer values to the TextFields
    * 
    * @return HashMap mapping ints to fields
    */
    private TextField[] mapIntsToFields() {
        TextField[] fields = new TextField[7];
        fields[0] = playerWeapon1;
        fields[1] = playerWeapon2;
        fields[2] = playerWeapon3;
        fields[3] = playerShield1;
        fields[4] = playerShield2;
        fields[5] = playerFuel;
        fields[6] = playerCargoSlots;
        return fields;
    }
    
   /**
    * Helper method to map integer values to the Labels
    * 
    * @return HashMap mapping ints to labels
    */
    private Label[] mapIntsToLabels() {
        Label[] labels = new Label[14];
        labels[0] = weapon1Cost;
        labels[1] = weapon2Cost;
        labels[2] = weapon3Cost;
        labels[3] = shield1Cost;
        labels[4] = shield2Cost;
        labels[5] = fuelCost;
        labels[6] = cargoSlotsCost;
        labels[7] = fleaCost;
        labels[8] = gnatCost;
        labels[9] = fireflyCost;
        labels[10] = mosquitoCost;
        labels[11] = bumblebeeCost;
        labels[12] = playerCreditNum1;
        labels[13] = playerCreditNum2;
        return labels;
    }
    
   /**
    * Updates prices in the int to label map
    */
    private void updatePrices() {
        int amountToFill = player.getShip().getMaxFuelLevel() - player.getShip().getFuelLevel();
        String[] prices = new String[12];
        Label[] labels = mapIntsToLabels();
        prices[0] = "1000"; //weapon1
        prices[1] = "1500"; //weapon2
        prices[2] = "2000"; //weapon3
        prices[3] = "2000"; //shield1
        prices[4] = "4000"; //shield2
        prices[5] = "" + amountToFill * player.getShip().getFuelCost();
        prices[6] = "1000";
        prices[7] = "" + new Ship("flea").getPrice();
        prices[8] = "" + new Ship("gnat").getPrice();
        prices[9] = "" + new Ship("firefly").getPrice();
        prices[10] = "" + new Ship("mosquito").getPrice();
        prices[11] = "" + new Ship("bumblebee").getPrice();
        for (int i = 0; i < 12; i++) {
            if (i == 5) {
                labels[i].setText(prices[i] + " to fill");
            } else {
                labels[i].setText(prices[i]);
            }
        }
    }
    
    //helper method to update textAreas with ship information
    private void updateShipTextArea(TextArea field, Ship ship) {
        field.setText(ship.shipDescription());
        selectedToBuy = ship;
    }
    
    //helper method to update textAreas with any String
    private void genericTextUpdate(TextArea area, String text) {
        area.setText(text);
    }
    
    private void updateQuantities() {
        ArrayList<Weapon> currentShipWeapons = player.getShip().getWeapons();
        ArrayList<Shield> currentShipShields = player.getShip().getShields();
        
        //need num of each type
        int weapon1 = 0;
        int weapon2 = 0;
        int weapon3 = 0;
        int shield1 = 0;
        int shield2 = 0;
        
        for(Weapon w : currentShipWeapons){
            if(w != null){
                //System.out.println(w.getName());
                if (w.getName().equalsIgnoreCase(player.getCurrentPlanet().getShipyard().getWeaponStock()[0].getName())){
                    weapon1 += 1;
                } else if (w.getName().equalsIgnoreCase(player.getCurrentPlanet().getShipyard().getWeaponStock()[1].getName())){
                    weapon2 += 1;
                } else if (w.getName().equalsIgnoreCase(player.getCurrentPlanet().getShipyard().getWeaponStock()[2].getName())){
                    weapon3 += 1;
                }
            }
            
        }
        
        for(Shield s : currentShipShields){
            //System.out.println(s.getName());
            if (s.getName().equalsIgnoreCase(player.getCurrentPlanet().getShipyard().getShieldStock()[0].getName())){
                shield1 += 1;
            } else if (s.getName().equalsIgnoreCase(player.getCurrentPlanet().getShipyard().getShieldStock()[1].getName())){
                shield2 += 1;
            }
        }
        
        playerWeapon1.setText("" + weapon1);
        playerWeapon2.setText("" + weapon2);
        playerWeapon3.setText("" + weapon3);
        playerShield1.setText("" + shield1);
        playerShield2.setText("" + shield2);
        
        
        
    }
    
    private void genericIncrement() {
        
    }
    
    private void genericDecrement() {
        
    }
    
    public void weapon1Increment(){
        ArrayList<Weapon> currentShipWeapons = player.getShip().getWeapons();
        if (player.getShip().getMaxWeapons() > currentShipWeapons.size() && player.removeCredits(player.getCurrentPlanet().getShipyard().getWeaponStock()[0].getPrice())){
            currentShipWeapons.add(player.getCurrentPlanet().getShipyard().getWeaponStock()[0]);
        }
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public void weapon1Decrement(){
        ArrayList<Weapon> currentShipWeapons = player.getShip().getWeapons();
        if (currentShipWeapons.contains(player.getCurrentPlanet().getShipyard().getWeaponStock()[0]) && player.addCredits(player.getCurrentPlanet().getShipyard().getWeaponStock()[0].getPrice()))
            currentShipWeapons.remove(player.getCurrentPlanet().getShipyard().getWeaponStock()[0]);
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public void weapon2Increment(){
        ArrayList<Weapon> currentShipWeapons = player.getShip().getWeapons();
        if (player.getShip().getMaxWeapons() > currentShipWeapons.size() && player.removeCredits(player.getCurrentPlanet().getShipyard().getWeaponStock()[1].getPrice())){
            currentShipWeapons.add(player.getCurrentPlanet().getShipyard().getWeaponStock()[1]);
        }
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public void weapon2Decrement(){
        ArrayList<Weapon> currentShipWeapons = player.getShip().getWeapons();
        if (currentShipWeapons.contains(player.getCurrentPlanet().getShipyard().getWeaponStock()[1]) && player.addCredits(player.getCurrentPlanet().getShipyard().getWeaponStock()[1].getPrice()))
            currentShipWeapons.remove(player.getCurrentPlanet().getShipyard().getWeaponStock()[1]);
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public void weapon3Increment(){
        ArrayList<Weapon> currentShipWeapons = player.getShip().getWeapons();
        if (player.getShip().getMaxWeapons() > currentShipWeapons.size() && player.removeCredits(player.getCurrentPlanet().getShipyard().getWeaponStock()[2].getPrice())){
            currentShipWeapons.add(player.getCurrentPlanet().getShipyard().getWeaponStock()[2]);
        }
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
        
    }
    
    public void weapon3Decrement(){
        ArrayList<Weapon> currentShipWeapons = player.getShip().getWeapons();
        if (currentShipWeapons.contains(player.getCurrentPlanet().getShipyard().getWeaponStock()[2]) && player.addCredits(player.getCurrentPlanet().getShipyard().getWeaponStock()[2].getPrice()))
            currentShipWeapons.remove(player.getCurrentPlanet().getShipyard().getWeaponStock()[2]);
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public void shield1Increment(){
        ArrayList<Shield> currentShipShields = player.getShip().getShields();
        if (player.getShip().getMaxShields() > currentShipShields.size() && player.removeCredits(player.getCurrentPlanet().getShipyard().getShieldStock()[0].getPrice()))
            currentShipShields.add(player.getCurrentPlanet().getShipyard().getShieldStock()[0]);  
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public void shield1Decrement(){
        ArrayList<Shield> currentShipShields = player.getShip().getShields();
        if (currentShipShields.contains(player.getCurrentPlanet().getShipyard().getShieldStock()[0]) && player.addCredits(player.getCurrentPlanet().getShipyard().getShieldStock()[0].getPrice()))
            currentShipShields.remove(player.getCurrentPlanet().getShipyard().getShieldStock()[0]);
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public void shield2Increment(){
        ArrayList<Shield> currentShipShields = player.getShip().getShields();
        if (player.getShip().getMaxShields() > currentShipShields.size() && player.removeCredits(player.getCurrentPlanet().getShipyard().getShieldStock()[1].getPrice()))
            currentShipShields.add(player.getCurrentPlanet().getShipyard().getShieldStock()[1]);  
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
        
    }
    
    public void shield2Decrement(){
        ArrayList<Shield> currentShipShields = player.getShip().getShields();
        if (currentShipShields.contains(player.getCurrentPlanet().getShipyard().getShieldStock()[1]) && player.addCredits(player.getCurrentPlanet().getShipyard().getShieldStock()[1].getPrice()))
            currentShipShields.remove(player.getCurrentPlanet().getShipyard().getShieldStock()[1]);
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    /**
     * Fills the player's ship up with fuel if they are missing fuel
     * and have enough money to do so.
     */
    public void fillTank(){
        Player player = Context.getInstance().getPlayer();
        Ship ship = player.getShip();
        int capacity = ship.getMaxFuel();
        int current = ship.getFuelLevel();
        int amountToFill = capacity - current;
        int cost = ship.getFuelCost() * amountToFill;
        if (current == capacity) {
            genericTextUpdate(currentShipStats, "You're already at full fuel!");
        } else if (player.getCredits() < cost) {
            genericTextUpdate(currentShipStats, "You don't have enough money.");
        } else {
            player.addCredits(-1 * cost);
            ship.addFuel(amountToFill);
            fuelCost.setText("0 to fill");
            genericTextUpdate(currentShipStats, "You're fueled up!");
        }
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public void cargoSlotsIncrement(){
        
    }
    
    public void cargoSlotsDecrement(){
        
    }
    
    //updates right TextArea with flea information
    public void fleaStats(){
        updateShipTextArea(buyShipStats, new Ship("flea"));
    }
    
    //updates right TextArea with gnat information
    public void gnatStats(){
        updateShipTextArea(buyShipStats, new Ship("gnat"));
    }

    //updates right TextArea with firefly information
    public void fireflyStats(){
        updateShipTextArea(buyShipStats, new Ship("firefly"));
    }
    
    //updates right TextArea with mosquito information    
    public void mosquitoStats(){
        updateShipTextArea(buyShipStats, new Ship("mosquito"));
    }
    
    //updates right TextArea with bumblebee information    
    public void bumblebeeStats(){
        updateShipTextArea(buyShipStats, new Ship("bumblebee"));
    }
    
    /**
     * returns to previous screen, saving changes that have occurred.
     */
    public void back(){
        controller.setScreen("PlanetScreen");
        buyShipStats.clear();
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(weaponsTab);
    }
    
    public void confirm(){
        /*controller.setScreen("PlanetScreen");
        buyShipStats.clear();
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(weaponsTab);*/
    }
    
    /**
     * Gives the player a new ship if they have no cargo, they have selected a 
     * ship to buy, and they have enough money to do so.
     */
    public void buyShip(){
        int usedSlots = player.getShip().getCurrentUsedCargoSlots();
        if (selectedToBuy == null) {
            buyShipStats.setText("Select a ship to buy by clicking one of the 5 buttons.");
        }
        else if (usedSlots > 0) {
            genericTextUpdate(currentShipStats, "You need to sell all your cargo before you can buy a ship!");
        }
        else if (selectedToBuy.getPrice() < player.getCredits()) {
            int price = selectedToBuy.getPrice();
            player.setShip(selectedToBuy);
            buyShipStats.setText(selectedToBuy.getType() + " purchased!");
            player.addCredits(-1 * price);
            updateShipTextArea(currentShipStats, player.getShip());
        } else {
            genericTextUpdate(currentShipStats, "You don't have enough credits to purchase that ship.");
        }
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
 
}
