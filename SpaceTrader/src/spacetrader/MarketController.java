package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import spacetrader.SpaceTrader.ControlledScreen;
import java.util.Random;

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
    @FXML private Slider waterSlider;
    @FXML private Slider furSlider;
    @FXML private Slider foodSlider;
    @FXML private Slider oreSlider;
    @FXML private Slider gameSlider;
    @FXML private Slider firearmSlider;
    @FXML private Slider medicineSlider;
    @FXML private Slider machineSlider;
    @FXML private Slider narcoticSlider;
    @FXML private Slider robotSlider;
    
    
    ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    Player player = Context.getInstance().getPlayer();
    SolarSystem currentlySelected;
    Random r = new Random();
    int[] stockGoods = new int[10];
    
    /**
     * Set the screen parent.
     * @param screenParent the screen parent
     */
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
    /**
     * Determine how many of each goods this planet stocks.
     * 
     * @param good type of good being stocked
     * @return stock of that good
     * 
     */
    public int calcStock(TradeGood good) {
        if (Context.getStock()[good.getID()] == -1) {
            Planet p = Context.getInstance().getPlayer().getCurrentPlanet();
            Event e = p.getEvent();
            Resource re = p.getResource();
            int stock = 7 + r.nextInt(14);
            if (re.getID() == good.getER().getID()) {
                stock *= .45;
            } else if (re.getID() == good.getCR().getID()) {
                stock *= 1.55;
            } else {
            }
            if (e.getID() == good.getIE().getID()) {
                stock *= .75;
            } else if (e.getID() == good.getDE().getID()) {
                stock *= 1.25;
            } else {
            }
            stockGoods[good.getID()] = stock;
            return stock;
        } else {
            return Context.getStock()[good.getID()];
        }
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
        playersWater.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.WATER)));
        waterSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.WATER));
        tradersWater.setText(String.valueOf(calcStock(TradeGood.WATER)));
        playersFur.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FURS)));
        furSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FURS));
        tradersFur.setText(String.valueOf(calcStock(TradeGood.FURS)));
        playersFood.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FOOD)));
        foodSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FOOD));
        tradersFood.setText(String.valueOf(calcStock(TradeGood.FOOD)));
        playersOre.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ORE)));
        oreSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ORE));
        tradersOre.setText(String.valueOf(calcStock(TradeGood.ORE)));
        playersGames.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.GAMES)));
        gameSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.GAMES));
        tradersGames.setText(String.valueOf(calcStock(TradeGood.GAMES)));
        playersFirearms.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FIREARMS)));
        firearmSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FIREARMS));
        tradersFirearms.setText(String.valueOf(calcStock(TradeGood.FIREARMS)));
        playersMed.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MEDICINE)));
        medicineSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MEDICINE));
        tradersMed.setText(String.valueOf(calcStock(TradeGood.MEDICINE)));
        playersMachines.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MACHINES)));
        machineSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MACHINES));
        tradersMachines.setText(String.valueOf(calcStock(TradeGood.MACHINES)));
        playersNarcotics.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.NARCOTICS)));
        narcoticSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.NARCOTICS));
        tradersNarcotics.setText(String.valueOf(calcStock(TradeGood.NARCOTICS)));
        playersRobots.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ROBOTS)));
        robotSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ROBOTS));
        tradersRobots.setText(String.valueOf(calcStock(TradeGood.ROBOTS)));
        Context.setStock(stockGoods);
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
    
    /*
    public void incrementInvestorAction() {
        double current = investorSlider.getValue();
        current += 1;
        if (checkTotals() && current <= 10) {
            investorSlider.setValue(current);
            pointsRemaining--;
            current = investorSlider.getValue();
            investorField.setText("" + ((int)current));
            upr();
        } else {
            current = investorSlider.getValue();
            upr();
        }
    }
    
    public void decrementFighterAction() {
        double current = fighterSlider.getValue();
        if (current != 0) {
            pointsRemaining++;
        }
        current -= 1;
        fighterSlider.setValue(current);
        current = fighterSlider.getValue();
        fighterField.setText("" + ((int)current));
        upr();
    } */
    
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
