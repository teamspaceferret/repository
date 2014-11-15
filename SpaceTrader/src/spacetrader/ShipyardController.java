package spacetrader;

import java.net.URL;
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
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

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
    @FXML private Button gnatStats;
    @FXML private Button fireflyStats;
    @FXML private Button mosquitoStats;
    @FXML private Button bumblebeeStats;
    
    @FXML private TextArea buyShipStats;
    @FXML private TextArea currentShipStats;
    
    @FXML private TextField playerFuel;
    @FXML private TextField playerCargoSlots;
    @FXML private TextField playerWeapon1;
    @FXML private TextField playerWeapon2;
    @FXML private TextField playerWeapon3;
    @FXML private TextField playerShield1;
    @FXML private TextField playerShield2;
    
    @FXML private Label weapon1;
    @FXML private Label weapon2;
    @FXML private Label weapon3;
    @FXML private Label shield1;
    @FXML private Label shield2;
    
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
    
    @FXML private Label playerCreditNum1; //credits on upgrade ship
    @FXML private Label playerCreditNum2; //credits on buy new ship
    @FXML private Tab weaponsTab;
    @FXML private Tab shipTab;
    @FXML private TabPane tabPane;
    
    @FXML private MenuItem optionsButton;
    
    
    private ScreensController controller;

    //Player player = Context.getInstance().getPlayer();
    //Ship selectedToBuy;
    
    SoundManager soundManager = SoundManager.getSoundManager();

    private Universe universe = Context.getInstance().getUniverse();
    private Player player = Context.getInstance().getPlayer();
    private Ship selectedToBuy;
    private Shipyard shipyard;
    private Weapon[] weaponStock;
    private Shield[] shieldStock;

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
    //flea, gnat, firefly, mosquito, bumblebee
    public final void initScreen() {
        soundManager.setPrevScreen("Shipyard");
        
        optionsButton.setAccelerator(new KeyCodeCombination(KeyCode.ESCAPE));

        universe = Context.getInstance().getUniverse();
        player = Context.getInstance().getPlayer();
        shipyard = player.getCurrentPlanet().getShipyard();
        Planet planet = player.getCurrentPlanet();
        int techLevel = planet.getTechLevel();
        weaponStock = shipyard.getWeaponStock();
        shieldStock = shipyard.getShieldStock();
        //to make sure we have creds for demo-ing
        //if (player.getCredits() < 1000000) {
          //player.addCredits(1000000);
        //}
        updateShipTextArea(currentShipStats, player.getShip());
        selectedToBuy = null;
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
        if (techLevel < new Ship("mosquito").getMinTechLevel()) {
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
        int matterCloak = Shipyard.MATTER_CLOAK_IDX;
        int turret = Shipyard.TURRET_IDX;
        weapon1Cost.setText("" + weaponStock[Shipyard.TURRET_IDX].getPrice());
        weapon2Cost.setText("" + weaponStock[Shipyard.CANNON_IDX].getPrice());
        weapon3Cost.setText("" + weaponStock[Shipyard.OCULASER_IDX].getPrice());
        shield1Cost.setText("" + shieldStock[Shipyard.PLATE_IDX].getPrice());
        shield2Cost.setText("" + shieldStock[matterCloak].getPrice());
        weapon1.setText("" + weaponStock[Shipyard.TURRET_IDX].getDamage());
        weapon2.setText("" + weaponStock[Shipyard.CANNON_IDX].getDamage());
        weapon3.setText("" + weaponStock[Shipyard.OCULASER_IDX].getDamage());
        shield1.setText("" + shieldStock[Shipyard.PLATE_IDX].getHealth());
        shield2.setText("" + shieldStock[matterCloak].getHealth());
        weapon1Name.setText(weaponStock[Shipyard.TURRET_IDX].getName());
        weapon2Name.setText(weaponStock[Shipyard.CANNON_IDX].getName());
        weapon3Name.setText(weaponStock[Shipyard.OCULASER_IDX].getName());
        shield1Name.setText(shieldStock[Shipyard.PLATE_IDX].getName());
        shield2Name.setText(shieldStock[Shipyard.MATTER_CLOAK_IDX].getName());
        //System.out.println(player.getCurrentPlanet().getTechLevel());
        int currentPlanetTechLevel = player.getCurrentPlanet().getTechLevel();
        int cannon = Shipyard.CANNON_IDX;
        int laser = Shipyard.OCULASER_IDX;
        int plate = Shipyard.PLATE_IDX;
        if (currentPlanetTechLevel < weaponStock[turret].getTechLevel()) {
            plusWeapon1.setDisable(true);
            minusWeapon1.setDisable(true);
        }
        if (currentPlanetTechLevel < weaponStock[cannon].getTechLevel()) {
            plusWeapon2.setDisable(true);
            minusWeapon2.setDisable(true);
        }
        if (currentPlanetTechLevel < weaponStock[laser].getTechLevel()) {
            plusWeapon3.setDisable(true);
            minusWeapon3.setDisable(true);
        }
        if (currentPlanetTechLevel < shieldStock[plate].getTechLevel()) {
            plusShield1.setDisable(true);
            minusShield1.setDisable(true);
        }
        if (currentPlanetTechLevel < shieldStock[matterCloak].getTechLevel()) {
            plusShield2.setDisable(true);
            minusShield2.setDisable(true);
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
        int amountToFill = player.getShip().getMaxFuelLevel()
                - player.getShip().getFuelLevel();
        String[] prices = new String[12];
        String[] priceStrings = Context.PRICES;
        Label[] labels = mapIntsToLabels();
        prices[0] = priceStrings[0]; //weapon1
        prices[1] = priceStrings[1]; //weapon2
        prices[2] = priceStrings[2]; //weapon3
        prices[3] = priceStrings[3]; //shield1
        prices[4] = priceStrings[4]; //shield2
        prices[5] = "" + amountToFill * player.getShip().getFuelCost();
        prices[6] = priceStrings[6];
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
        int weapon1num = 0;
        int weapon2num = 0;
        int weapon3num = 0;
        int shield1num = 0;
        int shield2num = 0;
        String turret = weaponStock[Shipyard.TURRET_IDX].getName();
        String cannon = weaponStock[Shipyard.CANNON_IDX].getName();
        String laser = weaponStock[Shipyard.OCULASER_IDX].getName();
        String plate = shieldStock[Shipyard.PLATE_IDX].getName();
        String matterCloak = shieldStock[Shipyard.MATTER_CLOAK_IDX].getName();
        for (Weapon w : currentShipWeapons) {
            if (w != null) {
                //System.out.println(w.getName());
                if (w.getName().equalsIgnoreCase(turret)) {
                    weapon1num += 1;
                } else if (w.getName().equalsIgnoreCase(cannon)) {
                    weapon2num += 1;
                } else if (w.getName().equalsIgnoreCase(laser)) {
                    weapon3num += 1;
                }
            }
        }
        for (Shield s : currentShipShields) {
            //System.out.println(s.getName());
            if (s.getName().equalsIgnoreCase(plate)) {
                shield1num += 1;
            } else if (s.getName().equalsIgnoreCase(matterCloak)) {
                shield2num += 1;
            }
        }
        playerWeapon1.setText("" + weapon1num);
        playerWeapon2.setText("" + weapon2num);
        playerWeapon3.setText("" + weapon3num);
        playerShield1.setText("" + shield1num);
        playerShield2.setText("" + shield2num);
    }
    
    public final void weapon1Increment() {
        ArrayList<Weapon> currentShipWeapons = player.getShip().getWeapons();
        shipyard = player.getCurrentPlanet().getShipyard();
        int wPrice = shipyard.getWeaponStock()[0].getPrice();
        if (player.getShip().getMaxWeapons() > currentShipWeapons.size()
                && player.removeCredits(wPrice)) {
            currentShipWeapons.add(shipyard.getWeaponStock()[0]);
        }
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public final void weapon1Decrement() {
        ArrayList<Weapon> currentShipWeapons = player.getShip().getWeapons();
        shipyard = player.getCurrentPlanet().getShipyard();
        int wPrice = shipyard.getWeaponStock()[0].getPrice();
        if (currentShipWeapons.contains(shipyard.getWeaponStock()[0])
                && player.addCredits(wPrice)) {
            currentShipWeapons.remove(shipyard.getWeaponStock()[0]);
        }
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public final void weapon2Increment() {
        ArrayList<Weapon> currentShipWeapons = player.getShip().getWeapons();
        shipyard = player.getCurrentPlanet().getShipyard();
        int wPrice = shipyard.getWeaponStock()[1].getPrice();
        if (player.getShip().getMaxWeapons() > currentShipWeapons.size()
                && player.removeCredits(wPrice)) {
            currentShipWeapons.add(shipyard.getWeaponStock()[1]);
        }
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }

    public final void weapon2Decrement() {
        ArrayList<Weapon> currentShipWeapons = player.getShip().getWeapons();
        shipyard = player.getCurrentPlanet().getShipyard();
        int wPrice = shipyard.getWeaponStock()[1].getPrice();
        if (currentShipWeapons.contains(shipyard.getWeaponStock()[1])
                && player.addCredits(wPrice)) {
            currentShipWeapons.remove(shipyard.getWeaponStock()[1]);
        }
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public final void weapon3Increment() {
        ArrayList<Weapon> currentShipWeapons = player.getShip().getWeapons();
        shipyard = player.getCurrentPlanet().getShipyard();
        int wPrice = shipyard.getWeaponStock()[2].getPrice();
        if (player.getShip().getMaxWeapons() > currentShipWeapons.size()
                && player.removeCredits(wPrice)) {
            currentShipWeapons.add(shipyard.getWeaponStock()[2]);
        }
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }

    public final void weapon3Decrement() {
        ArrayList<Weapon> currentShipWeapons = player.getShip().getWeapons();
        shipyard = player.getCurrentPlanet().getShipyard();
        int wPrice = shipyard.getWeaponStock()[2].getPrice();
        if (currentShipWeapons.contains(shipyard.getWeaponStock()[2])
                && player.addCredits(wPrice)) {
            currentShipWeapons.remove(shipyard.getWeaponStock()[2]);
        }
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public final void shield1Increment() {
        shipyard = player.getCurrentPlanet().getShipyard();
        int shieldStock = shipyard.getShieldStock()[0].getPrice();
        ArrayList<Shield> currentShipShields = player.getShip().getShields();
        if (player.getShip().getMaxShields() > currentShipShields.size()
                && player.removeCredits(shieldStock)) {
            currentShipShields.add(shipyard.getShieldStock()[0]);
        }
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public final void shield1Decrement() {
        shipyard = player.getCurrentPlanet().getShipyard();
        ArrayList<Shield> currentShipShields = player.getShip().getShields();
        if (currentShipShields.contains(shipyard.getShieldStock()[0])
                && player.addCredits(shipyard.getShieldStock()[0].getPrice())) {
            currentShipShields.remove(shipyard.getShieldStock()[0]);
        }
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public final void shield2Increment() {
        shipyard = player.getCurrentPlanet().getShipyard();
        int toRemove = shipyard.getShieldStock()[1].getPrice();
        ArrayList<Shield> currentShipShields = player.getShip().getShields();
        if (player.getShip().getMaxShields() > currentShipShields.size()
                && player.removeCredits(toRemove)) {
            currentShipShields.add(shipyard.getShieldStock()[1]);
        }
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
        
    }
    
    public final void shield2Decrement() {
        ArrayList<Shield> currentShipShields = player.getShip().getShields();
        shipyard = player.getCurrentPlanet().getShipyard();
        if (currentShipShields.contains(shipyard.getShieldStock()[1])
                && player.addCredits(shipyard.getShieldStock()[1].getPrice())) {
            currentShipShields.remove(shipyard.getShieldStock()[1]);
        }
        updateQuantities();
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    /**
     * Fills the player's ship up with fuel if they are missing fuel
     * and have enough money to do so.
     */
    public final void fillTank() {
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
    
    public final void cargoSlotsIncrement() {
        
    }
    
    public final void cargoSlotsDecrement() {
        
    }
    
    //updates right TextArea with flea information
    public final void fleaStats() {
        updateShipTextArea(buyShipStats, new Ship("flea"));
    }
    
    //updates right TextArea with gnat information
    public final void gnatStats() {
        updateShipTextArea(buyShipStats, new Ship("gnat"));
    }

    //updates right TextArea with firefly information
    public final void fireflyStats() {
        updateShipTextArea(buyShipStats, new Ship("firefly"));
    }
    
    //updates right TextArea with mosquito information    
    public final void mosquitoStats() {
        updateShipTextArea(buyShipStats, new Ship("mosquito"));
    }
    
    //updates right TextArea with bumblebee information    
    public final void bumblebeeStats() {
        updateShipTextArea(buyShipStats, new Ship("bumblebee"));
    }
    
    /**
     * returns to previous screen, saving changes that have occurred.
     */
    public final void back() {
        controller.setScreen("PlanetScreen");
        buyShipStats.clear();
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(weaponsTab);
    }
    
    public final void confirm() {
        /*controller.setScreen("PlanetScreen");
        buyShipStats.clear();
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(weaponsTab);*/
    }
    
    /**
     * Gives the player a new ship if they have no cargo, they have selected a 
     * ship to buy, and they have enough money to do so.
     */
    public final void buyShip() {
        int usedSlots = player.getShip().getCurrentUsedCargoSlots();
        if (selectedToBuy == null) {
            buyShipStats.setText("Select a ship to buy by clicking one of "
                    + "the 5 buttons.");
        } else if (usedSlots > 0) {
            genericTextUpdate(currentShipStats, "You need to sell all your "
                    + "cargo before you can buy a ship!");
        } else if (selectedToBuy.getPrice() < player.getCredits()) {
            int price = selectedToBuy.getPrice();
            player.setShip(selectedToBuy);
            buyShipStats.setText(selectedToBuy.getType() + " purchased!");
            player.addCredits(-1 * price);
            updateShipTextArea(currentShipStats, player.getShip());
        } else {
            genericTextUpdate(currentShipStats, "You don't have enough credits "
                    + "to purchase that ship.");
        }
        playerCreditNum1.setText(String.valueOf(player.getCredits()));
        playerCreditNum2.setText(String.valueOf(player.getCredits()));
    }
    
    public final void optionsAction(){
        controller.setScreen("OptionsScreen");
    }
 
}
