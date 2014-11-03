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
    static Random random = new Random();
    static int[] stockGoods = new int[10];
    HashMap<TradeGood,Integer> stockPrices = new HashMap<>();
    HashMap<TradeGood,Integer> startCargoStock;
    //hashmap of all goods and whether or not they are available
    HashMap<TradeGood, Boolean> goodsBeingTraded = new HashMap<>();
    //PLEASE USE ME
    HashMap<TradeGood, Label[]> labelsForGoods = new HashMap<>();
    int startCredits, startCargo;
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
     * Initializes the controller class.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     * Helper method to get labels from good ID.
     * 
     * @return Array of labels (tradersGood, goodPrice).
     */
    private Label[] getLabelsFromID(int id) {
        Label trader, price;
        switch(id) {
            case 0: trader = tradersWater;
                    price = waterPrice;
                    break;
            case 1: trader = tradersFur;
                    price = furPrice;
                    break;
            case 2: trader = tradersFood;
                    price = foodPrice;
                    break;
            case 3: trader = tradersOre;
                    price = orePrice;
                    break;
            case 4: trader = tradersGames;
                    price = gamesPrice;
                    break;
            case 5: trader = tradersFirearms;
                    price = firearmsPrice;
                    break;
            case 6: trader = tradersMed;
                    price = medPrice;
                    break;
            case 7: trader = tradersMachines;
                    price = machinesPrice;
                    break;    
            case 8: trader = tradersNarcotics;
                    price = narcoticsPrice;
                    break;
            case 9: trader = tradersRobots;
                    price = robotsPrice;
                    break;
            default: System.out.println("broken helper method: getLabelsFromID()");
                     price = new Label();
                     trader = new Label();
                     break;
        }
        return new Label[]{trader, price};
    }
    
    /**
     * Helper method to get TextField from good ID.
     * 
     * @return playersGood
     */
    private TextField getTextFieldFromID(int id) {
        TextField playerField;
        switch(id) {
            case 0: playerField = playersWater;
                    break;
            case 1: playerField = playersFur;
                    break;
            case 2: playerField = playersFood;
                    break;
            case 3: playerField = playersOre;
                    break;
            case 4: playerField = playersGames;
                    break;
            case 5: playerField = playersFirearms;
                    break;
            case 6: playerField = playersMed;
                    break;
            case 7: playerField = playersMachines;
                    break;    
            case 8: playerField = playersNarcotics;
                    break;
            case 9: playerField = playersRobots;
                    break;
            default: System.out.println("broken helper method: getTextFieldFromID()");
                     playerField = new TextField();
                     break;
        }
        return playerField;
    }
    
    /**
     * Helper method to get Slider from good ID.
     * 
     * @return goodSlider
     */
    private Slider getSliderFromID(int id) {
        Slider slider;
        switch(id) {
            case 0: slider = waterSlider;
                    break;
            case 1: slider = furSlider;
                    break;
            case 2: slider = foodSlider;
                    break;
            case 3: slider = oreSlider;
                    break;
            case 4: slider = gameSlider;
                    break;
            case 5: slider = firearmSlider;
                    break;
            case 6: slider = medicineSlider;
                    break;
            case 7: slider = machineSlider;
                    break;    
            case 8: slider = narcoticSlider;
                    break;
            case 9: slider = robotSlider;
                    break;
            default: System.out.println("broken helper method: getSliderFromID()");
                     slider = new Slider();
                     break;
        }
        return slider;
    }
    
    /**
     * Helper method to initialize labels and sliders.
     */
    private void initLabelsSliders(TradeGood good) {
        int index = good.getID();
        Label[] labels = getLabelsFromID(index);
        Label trader = labels[0];
        Label price = labels[1];
        TextField playerField = getTextFieldFromID(index);
        Slider slider = getSliderFromID(index);
        String cargoStock = String.valueOf(Context.getInstance().getPlayer().getShip().getCargoStock(good));
        String priceString = String.valueOf(stockPrices.get(good));
        String traderStock = String.valueOf(Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getStockIndex(index));
        playerField.setText(cargoStock);
        slider.setValue(Integer.parseInt(cargoStock));
        price.setText(priceString);
        trader.setText(traderStock);
    }
    
    /**
     * Initializes the screen.
     */
    @Override
    public void initScreen() {
        soundManager.setPrevScreen("Market");
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
        for (int i = 0; i < 10; i++) {
            initLabelsSliders(TradeGood.getGoodFromID(i));
        }
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
            Planet planet = Context.getInstance().getPlayer().getCurrentPlanet();
            Event event = planet.getEvent();
            Resource resource = planet.getResource();
            int goodMinTechLevelToBuy = good.getMTLB();
            int goodMinTechLevelToSell = good.getMTLS();
            int planetTechLevel = planet.getTechLevel();
            int stock;
            //will not produce things from too low a tech level
            if(goodMinTechLevelToBuy > planetTechLevel){
                stock = 0;
            } else {
                stock = 7 + random.nextInt(14);
            }
            if (resource.getID() == good.getER().getID()) {
                stock *= .45;
            } else if (resource.getID() == good.getCR().getID()) {
                stock *= 1.55;
            } else {
            }
            if (event.getID() == good.getIE().getID()) {
                stock *= .75;
            } else if (event.getID() == good.getDE().getID()) {
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
        Ship ship = Context.getInstance().getPlayer().getShip();
        for (int i = 0; i < 10; i++) {
            ship.addToCargo(TradeGood.getGoodFromID(i), random.nextInt(4));
        }
    }
    
    /**
     * Set the prices of the market
     */
    public void setPrices(){
        int[] marketPrices = new int[10];
        for (int i = 0; i < marketPrices.length; i++) {
            marketPrices[i] = Context.getInstance().getPlayer().getCurrentPlanet().getMarket().getPrices()[i];
            stockPrices.put(TradeGood.getGoodFromID(i), marketPrices[i]);
        }
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
        for (int i = 0; i < 10; i++) {
            Label[] labels = getLabelsFromID(i);
            Label trader = labels[0];
            Label price = labels[1];
            if (!goodsBeingTraded.get(TradeGood.getGoodFromID(i))) {
                trader.setText("NO");
                price.setText("TRADE");
            }
        }
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
     * 
     * @param id 
     */
    private void genericIncrement(int id) {
        TradeGood good = TradeGood.getGoodFromID(id);
        Ship ship = Context.getInstance().getPlayer().getShip();
        int credits = Context.getInstance().getPlayer().getCredits();
        int price = TradeGood.getGoodFromID(id).calcMarketPrice();
        Label trader = getLabelsFromID(id)[0];
        Slider slider = getSliderFromID(id);
        TextField playerField = getTextFieldFromID(id);
        //check if trader trades this good
        //set stock number to 0 if good is not tradeable
        String traderStockStr = trader.getText();
        int traderStock = 0;
        if(!traderStockStr.equals("NO")){
            traderStock = Integer.parseInt(trader.getText());
        }
        
        int sliderValue = (int)slider.getValue();
        boolean isCargoFull = ship.isCargoFull();
        if (credits > price && traderStock > 0 && !isCargoFull && goodsBeingTraded.get(good)) {
            slider.setValue(sliderValue + 1);
            sliderValue = (int)slider.getValue();
            playerField.setText(String.valueOf(sliderValue));
            trader.setText(String.valueOf(traderStock - 1));
            ship.addToCargo(good, 1);
            updateCredits(good, 1);
        }
        currentCargo();        
    }
    
    /**
     * @param id 
     */
    private void genericDecrement(int id) {
        boolean noTrade = false;
        TradeGood good = TradeGood.getGoodFromID(id);
        Ship ship = Context.getInstance().getPlayer().getShip();
        int credits = Context.getInstance().getPlayer().getCredits();
        int price = TradeGood.getGoodFromID(id).calcMarketPrice();
        Label trader = getLabelsFromID(id)[0];
        Slider slider = getSliderFromID(id);
        TextField playerField = getTextFieldFromID(id);
        //if the trader cannot trade the object, stock is 0 and noTrade is true
        String traderStockStr = trader.getText();
        int traderStock = 0;
        if(!traderStockStr.equals("NO")){
            traderStock = Integer.parseInt(trader.getText());
            
        } else {
            noTrade = true;
        }
        
        int sliderValue = (int)slider.getValue();
        boolean isCargoEmpty = ship.isCargoEmpty();
        if (!isCargoEmpty && !noTrade && ship.getCargoStock(good) > 0 && goodsBeingTraded.get(good)) {
            slider.setValue(sliderValue - 1);
            sliderValue = (int)slider.getValue();
            playerField.setText(String.valueOf(sliderValue));
            trader.setText(String.valueOf(traderStock + 1));
            ship.removeFromCargo(good, 1);
            updateCredits(good, -1);
        }
        currentCargo();
    }
    
    /**
     * Buy one water
     */
    public void waterIncrement() {
        genericIncrement(0);
    }

    /**
     * sell one water
     */
    public void waterDecrement() {
        genericDecrement(0);
    }

    /**
     * buy one fur
     */
    public void furIncrement() {
        genericIncrement(1);    
    }

    /**
     * sell one fur
     */
    public void furDecrement() {
        genericDecrement(1);
    }

    /**
     * buy one food
     */
    public void foodIncrement() {
        genericIncrement(2); 
    }

    /**
     * sell one food
     */
    public void foodDecrement() {
        genericDecrement(2);
    }

    /**
     * buy one ore
     */
    public void oreIncrement() {
        genericIncrement(3);  
    }

    /**
     * sell one ore
     */
    public void oreDecrement() {
        genericDecrement(3); 
    }

    /**
     * buy one game
     */
    public void gamesIncrement() {
        genericIncrement(4);
    }

    /**
     * sell one game
     */
    public void gamesDecrement() {
        genericDecrement(4);   
    }

    /**
     * buy one firearm
     */
    public void firearmsIncrement() {
        genericIncrement(5);
    }

    /**
     * sell one firearm
     */
    public void firearmsDecrement() {
        genericDecrement(5); 
    }

    /**
     * buy one medicine
     */
    public void medIncrement() {
        genericIncrement(6);  
    }

    /**
     * sell one medicine
     */
    public void medDecrement() {
        genericDecrement(6);    
    }

    /**
     * buy one machine
     */
    public void machinesIncrement() {
        genericIncrement(7);
    }

    /**
     * sell one machine
     */
    public void machinesDecrement() {
        genericDecrement(7);
    }

    /**
     * buy one narcotics
     */
    public void narcoticsIncrement() {
        genericIncrement(8);    
    }

    /**
     * sell one narcotics
     */
    public void narcoticsDecrement() {
        genericDecrement(8);
    }

    /**
     * buy one robot
     */
    public void robotsIncrement() {
        genericIncrement(9);
    }

    /**
     * sell one robot
     */
    public void robotsDecrement() {
        genericDecrement(9);
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
            //check if trader trades this good, and if he does, change value to correct number
            if(!getLabelsFromID(i)[0].getText().equals("NO")){
                value = Integer.valueOf(getLabelsFromID(i)[0].getText());
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
        player.removeCredits(Context.getInstance().getPlayer().getCredits());
        player.addCredits(startCredits);
        player.getShip().setCargo(startCargoStock,startCargo);
        //System.out.println(Context.getInstance().getPlayer().getShip().getCargo().get(TradeGood.WATER));
        //need to reset stock
        controller.setScreen("PlanetScreen");
    }
    
}
