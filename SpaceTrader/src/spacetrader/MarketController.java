package spacetrader;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import spacetrader.SpaceTrader.ControlledScreen;
import java.util.Random;

public class MarketController implements ControlledScreen, Initializable {
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

    private ScreensController controller;
    private Universe universe = Context.getInstance().getUniverse();
    private Player player = Context.getInstance().getPlayer();
    private SolarSystem currentlySelected;
    private final static Random RANDOM = new Random();
    private final static int[] STOCKGOODS = new int[TradeGood.NUM_TRADE_GOODS];
    private final HashMap<TradeGood, Integer> stockPrices = new HashMap<>();
    private HashMap<TradeGood, Integer> startCargoStock;
    //hashmap of all goods and whether or not they are available
    private final HashMap<TradeGood, Boolean> tradingGoods = new HashMap<>();
    private final HashMap<TradeGood, Label[]> labelsForGoods = new HashMap<>();
    private int startCredits;
    private int startCargo;

    /**
     * Set the screen parent.
     * @param screenParent the screen parent
     */
    @Override
    public final void setScreenParent(final ScreensController screenParent) {
        controller = screenParent;
    }

    /**
     * Initializes the controller class.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    /**
     * Helper method to get labels from good ID.
     * @param id the id of the good to get labels from
     * @return Array of labels (tradersGood, goodPrice).
     */
    private Label[] getLabelsFromID(final int id) {
        Label trader, price;
        switch(id) {
            case TradeGood.WATER_ID: trader = tradersWater;
                    price = waterPrice;
                    break;
            case TradeGood.FURS_ID: trader = tradersFur;
                    price = furPrice;
                    break;
            case TradeGood.FOOD_ID: trader = tradersFood;
                    price = foodPrice;
                    break;
            case TradeGood.ORE_ID: trader = tradersOre;
                    price = orePrice;
                    break;
            case TradeGood.GAMES_ID: trader = tradersGames;
                    price = gamesPrice;
                    break;
            case TradeGood.FIREARMS_ID: trader = tradersFirearms;
                    price = firearmsPrice;
                    break;
            case TradeGood.MEDICINE_ID: trader = tradersMed;
                    price = medPrice;
                    break;
            case TradeGood.MACHINES_ID: trader = tradersMachines;
                    price = machinesPrice;
                    break;
            case TradeGood.NARCOTICS_ID: trader = tradersNarcotics;
                    price = narcoticsPrice;
                    break;
            case TradeGood.ROBOTS_ID: trader = tradersRobots;
                    price = robotsPrice;
                    break;
            default: System.out.println("broken helper method: "
                    + "getLabelsFromID()");
                     price = new Label();
                     trader = new Label();
                     break;
        }
        return new Label[]{trader, price};
    }

    /**
     * Helper method to get TextField from good ID.
     * @param id the id of the good to get
     * @return playersGood
     */
    private TextField getTextFieldFromID(final int id) {
        TextField playerField;
        switch(id) {
            case TradeGood.WATER_ID: playerField = playersWater;
                    break;
            case TradeGood.FURS_ID: playerField = playersFur;
                    break;
            case TradeGood.FOOD_ID: playerField = playersFood;
                    break;
            case TradeGood.ORE_ID: playerField = playersOre;
                    break;
            case TradeGood.GAMES_ID: playerField = playersGames;
                    break;
            case TradeGood.FIREARMS_ID: playerField = playersFirearms;
                    break;
            case TradeGood.MEDICINE_ID: playerField = playersMed;
                    break;
            case TradeGood.MACHINES_ID: playerField = playersMachines;
                    break;
            case TradeGood.NARCOTICS_ID: playerField = playersNarcotics;
                    break;
            case TradeGood.ROBOTS_ID: playerField = playersRobots;
                    break;
            default: System.out.println("broken helper method: "
                    + "getTextFieldFromID()");
                     playerField = new TextField();
                     break;
        }
        return playerField;
    }

    /**
     * Helper method to get Slider from good ID.
     * @param id id of good to get
     * @return goodSlider
     */
    private Slider getSliderFromID(final int id) {
        Slider slider;
        switch(id) {
            case TradeGood.WATER_ID: slider = waterSlider;
                    break;
            case TradeGood.FURS_ID: slider = furSlider;
                    break;
            case TradeGood.FOOD_ID: slider = foodSlider;
                    break;
            case TradeGood.ORE_ID: slider = oreSlider;
                    break;
            case TradeGood.GAMES_ID: slider = gameSlider;
                    break;
            case TradeGood.FIREARMS_ID: slider = firearmSlider;
                    break;
            case TradeGood.MEDICINE_ID: slider = medicineSlider;
                    break;
            case TradeGood.MACHINES_ID: slider = machineSlider;
                    break;
            case TradeGood.NARCOTICS_ID: slider = narcoticSlider;
                    break;
            case TradeGood.ROBOTS_ID: slider = robotSlider;
                    break;
            default: System.out.println("broken helper method: "
                    + "getSliderFromID()");
                     slider = new Slider();
                     break;
        }
        return slider;
    }

    /**
     * Helper method to initialize labels and sliders.
     * @param good trade good to init slider for
     */
    private void initLabelsSliders(final TradeGood good) {
        int index = good.getID();
        Label[] labels = getLabelsFromID(index);
        Label trader = labels[0];
        Label price = labels[1];
        TextField playerField = getTextFieldFromID(index);
        Slider slider = getSliderFromID(index);
        player = Context.getInstance().getPlayer();
        Market market = player.getCurrentPlanet().getMarket();
        Ship ship = player.getShip();
        String cargoStock = String.valueOf(ship.getCargoStock(good));
        String priceString = String.valueOf(stockPrices.get(good));
        String traderStock = String.valueOf(market.getStockIndex(index));
        playerField.setText(cargoStock);
        slider.setValue(Integer.parseInt(cargoStock));
        price.setText(priceString);
        trader.setText(traderStock);
    }

    /**
     * Initializes the screen.
     */
    @Override
    public final void initScreen() {
        universe = Context.getInstance().getUniverse();
        player = Context.getInstance().getPlayer();
        startCredits = player.getCredits();
        startCargo = player.getShip().getCurrentUsedCargoSlots();
        currentCredits();
        currentCargo();
        setPrices();
        //set playersGood to the amount they currently have
        //set tradersGood to the amount they currently have
        //or to "No trade" if thats true
        //display the prices for each good via their price Label
        for (int i = 0; i < TradeGood.NUM_TRADE_GOODS; i++) {
            initLabelsSliders(TradeGood.getGoodFromID(i));
        }
        Context.getInstance().setStock(STOCKGOODS);
        currentCargo();
        startCargoStock = player.getShip().getCargoClone();
        setNoTrade();
    }

    /**
     * Determine how many of each goods this planet stocks.
     * @param good type of good being stocked
     * @return stock of that good
     */
    public static int calcStock(final TradeGood good) {
        if (Context.getInstance().getStock()[good.getID()] == -1) {
            Player player = Context.getInstance().getPlayer();
            Planet planet = player.getCurrentPlanet();
            Event event = planet.getEvent();
            Resource resource = planet.getResource();
            int goodMinTechLevelToBuy = good.getMTLB();
            int planetTechLevel = planet.getTechLevel();
            int baseStock = Context.STOCK_BASE_VALUE;
            int randStock = Context.STOCK_RAND_VALUE;
            int stock;
            //will not produce things from too low a tech level
            if (goodMinTechLevelToBuy > planetTechLevel) {
                stock = 0;
            } else {
                stock = baseStock + RANDOM.nextInt(randStock);
            }
            if (resource.getID() == good.getER().getID()) {
                stock *= Context.STOCK_RESOURCE_DEC;
            } else if (resource.getID() == good.getCR().getID()) {
                stock *= Context.STOCK_RESOURCE_INC;
            }
            if (event.getID() == good.getIE().getID()) {
                stock *= Context.STOCK_EVENT_DEC;
            } else if (event.getID() == good.getDE().getID()) {
                stock *= Context.STOCK_EVENT_INC;
            }
            STOCKGOODS[good.getID()] = stock;
            return stock;
        } else {
            return Context.getInstance().getStock()[good.getID()];
        }
    }

    /**
     * Set the prices of the market.
     */
    public final void setPrices() {
        int[] marketPrices = new int[TradeGood.NUM_TRADE_GOODS];
        Planet planet = Context.getInstance().getPlayer().getCurrentPlanet();
        for (int i = 0; i < marketPrices.length; i++) {
            marketPrices[i] = planet.getMarket().getPrices()[i];
            stockPrices.put(TradeGood.getGoodFromID(i), marketPrices[i]);
        }
    }

    /**
     * Displays "No Trade" beside items that cannot be bought/sold, either.
     * because of tech level or governments
     */
    public final void setNoTrade() {
        Planet planet = player.getCurrentPlanet();
        for (TradeGood good : TradeGood.values()) {
            //if tech level is too low to buy that here
            if (good.getMTLB() > planet.getTechLevel()) {
                tradingGoods.put(good, false);
                System.out.println("No trade: " + good.toString());
            } else {
                tradingGoods.put(good, true);
            }
        }
        //if govt has illegal goods restriction, change sale to false
        if (planet.getGovernment().getIllegalGoods().length > 0) {
            for (TradeGood good : planet.getGovernment().getIllegalGoods()) {
                tradingGoods.replace(good, Boolean.FALSE);
            }
        }
        //if govt satori
            //add those goods to tradingGoods as false
        for (int i = 0; i < TradeGood.NUM_TRADE_GOODS; i++) {
            Label[] labels = getLabelsFromID(i);
            Label trader = labels[0];
            Label price = labels[1];
            if (!tradingGoods.get(TradeGood.getGoodFromID(i))) {
                trader.setText("NO");
                price.setText("TRADE");
            }
        }
    }

    /**
     * Sets the current available credits to be displayed.
     */
    public final void currentCredits() {
        creditLabel.setText("Credits: " + player.getCredits());
    }

    /**
     * Sets the current available cargo space to be displayed.
     */
    public final void currentCargo() {
        int currentUsedCargo = player.getShip().getCurrentUsedCargoSlots();
        int maxCargoSlots = player.getShip().getMaxCargoSlots();
        cargoLabel.setText("Cargo Bay Slots: " + currentUsedCargo
                + "/" + maxCargoSlots);
    }

    /**
     * A generic increment method for a good.
     * @param id the good to increment the count of
     */
    private void genericIncrement(final int id) {
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
        if (!traderStockStr.equals("NO")) {
            traderStock = Integer.parseInt(trader.getText());
        }
        int sliderValue = (int) slider.getValue();
        boolean isCargoFull = ship.isCargoFull();
        if (credits > price && traderStock > 0 && !isCargoFull
                && tradingGoods.get(good)) {
            slider.setValue(sliderValue + 1);
            sliderValue = (int) slider.getValue();
            playerField.setText(String.valueOf(sliderValue));
            trader.setText(String.valueOf(traderStock - 1));
            ship.addToCargo(good, 1);
            updateCredits(good, 1);
        }
        currentCargo();
    }

    /**
     * Generic decrement for a good.
     * @param id good to decrement
     */
    private void genericDecrement(final int id) {
        boolean noTrade = false;
        TradeGood good = TradeGood.getGoodFromID(id);
        Ship ship = Context.getInstance().getPlayer().getShip();
        Label trader = getLabelsFromID(id)[0];
        Slider slider = getSliderFromID(id);
        TextField playerField = getTextFieldFromID(id);
        //if the trader cannot trade the object, stock is 0 and noTrade is true
        String traderStockStr = trader.getText();
        int traderStock = 0;
        if (!traderStockStr.equals("NO")) {
            traderStock = Integer.parseInt(trader.getText());
        } else {
            noTrade = true;
        }

        int sliderValue = (int) slider.getValue();
        boolean isCargoEmpty = ship.isCargoEmpty();
        if (!isCargoEmpty && !noTrade && ship.getCargoStock(good) > 0
                && tradingGoods.get(good)) {
            slider.setValue(sliderValue - 1);
            sliderValue = (int) slider.getValue();
            playerField.setText(String.valueOf(sliderValue));
            trader.setText(String.valueOf(traderStock + 1));
            ship.removeFromCargo(good, 1);
            updateCredits(good, -1);
        }
        currentCargo();
    }

    /**
     * Buy one water.
     */
    public final void waterIncrement() {
        genericIncrement(TradeGood.WATER_ID);
    }

    /**
     * sell one water.
     */
    public final void waterDecrement() {
        genericDecrement(TradeGood.WATER_ID);
    }

    /**
     * buy one fur.
     */
    public final void furIncrement() {
        genericIncrement(TradeGood.FURS_ID);
    }

    /**
     * sell one fur.
     */
    public final void furDecrement() {
        genericDecrement(TradeGood.FURS_ID);
    }

    /**
     * buy one food.
     */
    public final void foodIncrement() {
        genericIncrement(TradeGood.FOOD_ID);
    }

    /**
     * sell one food.
     */
    public final void foodDecrement() {
        genericDecrement(TradeGood.FOOD_ID);
    }

    /**
     * buy one ore.
     */
    public final void oreIncrement() {
        genericIncrement(TradeGood.ORE_ID);
    }

    /**
     * sell one ore.
     */
    public final void oreDecrement() {
        genericDecrement(TradeGood.ORE_ID);
    }

    /**
     * buy one game.
     */
    public final void gamesIncrement() {
        genericIncrement(TradeGood.GAMES_ID);
    }

    /**
     * sell one game.
     */
    public final void gamesDecrement() {
        genericDecrement(TradeGood.GAMES_ID);
    }

    /**
     * buy one firearm.
     */
    public final void firearmsIncrement() {
        genericIncrement(TradeGood.FIREARMS_ID);
    }

    /**
     * sell one firearm.
     */
    public final void firearmsDecrement() {
        genericDecrement(TradeGood.FIREARMS_ID);
    }

    /**
     * buy one medicine.
     */
    public final void medIncrement() {
        genericIncrement(TradeGood.MEDICINE_ID);
    }

    /**
     * sell one medicine.
     */
    public final void medDecrement() {
        genericDecrement(TradeGood.MEDICINE_ID);
    }

    /**
     * buy one machine.
     */
    public final void machinesIncrement() {
        genericIncrement(TradeGood.MACHINES_ID);
    }

    /**
     * sell one machine.
     */
    public final void machinesDecrement() {
        genericDecrement(TradeGood.MACHINES_ID);
    }

    /**
     * buy one narcotics.
     */
    public final void narcoticsIncrement() {
        genericIncrement(TradeGood.NARCOTICS_ID);
    }

    /**
     * sell one narcotics.
     */
    public final void narcoticsDecrement() {
        genericDecrement(TradeGood.NARCOTICS_ID);
    }

    /**
     * buy one robot.
     */
    public final void robotsIncrement() {
        genericIncrement(TradeGood.ROBOTS_ID);
    }

    /**
     * sell one robot.
     */
    public final void robotsDecrement() {
        genericDecrement(TradeGood.ROBOTS_ID);
    }

    /**
     * Updates the player's credits on buy / sell.
     * @param traded good you're buying or selling
     * @param num determines if you're adding or subtracting,
     *      negative to add pos to subtract (will flip later)
     */
    public final void updateCredits(final TradeGood traded, final int num) {
        player = Context.getInstance().getPlayer();
        if (num < 0) {
            int absNum = Math.abs(num);
            player.addCredits(absNum * stockPrices.get(traded));
        } else if (num > 0) {
            player.removeCredits(num * stockPrices.get(traded));
        }
        currentCredits();
    }

    /**
     * confirm button.
     */
    public final void confirmAction() {
        currentCargo();
        currentCredits();
        int value = -1;
        Planet planet = Context.getInstance().getPlayer().getCurrentPlanet();
        for (int i = 0; i < TradeGood.NUM_TRADE_GOODS; i++) {
            //check if trader trades this good, and if he does,
            //change value to correct number
            if (!getLabelsFromID(i)[0].getText().equals("NO")) {
                value = Integer.valueOf(getLabelsFromID(i)[0].getText());
            }
            planet.getMarket().setStockIndex(i, value);
        }
        controller.setScreen("PlanetScreen");
    }

    /**
     * back button.
     */
    public final void backAction() {
        player.removeCredits(Context.getInstance().getPlayer().getCredits());
        player.addCredits(startCredits);
        player.getShip().setCargo(startCargoStock, startCargo);
        controller.setScreen("PlanetScreen");
    }
}