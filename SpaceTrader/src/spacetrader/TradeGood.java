package spacetrader;
import java.util.Random;
/**
 * Enum for all of the different trade goods in Space Trader.
 *
 */
public enum TradeGood {
    WATER(0, 0, 2, 30, 3, 4, Event.DROUGHT, Event.NONE, Resource.LOTSOFWATER, Resource.DESERT, 30, 50, 0),
    FURS(0, 0, 0, 250, 10, 10, Event.COLD, Event.MANYHUNTERS, Resource.RICHFAUNA, Resource.LIFELESS, 230, 280, 1),
    FOOD(0, 1, 1, 100, 5, 5, Event.CROPFAIL, Event.HARVEST, Resource.RICHSOIL, Resource.POORSOIL, 90, 160, 2),
    ORE(2, 2, 3, 350, 20, 10, Event.WAR, Event.NONE, Resource.MINERALRICH, Resource.MINERALPOOR, 350, 420, 3),
    GAMES(1, 3, 6, 250, -10, 5, Event.BOREDOM, Event.NONE, Resource.ARTISTIC, Resource.NOSPECIALRESOURCES, 160, 270, 4),
    FIREARMS(1, 3, 5, 1250, -75, 100, Event.CRIMEWAVE, Event.POLICE, Resource.WARLIKE, Resource.PACIFIST, 600, 1100, 5),
    MEDICINE(1, 4, 6, 650, -20, 10, Event.PLAGUE, Event.NONE, Resource.LOTSOFHERBS, Resource.LIFELESS, 400, 700, 6),
    MACHINES(3, 4, 5, 900, -30, 5, Event.LACKOFWORKERS, Event.LUDDITES, Resource.NOSPECIALRESOURCES, Resource.NOSPECIALRESOURCES, 600, 800, 7),
    NARCOTICS(0, 5, 5, 3500, -125, 150, Event.POLICE, Event.STRAIGHTEDGE, Resource.WEIRDMUSHROOMS, Resource.NOSPECIALRESOURCES, 2000, 3000, 8),
    ROBOTS(4, 6, 7, 5000, -150, 100, Event.LACKOFWORKERS, Event.LUDDITES, Resource.NOSPECIALRESOURCES, Resource.NOSPECIALRESOURCES, 3500, 5000, 9);
    
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
    
    /**
     * TradeGoods have many parameters, most of them influence price in some way.
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
    
    private final int MTLB, MTLS, TTP, BASEPRICE, IPL, VARIANCE, RTL, RTH, ID;
    private final Event IE, DE;
    private final Resource CR, ER;
    Universe universe = Context.getInstance().getUniverse();
    Player player = Context.getInstance().getPlayer();
    
    
    private TradeGood(int mtlb, int mtls, int ttp, int basePrice, int ipl,
            int variance, Event ie, Event de, Resource cr, Resource er, int rtl, int rth, int id) {
        MTLB = mtlb;
        MTLS = mtls;
        TTP = ttp;
        BASEPRICE = basePrice;
        IPL = ipl;
        VARIANCE = variance;
        IE = ie;
        DE = de;
        CR = cr;
        ER = er;
        RTL = rtl;
        RTH = rth;
        ID = id;
    }
    
    /**
     * Calculates market prices for each good
     * @return the final price
     */
    public int calcMarketPrice() {
        player = Context.getInstance().getPlayer();
        Random r = new Random();
        Event event = player.getCurrentPlanet().getEvent();
        Resource resource = player.getCurrentPlanet().getResource();
        double price = BASEPRICE + (IPL * (player.getCurrentPlanet().getTechLevel() - MTLB)) + r.nextInt(VARIANCE + 1);
        if (player.getCurrentPlanet().getTechLevel() == this.TTP) {
            price *= .95;
        }
        if (event.equals(this.IE)) {
            price *= event.getUpMult();
        }
        if (event.equals(this.DE)) {
            price *= event.getDownMult();
        }
        if (resource.equals(this.CR)) {
            price *= resource.getUpMult();
        }
        if (resource.equals(this.ER)) {
            price *= resource.getDownMult();
        }
        price *= (1.0 + ((double)Context.getInstance().getPlayer().getTrader() / 50.0));
        int finalPrice = (int) price;
        if (player.getTrader() > 0) {
            int discountedPrice = (int)(price-( (price)*((double)player.getTrader())/100.0) );
            return discountedPrice;
        } else {
            return finalPrice;
        }
    }
    
    /**
     * calculates price when buying/selling with a random trader
     * @return trader price
     */
    public int calcTraderPrice() {
        Random r = new Random();
        return RTL + r.nextInt(RTH - RTL);
    }
    
    /**
     * get increase event
     * @return increase event
     */
    public Event getIE() {
        return IE;
    }
    
    /**
     * get decrease event
     * @return decrease event
     */
    public Event getDE() {
        return DE;
    }
    
    /**
     * get low price modifier resource
     * @return low price mod resource
     */
    public Resource getCR() {
        return CR;
    }
    
    /**
     * get high price modifier resource
     * @return high price mod resource
     */
    public Resource getER() {
        return ER;
    }
    
    /**
     * get good ID
     * @return good ID
     */
    public int getID() {
        return ID;
    }
    
    public int getMTLB(){
        return MTLB;
    }
    
    public int getMTLS(){
        return MTLS;
    }
    
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
