package spacetrader;
import java.util.Random;
/**
 * Enum for all of the different trade goods in Space Trader.
 *
 */
public enum TradeGood {
    WATER(0, 0, 2, 30, 3, 4, Event.DROUGHT, Event.NONE, Resource.LOTSOFWATER, Resource.DESERT, 30, 50, 0),
    FURS(0, 0, 0, 250, 10, 10, Event.COLD, Event.MANYHUNTERS, Resource.RICHFAUNA, Resource.LIFELESS, 230, 280, 1),
    FOOD(1, 0, 1, 100, 5, 5, Event.CROPFAIL, Event.HARVEST, Resource.RICHSOIL, Resource.POORSOIL, 90, 160, 2),
    ORE(2, 2, 3, 350, 20, 10, Event.WAR, Event.NONE, Resource.MINERALRICH, Resource.MINERALPOOR, 350, 420, 3),
    GAMES(3, 1, 6, 250, -10, 5, Event.BOREDOM, Event.NONE, Resource.ARTISTIC, Resource.NOSPECIALRESOURCES, 160, 270, 4),
    FIREARMS(3, 1, 5, 1250, -75, 100, Event.CRIMEWAVE, Event.POLICE, Resource.WARLIKE, Resource.PACIFIST, 600, 1100, 5),
    MEDICINE(4, 1, 6, 650, -20, 10, Event.PLAGUE, Event.NONE, Resource.LOTSOFHERBS, Resource.LIFELESS, 400, 700, 6),
    MACHINES(4, 3, 5, 900, -30, 5, Event.LACKOFWORKERS, Event.LUDDITES, Resource.NOSPECIALRESOURCES, Resource.NOSPECIALRESOURCES, 600, 800, 7),
    NARCOTICS(5, 0, 5, 3500, -125, 150, Event.POLICE, Event.STRAIGHTEDGE, Resource.WEIRDMUSHROOMS, Resource.NOSPECIALRESOURCES, 2000, 3000, 8),
    ROBOTS(6, 4, 7, 5000, -150, 100, Event.LACKOFWORKERS, Event.LUDDITES, Resource.NOSPECIALRESOURCES, Resource.NOSPECIALRESOURCES, 3500, 5000, 9);
    
    /**
     * TradeGoods have many parameters.
     * 
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
     *
     * @return
     */
    public int calcMarketPrice() {
        //basePrice + (IPL * (Planet tech level - MTLP)) + (variance)
        //need to add planet tech level to this equation when the player has a
        //"currentPlanet" variable etc, plus commented stuff
        Random r = new Random();
        double price = 0;
        if (player.getCurrentPlanet() != null) {
            price = BASEPRICE + (IPL * (player.getCurrentPlanet().getTechLevel() - MTLB)) + r.nextInt(VARIANCE + 1);
        } else {
            price = BASEPRICE + (IPL * (MTLB)) + r.nextInt(VARIANCE + 1);
        } if (player.getCurrentPlanet().getTechLevel() == this.TTP) {
            price *= .95;
        } if (player.getCurrentPlanet().getEvent() != null && player.getCurrentPlanet().getEvent().equals(this.IE)) {
            price *= 1.25;
        } if (player.getCurrentPlanet().getEvent() != null && player.getCurrentPlanet().getEvent().equals(this.DE)) {
            price *= .75;
        } if (player.getCurrentPlanet().getResource() != null && player.getCurrentPlanet().getResource().equals(this.CR)) {
            price *= 1.15;
        } if (player.getCurrentPlanet().getResource() != null && player.getCurrentPlanet().getResource().equals(this.ER)) {
            price *= .85;
        }
        //if current event = IE, 25% increase in price
        //if current event = DE, 25% decrease in price
        int finalPrice = (int)price;
        if (player.getTrader() > 0) {
            int discountedPrice = (finalPrice-(player.getTrader()));
            System.out.println("You got a discount from " + finalPrice + " to " + discountedPrice + "!");
            return discountedPrice;
        } else {
            return finalPrice;
        }
    }
    
    /**
     *
     * @return
     */
    public int calcTraderPrice() {
        Random r = new Random();
        return RTL + r.nextInt(RTH - RTL);
    }
    
    public Event getIE() {
        return IE;
    }
    
    public Event getDE() {
        return DE;
    }
    
    public Resource getCR() {
        return CR;
    }
    
    public Resource getER() {
        return ER;
    }
    
    public int getID() {
        return ID;
    }
}
