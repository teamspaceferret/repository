package spacetrader;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import spacetrader.SpaceTrader.ControlledScreen;
import java.util.Random;

public class MarketController implements ControlledScreen, Initializable {
    @FXML private Button confirmButton, backButton;
    @FXML private Label creditLabel;
    @FXML private Label cargoLabel;
    @FXML private TextField playersFirearms, playersFood, playersFur,
            playersGames, playersMachines, playersMed, playersNarcotics,
            playersOre, playersRobots, playersWater;
    @FXML private Label firearmsPrice, foodPrice, furPrice, gamesPrice,
            machinesPrice, medPrice, narcoticsPrice, orePrice, robotsPrice,
            tradersFirearms, tradersFood, tradersFur, tradersGames,
            tradersMachines, tradersMed, tradersNarcotics, tradersOre,
            tradersRobots, tradersWater, waterPrice;
    @FXML private Slider firearmSlider, foodSlider, furSlider, gameSlider,
            machineSlider, medicineSlider, narcoticSlider, oreSlider,
            robotSlider, waterSlider;
    
    ScreensController controller;
    Universe universe = Context.getInstance().getUniverse();
    Player player = Context.getInstance().getPlayer();   
    Ship ship = Context.getInstance().getPlayer().getShip();
    int startCredits, startCargo = 0;
    static int[] stockGoods = new int[10];
    HashMap<TradeGood, Integer> stockPrices = new HashMap<>();
    HashMap<TradeGood, Integer> startCargoStock;
    
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
        startCredits = this.player.getCredits();
        System.out.println("this.player: " + this.player + "\n");
        System.out.println("this.ship: " + this.ship + "\n");
        System.out.println("this.ship.getCurrentUsedCargoSlots(): " + this.ship.getCurrentUsedCargoSlots());
        startCargo = this.ship.getCurrentUsedCargoSlots();
        currentCredits();
        currentCargo();
        setPrices();
        Market market = this.player.getCurrentPlanet().getMarket();
               
        // Sets players<Good> to the amount they currently have
        // Set traders<Good> to the amount they currently have, or to "No trade" if thats true
        // Display the prices for each good via their price label
        playersWater.setText(String.valueOf(ship.getCargoStock(TradeGood.WATER)));
        waterSlider.setValue(ship.getCargoStock(TradeGood.WATER));
        waterPrice.setText(String.valueOf(stockPrices.get(TradeGood.WATER)));
        tradersWater.setText(String.valueOf(market.getStockIndex(0)));
        playersFur.setText(String.valueOf(ship.getCargoStock(TradeGood.FURS)));
        furSlider.setValue(ship.getCargoStock(TradeGood.FURS));
        tradersFur.setText(String.valueOf(market.getStockIndex(1)));
        furPrice.setText(String.valueOf(stockPrices.get(TradeGood.FURS)));
        playersFood.setText(String.valueOf(ship.getCargoStock(TradeGood.FOOD)));
        foodSlider.setValue(ship.getCargoStock(TradeGood.FOOD));
        tradersFood.setText(String.valueOf(market.getStockIndex(2)));
        foodPrice.setText(String.valueOf(stockPrices.get(TradeGood.FOOD)));
        playersOre.setText(String.valueOf(ship.getCargoStock(TradeGood.ORE)));
        oreSlider.setValue(ship.getCargoStock(TradeGood.ORE));
        tradersOre.setText(String.valueOf(market.getStockIndex(3)));
        orePrice.setText(String.valueOf(stockPrices.get(TradeGood.ORE)));
        playersGames.setText(String.valueOf(ship.getCargoStock(TradeGood.GAMES)));
        gameSlider.setValue(ship.getCargoStock(TradeGood.GAMES));
        tradersGames.setText(String.valueOf(market.getStockIndex(4)));
        gamesPrice.setText(String.valueOf(stockPrices.get(TradeGood.GAMES)));
        playersFirearms.setText(String.valueOf(ship.getCargoStock(TradeGood.FIREARMS)));
        firearmSlider.setValue(ship.getCargoStock(TradeGood.FIREARMS));
        tradersFirearms.setText(String.valueOf(market.getStockIndex(5)));
        firearmsPrice.setText(String.valueOf(stockPrices.get(TradeGood.FIREARMS)));
        playersMed.setText(String.valueOf(ship.getCargoStock(TradeGood.MEDICINE)));
        medicineSlider.setValue(ship.getCargoStock(TradeGood.MEDICINE));
        tradersMed.setText(String.valueOf(market.getStockIndex(6)));
        medPrice.setText(String.valueOf(stockPrices.get(TradeGood.MEDICINE)));
        playersMachines.setText(String.valueOf(ship.getCargoStock(TradeGood.MACHINES)));
        machineSlider.setValue(ship.getCargoStock(TradeGood.MACHINES));
        tradersMachines.setText(String.valueOf(market.getStockIndex(7)));
        machinesPrice.setText(String.valueOf(stockPrices.get(TradeGood.MACHINES)));
        playersNarcotics.setText(String.valueOf(ship.getCargoStock(TradeGood.NARCOTICS)));
        narcoticSlider.setValue(ship.getCargoStock(TradeGood.NARCOTICS));
        tradersNarcotics.setText(String.valueOf(market.getStockIndex(8)));
        narcoticsPrice.setText(String.valueOf(stockPrices.get(TradeGood.NARCOTICS)));
        playersRobots.setText(String.valueOf(ship.getCargoStock(TradeGood.ROBOTS)));
        robotSlider.setValue(ship.getCargoStock(TradeGood.ROBOTS));
        tradersRobots.setText(String.valueOf(market.getStockIndex(9)));
        robotsPrice.setText(String.valueOf(stockPrices.get(TradeGood.ROBOTS)));
        Context.getInstance().setStock(stockGoods);
        currentCargo();
        
        startCargoStock = ship.getCargoClone();
    }
    
    /**
     * Determine how many of each goods this planet stocks.
     * 
     * @param good type of good being stocked
     * @return stock of that good
     * 
     */
    public static int calcStock(TradeGood good) {
        Random rand = new Random();
        
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
                stock = 7 + rand.nextInt(14);
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
        stockPrices.put(TradeGood.FOOD, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[2]);
        stockPrices.put(TradeGood.ORE, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[3]);
        stockPrices.put(TradeGood.GAMES, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[4]);
        stockPrices.put(TradeGood.FIREARMS, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[5]);
        stockPrices.put(TradeGood.MEDICINE, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[6]);
        stockPrices.put(TradeGood.MACHINES, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[7]);
        stockPrices.put(TradeGood.NARCOTICS, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[8]);
        stockPrices.put(TradeGood.ROBOTS, Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[9]);
    }
    
    /**
     * Sets the current available credits to be displayed
     */
    public void currentCredits() {
        creditLabel.setText("Credits: " + this.player.getCredits());
    }
    
    /**
     * Sets the current available cargo space to be displayed
     */
    public void currentCargo() {
        cargoLabel.setText("Cargo Bay Slots: "
                + this.ship.getCurrentUsedCargoSlots() + "/"
                + this.ship.getMaxCargoSlots());
    }
    
    /**
     * buy one water
     */
    public void waterIncrement() {
        if (this.player.getCredits() > TradeGood.WATER.calcMarketPrice()
                && Integer.parseInt(tradersWater.getText()) > 0 
                && !this.ship.isCargoFull()) {
            waterSlider.setValue(((int)waterSlider.getValue()) + 1);
            playersWater.setText(String.valueOf((int)waterSlider.getValue()));
            tradersWater.setText(String.valueOf(Integer.valueOf(tradersWater.getText()) - 1));
            //waterPrice.setText(String.valueOf((int)waterSlider.getValue() * TradeGood.WATER.calcMarketPrice()));
            //waterPrice.setText(String.valueOf(stockPrices.get(TradeGood.WATER)));
            this.ship.addToCargo(TradeGood.WATER, 1);
            updateCredits(TradeGood.WATER, 1);
        }
        currentCargo();
    }

    /**
     * sell one water
     */
    public void waterDecrement() {
        if (!this.ship.isCargoEmpty() && !tradersWater.getText().equals("0")
                && this.ship.getCargoStock(TradeGood.WATER) > 0) {
        waterSlider.setValue(((int)waterSlider.getValue()) - 1);
        playersWater.setText(String.valueOf((int)waterSlider.getValue()));
        tradersWater.setText(String.valueOf(Integer.valueOf(tradersWater.getText()) + 1));
        //waterPrice.setText(String.valueOf((int)waterSlider.getValue() * TradeGood.WATER.calcMarketPrice()));
        //waterPrice.setText(String.valueOf(stockPrices.get(TradeGood.WATER)));
        this.ship.removeFromCargo(TradeGood.WATER, 1);
        updateCredits(TradeGood.WATER, -1);
        }
        currentCargo();
    }

    /**
     * buy one fur
     */
    public void furIncrement() {
        if (this.player.getCredits() > TradeGood.FURS.calcMarketPrice() && Integer.parseInt(tradersFur.getText()) > 0 
                && !this.ship.isCargoFull()) {
        furSlider.setValue((double)((int)furSlider.getValue()) + 1);
        playersFur.setText(String.valueOf((int)furSlider.getValue()));
        tradersFur.setText(String.valueOf(Integer.valueOf(tradersFur.getText()) - 1));
       // furPrice.setText(String.valueOf((int)furSlider.getValue() * TradeGood.FURS.calcMarketPrice()));        
        this.ship.addToCargo(TradeGood.FURS, 1);
        updateCredits(TradeGood.FURS, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one fur
     */
    public void furDecrement() {
        if (!this.ship.isCargoEmpty() && !tradersFur.getText().equals("0")
                && this.ship.getCargoStock(TradeGood.FURS) > 0) {
        furSlider.setValue((double)((int)furSlider.getValue()) - 1);
        playersFur.setText(String.valueOf((int)furSlider.getValue()));
        tradersFur.setText(String.valueOf(Integer.valueOf(tradersFur.getText()) + 1));
        //furPrice.setText(String.valueOf((int)furSlider.getValue() * TradeGood.FURS.calcMarketPrice()));        
        this.ship.removeFromCargo(TradeGood.FURS, 1);
        updateCredits(TradeGood.FURS, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one food
     */
    public void foodIncrement() {
        if (this.player.getCredits() > TradeGood.FOOD.calcMarketPrice() && Integer.parseInt(tradersFood.getText()) > 0 
            && !this.ship.isCargoFull()) {
        foodSlider.setValue((double)((int)foodSlider.getValue()) + 1);
        playersFood.setText(String.valueOf((int)foodSlider.getValue()));
        tradersFood.setText(String.valueOf(Integer.valueOf(tradersFood.getText()) - 1));
        //foodPrice.setText(String.valueOf((int)foodSlider.getValue() * TradeGood.FOOD.calcMarketPrice()));        
        this.ship.addToCargo(TradeGood.FOOD, 1);
        updateCredits(TradeGood.FOOD, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one food
     */
    public void foodDecrement() {
        if (!this.ship.isCargoEmpty() && !tradersFood.getText().equals("0")
                && this.ship.getCargoStock(TradeGood.FOOD) > 0) {
        foodSlider.setValue((double)((int)foodSlider.getValue()) - 1);
        playersFood.setText(String.valueOf((int)foodSlider.getValue()));
        tradersFood.setText(String.valueOf(Integer.valueOf(tradersFood.getText()) + 1));
        //foodPrice.setText(String.valueOf((int)foodSlider.getValue() * TradeGood.FOOD.calcMarketPrice()));       
        this.ship.removeFromCargo(TradeGood.FOOD, 1);
        updateCredits(TradeGood.FOOD, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one ore
     */
    public void oreIncrement() {
        if (this.player.getCredits() > TradeGood.ORE.calcMarketPrice() && Integer.parseInt(tradersOre.getText()) > 0 
           && !this.ship.isCargoFull()) {
        oreSlider.setValue((double)((int)oreSlider.getValue()) + 1);
        playersOre.setText(String.valueOf((int)oreSlider.getValue()));
        tradersOre.setText(String.valueOf(Integer.valueOf(tradersOre.getText()) - 1));
        //orePrice.setText(String.valueOf((int)oreSlider.getValue() * TradeGood.ORE.calcMarketPrice()));
        this.ship.addToCargo(TradeGood.ORE, 1);
        updateCredits(TradeGood.ORE, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one ore
     */
    public void oreDecrement() {
        if (!this.ship.isCargoEmpty() && !tradersOre.getText().equals("0")
                && this.ship.getCargoStock(TradeGood.ORE) > 0) {
        oreSlider.setValue((double)((int)oreSlider.getValue()) - 1);
        playersOre.setText(String.valueOf((int)oreSlider.getValue()));
        tradersOre.setText(String.valueOf(Integer.valueOf(tradersOre.getText()) + 1));
       // orePrice.setText(String.valueOf((int)oreSlider.getValue() * TradeGood.ORE.calcMarketPrice()));       
        this.ship.removeFromCargo(TradeGood.ORE, 1);        
        updateCredits(TradeGood.ORE, -1);
        }
        currentCargo();    
    }

    /**
     * buy one game
     */
    public void gamesIncrement() {
        if (this.player.getCredits() > TradeGood.GAMES.calcMarketPrice() && Integer.parseInt(tradersGames.getText()) > 0 
            && !this.ship.isCargoFull()) {
        gameSlider.setValue((double)((int)gameSlider.getValue()) + 1);
        playersGames.setText(String.valueOf((int)gameSlider.getValue()));
        tradersGames.setText(String.valueOf(Integer.valueOf(tradersGames.getText()) - 1));
        //gamesPrice.setText(String.valueOf((int)gameSlider.getValue() * TradeGood.GAMES.calcMarketPrice()));        
        this.ship.addToCargo(TradeGood.GAMES, 1);        
        updateCredits(TradeGood.GAMES, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one game
     */
    public void gamesDecrement() {
        if (!this.ship.isCargoEmpty() && !tradersGames.getText().equals("0")
                && this.ship.getCargoStock(TradeGood.GAMES) > 0) {
        gameSlider.setValue((double)((int)gameSlider.getValue()) - 1);
        playersGames.setText(String.valueOf((int)gameSlider.getValue()));
        tradersGames.setText(String.valueOf(Integer.valueOf(tradersGames.getText()) + 1));
        //gamesPrice.setText(String.valueOf((int)gameSlider.getValue() * TradeGood.GAMES.calcMarketPrice()));      
        this.ship.removeFromCargo(TradeGood.GAMES, 1);        
        updateCredits(TradeGood.GAMES, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one firearm
     */
    public void firearmsIncrement() {
        if (this.player.getCredits() > TradeGood.FIREARMS.calcMarketPrice() && Integer.parseInt(tradersFirearms.getText()) > 0 
            && !this.ship.isCargoFull()) {
        firearmSlider.setValue((double)((int)firearmSlider.getValue()) + 1);
        playersFirearms.setText(String.valueOf((int)firearmSlider.getValue()));
        tradersFirearms.setText(String.valueOf(Integer.valueOf(tradersFirearms.getText()) - 1));
        //firearmsPrice.setText(String.valueOf((int)firearmSlider.getValue() * TradeGood.FIREARMS.calcMarketPrice()));        
        this.ship.addToCargo(TradeGood.FIREARMS, 1);        
        updateCredits(TradeGood.FIREARMS, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one firearm
     */
    public void firearmsDecrement() {
        if (!this.ship.isCargoEmpty() && !tradersFirearms.getText().equals("0")
                && this.ship.getCargoStock(TradeGood.FIREARMS) > 0) {
        firearmSlider.setValue((double)((int)firearmSlider.getValue() - 1));
        playersFirearms.setText(String.valueOf((int)firearmSlider.getValue()));
        tradersFirearms.setText(String.valueOf(Integer.valueOf(tradersFirearms.getText()) + 1));
        //firearmsPrice.setText(String.valueOf((int)firearmSlider.getValue() * TradeGood.FIREARMS.calcMarketPrice()));       
        this.ship.removeFromCargo(TradeGood.FIREARMS, 1);        
        updateCredits(TradeGood.FIREARMS, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one medicine
     */
    public void medIncrement() {
        if (this.player.getCredits() > TradeGood.MEDICINE.calcMarketPrice() && Integer.parseInt(tradersMed.getText()) > 0 
                && !this.ship.isCargoFull()) {        
        medicineSlider.setValue((double)((int)medicineSlider.getValue()) + 1);
        playersMed.setText(String.valueOf((int)medicineSlider.getValue()));
        tradersMed.setText(String.valueOf(Integer.valueOf(tradersMed.getText()) - 1));
        //medPrice.setText(String.valueOf((int)medicineSlider.getValue() * TradeGood.MEDICINE.calcMarketPrice()));        
        this.ship.addToCargo(TradeGood.MEDICINE, 1);    
        updateCredits(TradeGood.MEDICINE, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one medicine
     */
    public void medDecrement() {
        if (!this.ship.isCargoEmpty() && !tradersMed.getText().equals("0")
                && this.ship.getCargoStock(TradeGood.MEDICINE) > 0) {
        medicineSlider.setValue((double)((int)medicineSlider.getValue()) - 1);
        playersMed.setText(String.valueOf((int)medicineSlider.getValue()));
        tradersMed.setText(String.valueOf(Integer.valueOf(tradersMed.getText()) + 1));
       // medPrice.setText(String.valueOf((int)medicineSlider.getValue() * TradeGood.MEDICINE.calcMarketPrice()));       
        this.ship.removeFromCargo(TradeGood.MEDICINE, 1);
        updateCredits(TradeGood.MEDICINE, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one machine
     */
    public void machinesIncrement() {
        if (this.player.getCredits() > TradeGood.MACHINES.calcMarketPrice() && Integer.parseInt(tradersMachines.getText()) > 0 
                && !this.ship.isCargoFull()) {        
        machineSlider.setValue((double)((int)machineSlider.getValue()) + 1);
        playersMachines.setText(String.valueOf((int)machineSlider.getValue()));
        tradersMachines.setText(String.valueOf(Integer.valueOf(tradersMachines.getText()) - 1));
       // machinesPrice.setText(String.valueOf((int)machineSlider.getValue() * TradeGood.MACHINES.calcMarketPrice()));        
        this.ship.addToCargo(TradeGood.MACHINES, 1);
        updateCredits(TradeGood.MACHINES, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one machine
     */
    public void machinesDecrement() {
        if (!this.ship.isCargoEmpty() && !tradersMachines.getText().equals("0")
                && this.ship.getCargoStock(TradeGood.MACHINES) > 0) {        
        machineSlider.setValue((double)((int)machineSlider.getValue() - 1));
        playersMachines.setText(String.valueOf((int)machineSlider.getValue()));
        tradersMachines.setText(String.valueOf(Integer.valueOf(tradersMachines.getText()) + 1));
       // machinesPrice.setText(String.valueOf((int)machineSlider.getValue() * TradeGood.MACHINES.calcMarketPrice()));       
        this.ship.removeFromCargo(TradeGood.MACHINES, 1);
        updateCredits(TradeGood.MACHINES, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one narcotics
     */
    public void narcoticsIncrement() {
        if (this.player.getCredits() > TradeGood.NARCOTICS.calcMarketPrice() && Integer.parseInt(tradersNarcotics.getText()) > 0 
                && !this.ship.isCargoFull()) {
        narcoticSlider.setValue((double)((int)narcoticSlider.getValue()) + 1);
        playersNarcotics.setText(String.valueOf((int)narcoticSlider.getValue()));
        tradersNarcotics.setText(String.valueOf(Integer.valueOf(tradersNarcotics.getText()) - 1));
        //narcoticsPrice.setText(String.valueOf((int)narcoticSlider.getValue() * TradeGood.NARCOTICS.calcMarketPrice()));        
        this.ship.addToCargo(TradeGood.NARCOTICS, 1);
        updateCredits(TradeGood.NARCOTICS, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one narcotics
     */
    public void narcoticsDecrement() {
        if (!this.ship.isCargoEmpty() && !tradersNarcotics.getText().equals("0")
                && this.ship.getCargoStock(TradeGood.NARCOTICS) > 0) {        
        narcoticSlider.setValue((double)((int)narcoticSlider.getValue()) - 1);
        playersNarcotics.setText(String.valueOf((int)narcoticSlider.getValue()));
        tradersNarcotics.setText(String.valueOf(Integer.valueOf(tradersNarcotics.getText()) + 1));
        //narcoticsPrice.setText(String.valueOf((int)narcoticSlider.getValue() * TradeGood.NARCOTICS.calcMarketPrice()));      
        this.ship.removeFromCargo(TradeGood.NARCOTICS, 1);        
        updateCredits(TradeGood.NARCOTICS, -1);        
        }
        currentCargo();    
    }

    /**
     * buy one robot
     */
    public void robotsIncrement() {
        if (this.player.getCredits() > TradeGood.ROBOTS.calcMarketPrice() && Integer.parseInt(tradersRobots.getText()) > 0 
            && !this.ship.isCargoFull()) {
        robotSlider.setValue((double)((int)robotSlider.getValue()) + 1);
        playersRobots.setText(String.valueOf((int)robotSlider.getValue()));
        tradersRobots.setText(String.valueOf(Integer.valueOf(tradersRobots.getText()) - 1));
        //robotsPrice.setText(String.valueOf((int)robotSlider.getValue() * TradeGood.ROBOTS.calcMarketPrice()));        
        this.ship.addToCargo(TradeGood.ROBOTS, 1);
        updateCredits(TradeGood.ROBOTS, 1);        
        }
        currentCargo();    
    }

    /**
     * sell one robot
     */
    public void robotsDecrement() {
        if (!this.ship.isCargoEmpty() && !tradersRobots.getText().equals("0")
                && this.ship.getCargoStock(TradeGood.ROBOTS) > 0) {
        robotSlider.setValue((double)((int)robotSlider.getValue()) - 1);
        playersRobots.setText(String.valueOf((int)robotSlider.getValue()));
        tradersRobots.setText(String.valueOf(Integer.valueOf(tradersRobots.getText()) + 1));
       // robotsPrice.setText(String.valueOf((int)robotSlider.getValue() * TradeGood.ROBOTS.calcMarketPrice()));      
        this.ship.removeFromCargo(TradeGood.ROBOTS, 1);        
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
            this.player.addCredits(absNum * stockPrices.get(traded));
        } else if (num > 0) {
            this.player.removeCredits(num * stockPrices.get(traded));
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
            this.player.getCurrentPlanet().getMarket().setStockIndex(i, value);
        }
        controller.setScreen("PlanetScreen");
        //do stuff
        //go back to PlanetScreen
    }
    
    /**
     * back button
     */
    public void backAction() {
        this.player.removeCredits(this.player.getCredits());
        this.player.addCredits(startCredits);
        this.ship.setCargo(startCargoStock,startCargo);
        System.out.println(this.ship.getCargo().get(TradeGood.WATER));
        //need to reset stock
        controller.setScreen("PlanetScreen");
    }
    
}
