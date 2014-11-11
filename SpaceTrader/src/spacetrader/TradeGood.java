package spacetrader;
import java.util.Random;
/**
 * Enum for all of the different trade goods in Space Trader.
 *
 */
public enum TradeGood {
    WATER(0, 0, 2, 30, 3, 4, 1, 0, 4, 3, 30, 50, 0),
    FURS(0, 0, 0, 250, 10, 10, 2, 9, 7, 8, 230, 280, 1),
    FOOD(0, 1, 1, 100, 5, 5, 10, 11, 5, 6, 90, 160, 2),
    ORE(2, 2, 3, 350, 20, 10, 3, 0, 1, 2, 350, 420, 3),
    GAMES(1, 3, 6, 250, -10, 5, 4, 0, 11, 0, 160, 270, 4),
    FIREARMS(1, 3, 5, 1250, -75, 100, 7, 12, 12, 13, 600, 1100, 5),
    MEDICINE(1, 4, 6, 650, -20, 10, 5, 0, 10, 8, 400, 700, 6),
    MACHINES(3, 4, 5, 900, -30, 5, 6, 13, 0, 0, 600, 800, 7),
    NARCOTICS(0, 5, 5, 3500, -125, 150, 12, 14, 9, 0, 2000, 3000, 8),
    ROBOTS(4, 6, 7, 5000, -150, 100, 6, 13, 0, 0, 3500, 5000, 9);
    
    public static final int NUM_TRADE_GOODS = 10;
    public static final int WATER_ID = 0;
    public static final int FURS_ID = 1;
    public static final int FOOD_ID = 2;
    public static final int ORE_ID = 3;
    public static final int GAMES_ID = 4;
    public static final int FIREARMS_ID = 5;
    public static final int MEDICINE_ID = 6;
    public static final int MACHINES_ID = 7;
    public static final int NARCOTICS_ID = 8;
    public static final int ROBOTS_ID = 9;
    public static final double DFIFTY = 50.0;
    public static final double PERCENT = 100.0;

    /**
     * TradeGoods have many parameters, most of them influence price in a way.
     * @param MTLB minimum tech level to buy this resource
     * @param MTLS minimum tech level to sell this resource
     * @param TTP tech level which produces the most of this resource
     * @param basePrice base price of this resource
     * @param IPL price increase per tech level
     * @param variance max percent this good can vary above or below the base
     * @param IE price increase event, raises price astronomically
     * @param DE price decrease event, drops price astronomically
     * @param CR if this is true, price of this good is unusually low
     * @param ER if this is true, price of this good is expensive
     * @param RTL random trader lowest price, outside of marketplace
     * @param RTH random trader highest price, outside of marketplace
     * @param ID good id
     */

    private final int mtlb, mtls, ttp, basePrice, ipl, variance, rtl, rth, id;
    private final Event ie, de;
    private final Resource cr, er;
    private final Universe universe = Context.getInstance().getUniverse();
    private Player player = Context.getInstance().getPlayer();

    private TradeGood(final int mtlb, final int mtls, final int ttp,
            final int basePrice, final int ipl, final int variance,
            final int ie, final int de, final int cr, final int er,
            final int rtl, final int rth, final int id) {
        Event[] events = Event.eventArray();
        Resource[] resources = Resource.resourceArray();
        this.mtlb = mtlb;
        this.mtls = mtls;
        this.ttp = ttp;
        this.basePrice = basePrice;
        this.ipl = ipl;
        this.variance = variance;
        this.ie = events[ie];
        this.de = events[de];
        this.cr = resources[cr];
        this.er = resources[er];
        this.rtl = rtl;
        this.rth = rth;
        this.id = id;
    }
    
    /**
     * Calculates market prices for each good
     * @return the final price
     */
    public int calcMarketPrice() {
        player = Context.getInstance().getPlayer();
        Random r = new Random();
        int techLevel = player.getCurrentPlanet().getTechLevel();
        Event event = player.getCurrentPlanet().getEvent();
        Resource resource = player.getCurrentPlanet().getResource();
        double price = basePrice;
        price += (ipl * (techLevel - mtlb)) + r.nextInt(variance + 1);
        if (player.getCurrentPlanet().getTechLevel() == this.ttp) {
            price *= Context.TECH_PRICE_MULT;
        }
        if (event.equals(this.ie)) {
            price *= event.getUpMult();
        }
        if (event.equals(this.de)) {
            price *= event.getDownMult();
        }
        if (resource.equals(this.cr)) {
            price *= resource.getUpMult();
        }
        if (resource.equals(this.er)) {
            price *= resource.getDownMult();
        }
        price *= (((double) player.getTrader() / DFIFTY) + 1.0);
        int finalPrice = (int) price;
        if (player.getTrader() > 0) {
            int discountedPrice = (int) price;
            double trader = (double) player.getTrader();
            discountedPrice -= (price) * (trader / PERCENT);
            return discountedPrice;
        } else {
            return finalPrice;
        }
    }

    /**
     * calculates price when buying/selling with a random trader.
     * @return trader price
     */
    public int calcTraderPrice() {
        Random r = new Random();
        return rtl + r.nextInt(rth - rtl);
    }
    
    /**
     * get increase event.
     * @return increase event
     */
    public Event getIE() {
        return ie;
    }
    
    /**
     * get decrease event.
     * @return decrease event
     */
    public Event getDE() {
        return de;
    }
    
    /**
     * get low price modifier resource.
     * @return low price mod resource
     */
    public Resource getCR() {
        return cr;
    }
    
    /**
     * get high price modifier resource.
     * @return high price mod resource
     */
    public Resource getER() {
        return er;
    }

    /**
     * get good ID.
     * @return good ID
     */
    public int getID() {
        return id;
    }

    /**
     * get MTLB stat.
     * @return MTLB int stat
     */
    public int getMTLB() {
        return mtlb;
    }

    /**
     * get MTLS stat.
     * @return MTLS int stat
     */
    public int getMTLS() {
        return mtls;
    }

    /**
     * uses a switch statement to return a good from an int id.
     * @param id id of good to return
     * @return good with ID id
     */
    public static TradeGood getGoodFromID(final int id) {
        TradeGood good = null;
        switch(id) {
            case TradeGood.WATER_ID: good = WATER;
                    break;
            case TradeGood.FURS_ID: good = FURS;
                    break;
            case TradeGood.FOOD_ID: good = FOOD;
                    break;
            case TradeGood.ORE_ID: good = ORE;
                    break;
            case TradeGood.GAMES_ID: good = GAMES;
                    break;
            case TradeGood.FIREARMS_ID: good = FIREARMS;
                    break;
            case TradeGood.MEDICINE_ID: good = MEDICINE;
                    break;
            case TradeGood.MACHINES_ID: good = MACHINES;
                    break;
            case TradeGood.NARCOTICS_ID: good = NARCOTICS;
                    break;
            case TradeGood.ROBOTS_ID: good = ROBOTS;
                    break;
            default: System.out.println("you supplied a bad ID, returns null");
                    break;
        }
        return good;
    }
}
