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

/**
 * Controls the Market.fxml file.
 * 
 */
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
    static Random r = new Random();
    static int[] stockGoods = new int[10];
    HashMap<TradeGood,Integer> stockPrices = new HashMap<>();
    HashMap<TradeGood,Integer> startCargoStock;
    //hashmap of all goods and whether or not they are available
    HashMap<TradeGood, Boolean> goodsBeingTraded = new HashMap<>();
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
     * Initializes the controller class.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     * Initializes the screen.
     */
    @Override
    public void initScreen() {
        universe = Context.getInstance().getUniverse();
        player = Context.getInstance().getPlayer();
        startCredits = player.getCredits();
        startCargo = player.getShip().getCurrentUsedCargoSlots();
        currentCredits();
        currentCargo();
        setPrices();
        //set playersGood to the amount they currently have
        //set tradersGood to the amount they currently have, or to "No trade" if thats true
        //display the prices for each good via their price Label
        playersWater.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.WATER)));
        waterSlider.setValue((int)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.WATER));
        waterPrice.setText(String.valueOf(stockPrices.get(TradeGood.WATER)));
        tradersWater.setText(String.valueOf(Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getStockIndex(0)));
        playersFur.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FURS)));
        furSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FURS));
        tradersFur.setText(String.valueOf(Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getStockIndex(1)));
        furPrice.setText(String.valueOf(stockPrices.get(TradeGood.FURS)));
        playersFood.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FOOD)));
        foodSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FOOD));
        tradersFood.setText(String.valueOf(Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getStockIndex(2)));
        foodPrice.setText(String.valueOf(stockPrices.get(TradeGood.FOOD)));
        playersOre.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ORE)));
        oreSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ORE));
        tradersOre.setText(String.valueOf(Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getStockIndex(3)));
        orePrice.setText(String.valueOf(stockPrices.get(TradeGood.ORE)));
        playersGames.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.GAMES)));
        gameSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.GAMES));
        tradersGames.setText(String.valueOf(Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getStockIndex(4)));
        gamesPrice.setText(String.valueOf(stockPrices.get(TradeGood.GAMES)));
        playersFirearms.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FIREARMS)));
        firearmSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FIREARMS));
        tradersFirearms.setText(String.valueOf(Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getStockIndex(5)));
        firearmsPrice.setText(String.valueOf(stockPrices.get(TradeGood.FIREARMS)));
        playersMed.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MEDICINE)));
        medicineSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MEDICINE));
        tradersMed.setText(String.valueOf(Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getStockIndex(6)));
        medPrice.setText(String.valueOf(stockPrices.get(TradeGood.MEDICINE)));
        playersMachines.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MACHINES)));
        machineSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MACHINES));
        tradersMachines.setText(String.valueOf(Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getStockIndex(7)));
        machinesPrice.setText(String.valueOf(stockPrices.get(TradeGood.MACHINES)));
        playersNarcotics.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.NARCOTICS)));
        narcoticSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.NARCOTICS));
        tradersNarcotics.setText(String.valueOf(Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getStockIndex(8)));
        narcoticsPrice.setText(String.valueOf(stockPrices.get(TradeGood.NARCOTICS)));
        playersRobots.setText(String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ROBOTS)));
        robotSlider.setValue((double)Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ROBOTS));
        tradersRobots.setText(String.valueOf(Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getStockIndex(9)));
        robotsPrice.setText(String.valueOf(stockPrices.get(TradeGood.ROBOTS)));
        Context.getInstance().setStock(stockGoods);
        currentCargo();
        
        startCargoStock = Context.getInstance().getPlayer().getShip().getCargoClone();
        setNoTrade();
    }
    
    /**
     * Determine how many of each goods this planet stocks.
     * 
     * @param good type of good being stocked
     * @return stock of that good
     * 
     */
    public static int calcStock(TradeGood good) {
        if (Context.getInstance().getStock()[good.getID()] == -1) {
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
            return Context.getInstance().getStock()[good.getID()];
        }
    }
    //demo function only

    /**
     * Loads the player up with some cargo. For demo use only!
     */
        public void cargoUp() {
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.FURS, 2);
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.FIREARMS, 2);
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.WATER, 3);
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.FOOD, 2);
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.NARCOTICS, 1);
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.ROBOTS, 3);
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.GAMES, 2);
    }
    
    /**
     * Set the prices of the market
     */
    public void setPrices(){
        stockPrices.put(TradeGood.WATER, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[0]);
        stockPrices.put(TradeGood.FURS, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[1]);
        stockPrices.put(TradeGood.FIREARMS, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[5]);
        stockPrices.put(TradeGood.FOOD, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[2]);
        stockPrices.put(TradeGood.NARCOTICS, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[8]);
        stockPrices.put(TradeGood.ROBOTS, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[9]);
        stockPrices.put(TradeGood.GAMES, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[4]);
        stockPrices.put(TradeGood.MEDICINE, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[6]);
        stockPrices.put(TradeGood.ORE, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[3]);
        stockPrices.put(TradeGood.MACHINES, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[7]);
    }
    
    /**
     * Displays "No Trade" beside items that cannot be bought/sold, either
     * because of tech level or governments
     */
    public void setNoTrade(){
        Planet planet = player.getCurrentPlanet();
        for(TradeGood good : TradeGood.values()) {
            //if tech level is too low to buy that here
            if(good.getMTLB() > planet.getTechLevel()) {
                goodsBeingTraded.put(good, false);
                System.out.println("No trade: " + good.toString());
            } else {
                goodsBeingTraded.put(good, true);
            }
        }
        //when buying/selling check if the good is on this list
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
    
    /**
     * Buy one water
     */
    public void waterIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.WATER.calcMarketPrice() && Integer.parseInt(tradersWater.getText()) > 0 
                && !Context.getInstance().getPlayer().getShip().isCargoFull() && goodsBeingTraded.get(TradeGood.WATER)) {
            waterSlider.setValue(((int)waterSlider.getValue()) + 1);
            playersWater.setText(String.valueOf((int)waterSlider.getValue()));
            tradersWater.setText(String.valueOf(Integer.valueOf(tradersWater.getText()) - 1));
            //waterPrice.setText(String.valueOf((int)waterSlider.getValue() * TradeGood.WATER.calcMarketPrice()));
            //waterPrice.setText(String.valueOf(stockPrices.get(TradeGood.WATER)));
            Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.WATER, 1);
            updateCredits(TradeGood.WATER, 1);
        }
        currentCargo();
    }

    /**
     * sell one water
     */
    public void waterDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() && !tradersWater.getText().equals("0")
                && (Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.WATER) > 0) 
                && goodsBeingTraded.get(TradeGood.WATER)) {
        waterSlider.setValue(((int)waterSlider.getValue()) - 1);
        playersWater.setText(String.valueOf((int)waterSlider.getValue()));
        tradersWater.setText(String.valueOf(Integer.valueOf(tradersWater.getText()) + 1));
        //waterPrice.setText(String.valueOf((int)waterSlider.getValue() * TradeGood.WATER.calcMarketPrice()));
        //waterPrice.setText(String.valueOf(stockPrices.get(TradeGood.WATER)));
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.WATER, 1);
        updateCredits(TradeGood.WATER, -1);
        }
        currentCargo();
    }

    /**
     * buy one fur
     */
    public void furIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.FURS.calcMarketPrice() && Integer.parseInt(tradersFur.getText()) > 0 
                && !Context.getInstance().getPlayer().getShip().isCargoFull()  && goodsBeingTraded.get(TradeGood.FURS)) {
        furSlider.setValue((double)((int)furSlider.getValue()) + 1);
        playersFur.setText(String.valueOf((int)furSlider.getValue()));
        tradersFur.setText(String.valueOf(Integer.valueOf(tradersFur.getText()) - 1));
       // furPrice.setText(String.valueOf((int)furSlider.getValue() * TradeGood.FURS.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.FURS, 1);
        updateCredits(TradeGood.FURS, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one fur
     */
    public void furDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() && !tradersFur.getText().equals("0")
                && (Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FURS) > 0)
                && goodsBeingTraded.get(TradeGood.FURS)){
        furSlider.setValue((double)((int)furSlider.getValue()) - 1);
        playersFur.setText(String.valueOf((int)furSlider.getValue()));
        tradersFur.setText(String.valueOf(Integer.valueOf(tradersFur.getText()) + 1));
        //furPrice.setText(String.valueOf((int)furSlider.getValue() * TradeGood.FURS.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.FURS, 1);
        updateCredits(TradeGood.FURS, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one food
     */
    public void foodIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.FOOD.calcMarketPrice() && Integer.parseInt(tradersFood.getText()) > 0 
            && !Context.getInstance().getPlayer().getShip().isCargoFull() && goodsBeingTraded.get(TradeGood.FOOD)) {
        foodSlider.setValue((double)((int)foodSlider.getValue()) + 1);
        playersFood.setText(String.valueOf((int)foodSlider.getValue()));
        tradersFood.setText(String.valueOf(Integer.valueOf(tradersFood.getText()) - 1));
        //foodPrice.setText(String.valueOf((int)foodSlider.getValue() * TradeGood.FOOD.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.FOOD, 1);
        updateCredits(TradeGood.FOOD, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one food
     */
    public void foodDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() && !tradersFood.getText().equals("0")
                && (Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FOOD) > 0)
                && goodsBeingTraded.get(TradeGood.FOOD)){
        foodSlider.setValue((double)((int)foodSlider.getValue()) - 1);
        playersFood.setText(String.valueOf((int)foodSlider.getValue()));
        tradersFood.setText(String.valueOf(Integer.valueOf(tradersFood.getText()) + 1));
        //foodPrice.setText(String.valueOf((int)foodSlider.getValue() * TradeGood.FOOD.calcMarketPrice()));       
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.FOOD, 1);
        updateCredits(TradeGood.FOOD, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one ore
     */
    public void oreIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.ORE.calcMarketPrice() && Integer.parseInt(tradersOre.getText()) > 0 
           && !Context.getInstance().getPlayer().getShip().isCargoFull()  && goodsBeingTraded.get(TradeGood.ORE)) {
        oreSlider.setValue((double)((int)oreSlider.getValue()) + 1);
        playersOre.setText(String.valueOf((int)oreSlider.getValue()));
        tradersOre.setText(String.valueOf(Integer.valueOf(tradersOre.getText()) - 1));
        //orePrice.setText(String.valueOf((int)oreSlider.getValue() * TradeGood.ORE.calcMarketPrice()));
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.ORE, 1);
        updateCredits(TradeGood.ORE, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one ore
     */
    public void oreDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() && !tradersOre.getText().equals("0")
                && (Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ORE) > 0)
                && goodsBeingTraded.get(TradeGood.ORE)){
        oreSlider.setValue((double)((int)oreSlider.getValue()) - 1);
        playersOre.setText(String.valueOf((int)oreSlider.getValue()));
        tradersOre.setText(String.valueOf(Integer.valueOf(tradersOre.getText()) + 1));
       // orePrice.setText(String.valueOf((int)oreSlider.getValue() * TradeGood.ORE.calcMarketPrice()));       
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.ORE, 1);        
        updateCredits(TradeGood.ORE, -1);
        }
        currentCargo();    
    }

    /**
     * buy one game
     */
    public void gamesIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.GAMES.calcMarketPrice() && Integer.parseInt(tradersGames.getText()) > 0 
            && !Context.getInstance().getPlayer().getShip().isCargoFull()  && goodsBeingTraded.get(TradeGood.GAMES)) {
        gameSlider.setValue((double)((int)gameSlider.getValue()) + 1);
        playersGames.setText(String.valueOf((int)gameSlider.getValue()));
        tradersGames.setText(String.valueOf(Integer.valueOf(tradersGames.getText()) - 1));
        //gamesPrice.setText(String.valueOf((int)gameSlider.getValue() * TradeGood.GAMES.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.GAMES, 1);        
        updateCredits(TradeGood.GAMES, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one game
     */
    public void gamesDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() && !tradersGames.getText().equals("0")
                && (Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.GAMES) > 0)
                && goodsBeingTraded.get(TradeGood.GAMES)) {
        gameSlider.setValue((double)((int)gameSlider.getValue()) - 1);
        playersGames.setText(String.valueOf((int)gameSlider.getValue()));
        tradersGames.setText(String.valueOf(Integer.valueOf(tradersGames.getText()) + 1));
        //gamesPrice.setText(String.valueOf((int)gameSlider.getValue() * TradeGood.GAMES.calcMarketPrice()));      
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.GAMES, 1);        
        updateCredits(TradeGood.GAMES, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one firearm
     */
    public void firearmsIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.FIREARMS.calcMarketPrice() && Integer.parseInt(tradersFirearms.getText()) > 0 
            && !Context.getInstance().getPlayer().getShip().isCargoFull()  && goodsBeingTraded.get(TradeGood.FIREARMS)) {
        firearmSlider.setValue((double)((int)firearmSlider.getValue()) + 1);
        playersFirearms.setText(String.valueOf((int)firearmSlider.getValue()));
        tradersFirearms.setText(String.valueOf(Integer.valueOf(tradersFirearms.getText()) - 1));
        //firearmsPrice.setText(String.valueOf((int)firearmSlider.getValue() * TradeGood.FIREARMS.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.FIREARMS, 1);        
        updateCredits(TradeGood.FIREARMS, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one firearm
     */
    public void firearmsDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() && !tradersFirearms.getText().equals("0")
                && (Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.FIREARMS) > 0)
                && goodsBeingTraded.get(TradeGood.FIREARMS)){
        firearmSlider.setValue((double)((int)firearmSlider.getValue() - 1));
        playersFirearms.setText(String.valueOf((int)firearmSlider.getValue()));
        tradersFirearms.setText(String.valueOf(Integer.valueOf(tradersFirearms.getText()) + 1));
        //firearmsPrice.setText(String.valueOf((int)firearmSlider.getValue() * TradeGood.FIREARMS.calcMarketPrice()));       
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.FIREARMS, 1);        
        updateCredits(TradeGood.FIREARMS, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one medicine
     */
    public void medIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.MEDICINE.calcMarketPrice() && Integer.parseInt(tradersMed.getText()) > 0 
                && !Context.getInstance().getPlayer().getShip().isCargoFull()  && goodsBeingTraded.get(TradeGood.MEDICINE)) {        
        medicineSlider.setValue((double)((int)medicineSlider.getValue()) + 1);
        playersMed.setText(String.valueOf((int)medicineSlider.getValue()));
        tradersMed.setText(String.valueOf(Integer.valueOf(tradersMed.getText()) - 1));
        //medPrice.setText(String.valueOf((int)medicineSlider.getValue() * TradeGood.MEDICINE.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.MEDICINE, 1);    
        updateCredits(TradeGood.MEDICINE, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one medicine
     */
    public void medDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() && !tradersMed.getText().equals("0")
                && (Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MEDICINE) > 0)
                && goodsBeingTraded.get(TradeGood.MEDICINE)){
        medicineSlider.setValue((double)((int)medicineSlider.getValue()) - 1);
        playersMed.setText(String.valueOf((int)medicineSlider.getValue()));
        tradersMed.setText(String.valueOf(Integer.valueOf(tradersMed.getText()) + 1));
       // medPrice.setText(String.valueOf((int)medicineSlider.getValue() * TradeGood.MEDICINE.calcMarketPrice()));       
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.MEDICINE, 1);
        updateCredits(TradeGood.MEDICINE, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one machine
     */
    public void machinesIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.MACHINES.calcMarketPrice() && Integer.parseInt(tradersMachines.getText()) > 0 
                && !Context.getInstance().getPlayer().getShip().isCargoFull() && goodsBeingTraded.get(TradeGood.MACHINES)) {        
        machineSlider.setValue((double)((int)machineSlider.getValue()) + 1);
        playersMachines.setText(String.valueOf((int)machineSlider.getValue()));
        tradersMachines.setText(String.valueOf(Integer.valueOf(tradersMachines.getText()) - 1));
       // machinesPrice.setText(String.valueOf((int)machineSlider.getValue() * TradeGood.MACHINES.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.MACHINES, 1);
        updateCredits(TradeGood.MACHINES, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one machine
     */
    public void machinesDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() && !tradersMachines.getText().equals("0")
                && (Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.MACHINES) > 0)
                && goodsBeingTraded.get(TradeGood.MACHINES)){        
        machineSlider.setValue((double)((int)machineSlider.getValue() - 1));
        playersMachines.setText(String.valueOf((int)machineSlider.getValue()));
        tradersMachines.setText(String.valueOf(Integer.valueOf(tradersMachines.getText()) + 1));
       // machinesPrice.setText(String.valueOf((int)machineSlider.getValue() * TradeGood.MACHINES.calcMarketPrice()));       
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.MACHINES, 1);
        updateCredits(TradeGood.MACHINES, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one narcotics
     */
    public void narcoticsIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.NARCOTICS.calcMarketPrice() && Integer.parseInt(tradersNarcotics.getText()) > 0 
                && !Context.getInstance().getPlayer().getShip().isCargoFull() && goodsBeingTraded.get(TradeGood.NARCOTICS)) {
        narcoticSlider.setValue((double)((int)narcoticSlider.getValue()) + 1);
        playersNarcotics.setText(String.valueOf((int)narcoticSlider.getValue()));
        tradersNarcotics.setText(String.valueOf(Integer.valueOf(tradersNarcotics.getText()) - 1));
        //narcoticsPrice.setText(String.valueOf((int)narcoticSlider.getValue() * TradeGood.NARCOTICS.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.NARCOTICS, 1);
        updateCredits(TradeGood.NARCOTICS, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one narcotics
     */
    public void narcoticsDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() && !tradersNarcotics.getText().equals("0")
                && (Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.NARCOTICS) > 0)
                && goodsBeingTraded.get(TradeGood.NARCOTICS)){       
        narcoticSlider.setValue((double)((int)narcoticSlider.getValue()) - 1);
        playersNarcotics.setText(String.valueOf((int)narcoticSlider.getValue()));
        tradersNarcotics.setText(String.valueOf(Integer.valueOf(tradersNarcotics.getText()) + 1));
        //narcoticsPrice.setText(String.valueOf((int)narcoticSlider.getValue() * TradeGood.NARCOTICS.calcMarketPrice()));      
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.NARCOTICS, 1);        
        updateCredits(TradeGood.NARCOTICS, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one robot
     */
    public void robotsIncrement() {
        if (Context.getInstance().getPlayer().getCredits() > TradeGood.ROBOTS.calcMarketPrice() && Integer.parseInt(tradersRobots.getText()) > 0 
            && !Context.getInstance().getPlayer().getShip().isCargoFull() && goodsBeingTraded.get(TradeGood.ROBOTS)) {
        robotSlider.setValue((double)((int)robotSlider.getValue()) + 1);
        playersRobots.setText(String.valueOf((int)robotSlider.getValue()));
        tradersRobots.setText(String.valueOf(Integer.valueOf(tradersRobots.getText()) - 1));
        //robotsPrice.setText(String.valueOf((int)robotSlider.getValue() * TradeGood.ROBOTS.calcMarketPrice()));        
        Context.getInstance().getPlayer().getShip().addToCargo(TradeGood.ROBOTS, 1);
        updateCredits(TradeGood.ROBOTS, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one robot
     */
    public void robotsDecrement() {
        if (!Context.getInstance().getPlayer().getShip().isCargoEmpty() && !tradersRobots.getText().equals("0")
                && (Context.getInstance().getPlayer().getShip().getCargoStock(TradeGood.ROBOTS) > 0)
                && goodsBeingTraded.get(TradeGood.ROBOTS)) {
        robotSlider.setValue((double)((int)robotSlider.getValue()) - 1);
        playersRobots.setText(String.valueOf((int)robotSlider.getValue()));
        tradersRobots.setText(String.valueOf(Integer.valueOf(tradersRobots.getText()) + 1));
       // robotsPrice.setText(String.valueOf((int)robotSlider.getValue() * TradeGood.ROBOTS.calcMarketPrice()));      
        Context.getInstance().getPlayer().getShip().removeFromCargo(TradeGood.ROBOTS, 1);        
        updateCredits(TradeGood.ROBOTS, -1);        
        }
        currentCargo();    
    }
    
    /**
     * Updates the player's credits on buy / sell.
     * @param traded good you're buying or selling
     * @param num determines if you're adding or subtracting, negative to add pos to subtract (will flip later)
     */
    public void updateCredits(TradeGood traded, int num) {
        if (num < 0) {
            int absNum = Math.abs(num);
            Context.getInstance().getPlayer().addCredits(absNum * stockPrices.get(traded));
        } else if (num > 0) {
            Context.getInstance().getPlayer().removeCredits(num * stockPrices.get(traded));
        }
        currentCredits();
    }
    
    /**
     * confirm button
     */
    public void confirmAction() {
        currentCargo();
        currentCredits();
        int value = -1;
        for (int i = 0; i < 10; i++) {
            switch (i) {
                case 0: value = Integer.valueOf(tradersWater.getText());
                        break;
                case 1: value = Integer.valueOf(tradersFur.getText());
                        break;
                case 2: value = Integer.valueOf(tradersFood.getText());
                        break;
                case 3: value = Integer.valueOf(tradersOre.getText());
                        break;
                case 4: value = Integer.valueOf(tradersGames.getText());
                        break;
                case 5: value = Integer.valueOf(tradersFirearms.getText());
                        break;
                case 6: value = Integer.valueOf(tradersMed.getText());
                        break;
                case 7: value = Integer.valueOf(tradersMachines.getText());
                        break;
                case 8: value = Integer.valueOf(tradersNarcotics.getText());
                        break;
                case 9: value = Integer.valueOf(tradersRobots.getText());
                        break;
                default: System.out.println("I don't know how this happened");
                        break;
            }
            Context.getInstance().getPlayer().getCurrentPlanet().getMarket().setStockIndex(i, value);
        }
        controller.setScreen("PlanetScreen");
        //do stuff
        //go back to PlanetScreen
    }
    
    /**
     * back button
     */
    public void backAction() {
        Context.getInstance().getPlayer().removeCredits(Context.getInstance().getPlayer().getCredits());
        Context.getInstance().getPlayer().addCredits(startCredits);
        Context.getInstance().getPlayer().getShip().setCargo(startCargoStock,startCargo);
        System.out.println(Context.getInstance().getPlayer().getShip().getCargo().get(TradeGood.WATER));
        //need to reset stock
        controller.setScreen("PlanetScreen");
    }
    
}