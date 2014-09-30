package spacetrader;

import java.net.URL;
import java.util.HashMap;
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
    HashMap<TradeGood,Integer> stockPrices = new HashMap<>();
    HashMap<TradeGood,Integer> startCargoStock;
    int startCredits, startCargo;
    
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
            
            int goodMinTechLevelToBuy = good.getMTLB();
            int goodMinTechLevelToSell = good.getMTLS();
            int planetTechLevel = p.getTechLevel();
            int stock;
            //will not produce things from too low a tech level
            if(goodMinTechLevelToBuy > planetTechLevel){
                stock = 0;
            } else {
                stock = 7 + r.nextInt(14);
            }
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
    //demo function only
    public void cargoUp() {
        int rand = r.nextInt(3);
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.FURS, 2);
        System.out.println("furs: " + rand);
        rand = r.nextInt(3);
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.FIREARMS, 2);
        System.out.println("firearms: " + rand);
        rand = r.nextInt(3);
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.WATER, 3);
        System.out.println("water: " + rand);
        rand = r.nextInt(3);
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.FOOD, 2);
        System.out.println("food: " + rand);
        rand = r.nextInt(3);
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.NARCOTICS, 1);
        System.out.println("narcotics: " + rand);
        rand = r.nextInt(3);
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.ROBOTS, 3);
        System.out.println("robots: " + rand);
        rand = r.nextInt(3);
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.GAMES, 2);
        System.out.println("games: " + rand);
    }
    
    private void setPrices(){
        stockPrices.put(TradeGood.WATER, TradeGood.WATER.calcMarketPrice());
        stockPrices.put(TradeGood.FURS, TradeGood.FURS.calcMarketPrice());
        stockPrices.put(TradeGood.FIREARMS, TradeGood.FIREARMS.calcMarketPrice());
        stockPrices.put(TradeGood.FOOD, TradeGood.FOOD.calcMarketPrice());
        stockPrices.put(TradeGood.NARCOTICS, TradeGood.NARCOTICS.calcMarketPrice());
        stockPrices.put(TradeGood.ROBOTS, TradeGood.ROBOTS.calcMarketPrice());
        stockPrices.put(TradeGood.GAMES, TradeGood.GAMES.calcMarketPrice());
        stockPrices.put(TradeGood.MEDICINE, TradeGood.MEDICINE.calcMarketPrice());
        stockPrices.put(TradeGood.ORE, TradeGood.ORE.calcMarketPrice());
        stockPrices.put(TradeGood.MACHINES, TradeGood.MACHINES.calcMarketPrice());
        
    }
    
    /**
     * Initializes the screen.
     */
    @Override
    public void initScreen() {
        startCredits = Context.getInstance().getPlayer().getCredits();
        startCargo = Context.getInstance().getPlayer().getShip().getCurrentUsedCargoSlots();
        currentCredits();
        currentCargo();
        //cargoUp();
        setPrices();
        //set playersGood to the amount they currently have
        //set tradersGood to the amount they currently have, or to "No trade" if thats true
        //display the prices for each good via their price Label
        playersWater.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.WATER)));
        waterSlider.setValue((int)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.WATER));
        waterPrice.setText(String.valueOf(stockPrices.get(TradeGood.WATER)));
        tradersWater.setText(String.valueOf(calcStock(TradeGood.WATER)));
        playersFur.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FURS)));
        furSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FURS));
        tradersFur.setText(String.valueOf(calcStock(TradeGood.FURS)));
        furPrice.setText(String.valueOf(stockPrices.get(TradeGood.FURS)));
        playersFood.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FOOD)));
        foodSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FOOD));
        tradersFood.setText(String.valueOf(calcStock(TradeGood.FOOD)));
        foodPrice.setText(String.valueOf(stockPrices.get(TradeGood.FOOD)));
        playersOre.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ORE)));
        oreSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ORE));
        tradersOre.setText(String.valueOf(calcStock(TradeGood.ORE)));
        orePrice.setText(String.valueOf(stockPrices.get(TradeGood.ORE)));
        playersGames.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.GAMES)));
        gameSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.GAMES));
        tradersGames.setText(String.valueOf(calcStock(TradeGood.GAMES)));
        gamesPrice.setText(String.valueOf(stockPrices.get(TradeGood.GAMES)));
        playersFirearms.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FIREARMS)));
        firearmSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FIREARMS));
        tradersFirearms.setText(String.valueOf(calcStock(TradeGood.FIREARMS)));
        firearmsPrice.setText(String.valueOf(stockPrices.get(TradeGood.FIREARMS)));
        playersMed.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MEDICINE)));
        medicineSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MEDICINE));
        tradersMed.setText(String.valueOf(calcStock(TradeGood.MEDICINE)));
        medPrice.setText(String.valueOf(stockPrices.get(TradeGood.MEDICINE)));
        playersMachines.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MACHINES)));
        machineSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MACHINES));
        tradersMachines.setText(String.valueOf(calcStock(TradeGood.MACHINES)));
        machinesPrice.setText(String.valueOf(stockPrices.get(TradeGood.MACHINES)));
        playersNarcotics.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.NARCOTICS)));
        narcoticSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.NARCOTICS));
        tradersNarcotics.setText(String.valueOf(calcStock(TradeGood.NARCOTICS)));
        narcoticsPrice.setText(String.valueOf(stockPrices.get(TradeGood.NARCOTICS)));
        playersRobots.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ROBOTS)));
        robotSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ROBOTS));
        tradersRobots.setText(String.valueOf(calcStock(TradeGood.ROBOTS)));
        robotsPrice.setText(String.valueOf(stockPrices.get(TradeGood.ROBOTS)));
        Context.setStock(stockGoods);
        currentCargo();
        
        startCargoStock = Context.getInstance().getPlayer().getShip().getCargoClone();
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
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.WATER.calcMarketPrice() && Integer.parseInt(tradersWater.getText()) > 0 
                && !Context.getInstance().getPlayer().getShip().isCargoFull()) {
            waterSlider.setValue(((int)waterSlider.getValue()) + 1);
            playersWater.setText(String.valueOf(waterSlider.getValue()));
            tradersWater.setText(String.valueOf(Integer.valueOf(tradersWater.getText()) - 1));
            //waterPrice.setText(String.valueOf((int)waterSlider.getValue() * TradeGood.WATER.calcMarketPrice()));
            //waterPrice.setText(String.valueOf(stockPrices.get(TradeGood.WATER)));
            Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.WATER, 1);
            updateCredits(TradeGood.WATER, 1);
        }
        currentCargo();
    }
    public void waterDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() 
                && Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.WATER) > 0) {
        waterSlider.setValue(((int)waterSlider.getValue()) - 1);
        playersWater.setText(String.valueOf(waterSlider.getValue()));
        tradersWater.setText(String.valueOf(Integer.valueOf(tradersWater.getText()) + 1));
        //waterPrice.setText(String.valueOf((int)waterSlider.getValue() * TradeGood.WATER.calcMarketPrice()));
        //waterPrice.setText(String.valueOf(stockPrices.get(TradeGood.WATER)));
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.WATER, 1);
        updateCredits(TradeGood.WATER, -1);
        }
        currentCargo();
    }
    public void furIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.FURS.calcMarketPrice() && Integer.parseInt(tradersFur.getText()) > 0 
                && !Context.getInstance().getPlayer().getShip().isCargoFull()) {
        furSlider.setValue((double)((int)furSlider.getValue()) + 1);
        playersFur.setText(String.valueOf(furSlider.getValue()));
        tradersFur.setText(String.valueOf(Integer.valueOf(tradersFur.getText()) - 1));
       // furPrice.setText(String.valueOf((int)furSlider.getValue() * TradeGood.FURS.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.FURS, 1);
        updateCredits(TradeGood.FURS, 1);        
        }
        currentCargo();    
    }
    public void furDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() 
                && Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FURS) > 0) {
        furSlider.setValue((double)((int)furSlider.getValue()) - 1);
        playersFur.setText(String.valueOf(furSlider.getValue()));
        tradersFur.setText(String.valueOf(Integer.valueOf(tradersFur.getText()) + 1));
        //furPrice.setText(String.valueOf((int)furSlider.getValue() * TradeGood.FURS.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.FURS, 1);
        updateCredits(TradeGood.FURS, -1);        
        }
        currentCargo();    
    }
    public void foodIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.FOOD.calcMarketPrice() && Integer.parseInt(tradersFood.getText()) > 0 
            && !Context.getInstance().getPlayer().getShip().isCargoFull()) {
        foodSlider.setValue((double)((int)foodSlider.getValue()) + 1);
        playersFood.setText(String.valueOf(foodSlider.getValue()));
        tradersFood.setText(String.valueOf(Integer.valueOf(tradersFood.getText()) - 1));
        //foodPrice.setText(String.valueOf((int)foodSlider.getValue() * TradeGood.FOOD.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.FOOD, 1);
        updateCredits(TradeGood.FOOD, 1);        
        }
        currentCargo();    
    }
    public void foodDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() 
                && Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FOOD) > 0) {
        foodSlider.setValue((double)((int)foodSlider.getValue()) - 1);
        playersFood.setText(String.valueOf(foodSlider.getValue()));
        tradersFood.setText(String.valueOf(Integer.valueOf(tradersFood.getText()) + 1));
        //foodPrice.setText(String.valueOf((int)foodSlider.getValue() * TradeGood.FOOD.calcMarketPrice()));       
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.FOOD, 1);
        updateCredits(TradeGood.FOOD, -1);        
        }
        currentCargo();    
    }
    public void oreIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.ORE.calcMarketPrice() && Integer.parseInt(tradersOre.getText()) > 0 
           && !Context.getInstance().getPlayer().getShip().isCargoFull()) {
        oreSlider.setValue((double)((int)oreSlider.getValue()) + 1);
        playersOre.setText(String.valueOf(oreSlider.getValue()));
        tradersOre.setText(String.valueOf(Integer.valueOf(tradersOre.getText()) - 1));
        //orePrice.setText(String.valueOf((int)oreSlider.getValue() * TradeGood.ORE.calcMarketPrice()));
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.ORE, 1);
        updateCredits(TradeGood.ORE, 1);        
        }
        currentCargo();    
    }
    public void oreDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() 
                && Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ORE) > 0) {
        oreSlider.setValue((double)((int)oreSlider.getValue()) - 1);
        playersOre.setText(String.valueOf(oreSlider.getValue()));
        tradersOre.setText(String.valueOf(Integer.valueOf(tradersOre.getText()) + 1));
       // orePrice.setText(String.valueOf((int)oreSlider.getValue() * TradeGood.ORE.calcMarketPrice()));       
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.ORE, 1);        
        updateCredits(TradeGood.ORE, -1);
        }
        currentCargo();    
    }
    public void gamesIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.GAMES.calcMarketPrice() && Integer.parseInt(tradersGames.getText()) > 0 
            && !Context.getInstance().getPlayer().getShip().isCargoFull()) {
        gameSlider.setValue((double)((int)gameSlider.getValue()) + 1);
        playersGames.setText(String.valueOf(gameSlider.getValue()));
        tradersGames.setText(String.valueOf(Integer.valueOf(tradersGames.getText()) - 1));
        //gamesPrice.setText(String.valueOf((int)gameSlider.getValue() * TradeGood.GAMES.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.GAMES, 1);        
        updateCredits(TradeGood.GAMES, 1);        
        }
        currentCargo();    
    }
    public void gamesDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() 
                && Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.GAMES) > 0) {
        gameSlider.setValue((double)((int)gameSlider.getValue()) - 1);
        playersGames.setText(String.valueOf(gameSlider.getValue()));
        tradersGames.setText(String.valueOf(Integer.valueOf(tradersGames.getText()) + 1));
        //gamesPrice.setText(String.valueOf((int)gameSlider.getValue() * TradeGood.GAMES.calcMarketPrice()));      
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.GAMES, 1);        
        updateCredits(TradeGood.GAMES, -1);        
        }
        currentCargo();    
    }
    public void firearmsIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.FIREARMS.calcMarketPrice() && Integer.parseInt(tradersFirearms.getText()) > 0 
            && !Context.getInstance().getPlayer().getShip().isCargoFull()) {
        firearmSlider.setValue((double)((int)firearmSlider.getValue()) + 1);
        playersFirearms.setText(String.valueOf(firearmSlider.getValue()));
        tradersFirearms.setText(String.valueOf(Integer.valueOf(tradersFirearms.getText()) - 1));
        //firearmsPrice.setText(String.valueOf((int)firearmSlider.getValue() * TradeGood.FIREARMS.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.FIREARMS, 1);        
        updateCredits(TradeGood.FIREARMS, 1);        
        }
        currentCargo();    
    }
    public void firearmsDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() 
                && Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FIREARMS) > 0) {
        firearmSlider.setValue((double)((int)firearmSlider.getValue() - 1));
        playersFirearms.setText(String.valueOf(firearmSlider.getValue()));
        tradersFirearms.setText(String.valueOf(Integer.valueOf(tradersFirearms.getText()) + 1));
        //firearmsPrice.setText(String.valueOf((int)firearmSlider.getValue() * TradeGood.FIREARMS.calcMarketPrice()));       
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.FIREARMS, 1);        
        updateCredits(TradeGood.FIREARMS, -1);        
        }
        currentCargo();    
    }
    public void medIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.MEDICINE.calcMarketPrice() && Integer.parseInt(tradersMed.getText()) > 0 
                && !Context.getInstance().getPlayer().getShip().isCargoFull()) {        
        medicineSlider.setValue((double)((int)medicineSlider.getValue()) + 1);
        playersMed.setText(String.valueOf(medicineSlider.getValue()));
        tradersMed.setText(String.valueOf(Integer.valueOf(tradersMed.getText()) - 1));
        //medPrice.setText(String.valueOf((int)medicineSlider.getValue() * TradeGood.MEDICINE.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.MEDICINE, 1);    
        updateCredits(TradeGood.MEDICINE, 1);        
        }
        currentCargo();    
    }
    public void medDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() 
                && Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MEDICINE) > 0) {
        medicineSlider.setValue((double)((int)medicineSlider.getValue()) - 1);
        playersMed.setText(String.valueOf(medicineSlider.getValue()));
        tradersMed.setText(String.valueOf(Integer.valueOf(tradersMed.getText()) + 1));
       // medPrice.setText(String.valueOf((int)medicineSlider.getValue() * TradeGood.MEDICINE.calcMarketPrice()));       
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.MEDICINE, 1);
        updateCredits(TradeGood.MEDICINE, -1);        
        }
        currentCargo();    
    }
    public void machinesIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.MACHINES.calcMarketPrice() && Integer.parseInt(tradersMachines.getText()) > 0 
                && !Context.getInstance().getPlayer().getShip().isCargoFull()) {        
        machineSlider.setValue((double)((int)machineSlider.getValue()) + 1);
        playersMachines.setText(String.valueOf(machineSlider.getValue()));
        tradersMachines.setText(String.valueOf(Integer.valueOf(tradersMachines.getText()) - 1));
       // machinesPrice.setText(String.valueOf((int)machineSlider.getValue() * TradeGood.MACHINES.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.MACHINES, 1);
        updateCredits(TradeGood.MACHINES, 1);        
        }
        currentCargo();    
    }
    public void machinesDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() 
                && Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MACHINES) > 0) {        
        machineSlider.setValue((double)((int)machineSlider.getValue() - 1));
        playersMachines.setText(String.valueOf(machineSlider.getValue()));
        tradersMachines.setText(String.valueOf(Integer.valueOf(tradersMachines.getText()) + 1));
       // machinesPrice.setText(String.valueOf((int)machineSlider.getValue() * TradeGood.MACHINES.calcMarketPrice()));       
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.MACHINES, 1);
        updateCredits(TradeGood.MACHINES, -1);        
        }
        currentCargo();    
    }
    public void narcoticsIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.NARCOTICS.calcMarketPrice() && Integer.parseInt(tradersNarcotics.getText()) > 0 
                && !Context.getInstance().getPlayer().getShip().isCargoFull()) {
        narcoticSlider.setValue((double)((int)narcoticSlider.getValue()) + 1);
        playersNarcotics.setText(String.valueOf(narcoticSlider.getValue()));
        tradersNarcotics.setText(String.valueOf(Integer.valueOf(tradersNarcotics.getText()) - 1));
        //narcoticsPrice.setText(String.valueOf((int)narcoticSlider.getValue() * TradeGood.NARCOTICS.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.NARCOTICS, 1);
        updateCredits(TradeGood.NARCOTICS, 1);        
        }
        currentCargo();    
    }
    public void narcoticsDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() 
                && Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.NARCOTICS) > 0) {        
        narcoticSlider.setValue((double)((int)narcoticSlider.getValue()) - 1);
        playersNarcotics.setText(String.valueOf(narcoticSlider.getValue()));
        tradersNarcotics.setText(String.valueOf(Integer.valueOf(tradersNarcotics.getText()) + 1));
        //narcoticsPrice.setText(String.valueOf((int)narcoticSlider.getValue() * TradeGood.NARCOTICS.calcMarketPrice()));      
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.NARCOTICS, 1);        
        updateCredits(TradeGood.NARCOTICS, -1);        
        }
        currentCargo();    
    }
    public void robotsIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.ROBOTS.calcMarketPrice() && Integer.parseInt(tradersRobots.getText()) > 0 
            && !Context.getInstance().getPlayer().getShip().isCargoFull()) {
        robotSlider.setValue((double)((int)robotSlider.getValue()) + 1);
        playersRobots.setText(String.valueOf(robotSlider.getValue()));
        tradersRobots.setText(String.valueOf(Integer.valueOf(tradersRobots.getText()) - 1));
        //robotsPrice.setText(String.valueOf((int)robotSlider.getValue() * TradeGood.ROBOTS.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.ROBOTS, 1);
        updateCredits(TradeGood.ROBOTS, 1);        
        }
        currentCargo();    
    }
    public void robotsDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() 
                && Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ROBOTS) > 0) {
        robotSlider.setValue((double)((int)robotSlider.getValue()) - 1);
        playersRobots.setText(String.valueOf(robotSlider.getValue()));
        tradersRobots.setText(String.valueOf(Integer.valueOf(tradersRobots.getText()) + 1));
       // robotsPrice.setText(String.valueOf((int)robotSlider.getValue() * TradeGood.ROBOTS.calcMarketPrice()));      
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.ROBOTS, 1);        
        updateCredits(TradeGood.ROBOTS, -1);        
        }
        currentCargo();    
    }
    
    public void updateCredits(TradeGood traded, int num) {
        if (num < 0) {
            int absNum = Math.abs(num);
            Context.getInstance().getPlayer().addCredits(absNum * stockPrices.get(traded));
        } else if (num > 0) {
            Context.getInstance().getPlayer().removeCredits(num * stockPrices.get(traded));
        }
        currentCredits();
    }
    
    public void confirmAction() {
        currentCargo();
        currentCredits();
        controller.setScreen("PlanetScreen");
        //do stuff
        //go back to PlanetScreen
    }
    
    public void backAction() {
        Context.getInstance().getPlayer().removeCredits(Context.getInstance().getPlayer().getCredits());
        Context.getInstance().getPlayer().addCredits(startCredits);
        Context.getInstance().getPlayer().getShip().setCargo(startCargoStock,startCargo);
        System.out.println(Context.getInstance().getPlayer().getShip().getCargo().get(TradeGood.WATER));
        //need to reset stock
        controller.setScreen("PlanetScreen");
    }
    
}
