package spacetrader;
import java.util.Random;
/**
 * Enum for all of the different trade goods in Space Trader.
 *
 */
public enum TradeGood {
    WATER(0, 0, 2, 30, 3, 4, Event.DROUGHT, Event.NONE, Resource.LOTSOFWATER, Resource.DESERT, 30, 50),
    FURS(0, 0, 0, 250, 10, 10, Event.COLD, Event.MANYHUNTERS, Resource.RICHFAUNA, Resource.LIFELESS, 230, 280),
    FOOD(1, 0, 1, 100, 5, 5, Event.CROPFAIL, Event.HARVEST, Resource.RICHSOIL, Resource.POORSOIL, 90, 160),
    ORE(2, 2, 3, 350, 20, 10, Event.WAR, Event.NONE, Resource.MINERALRICH, Resource.MINERALPOOR, 350, 420),
    GAMES(3, 1, 6, 250, -10, 5, Event.BOREDOM, Event.NONE, Resource.ARTISTIC, Resource.NOSPECIALRESOURCES, 160, 270),
    FIREARMS(3, 1, 5, 1250, -75, 100, Event.CRIMEWAVE, Event.POLICE, Resource.WARLIKE, Resource.PACIFIST, 600, 1100),
    MEDICINE(4, 1, 6, 650, -20, 10, Event.PLAGUE, Event.NONE, Resource.LOTSOFHERBS, Resource.LIFELESS, 400, 700),
    MACHINES(4, 3, 5, 900, -30, 5, Event.LACKOFWORKERS, Event.LUDDITES, Resource.NOSPECIALRESOURCES, Resource.NOSPECIALRESOURCES, 600, 800),
    NARCOTICS(5, 0, 5, 3500, -125, 150, Event.POLICE, Event.STRAIGHTEDGE, Resource.WEIRDMUSHROOMS, Resource.NOSPECIALRESOURCES, 2000, 3000),
    ROBOTS(6, 4, 7, 5000, -150, 100, Event.LACKOFWORKERS, Event.LUDDITES, Resource.NOSPECIALRESOURCES, Resource.NOSPECIALRESOURCES, 3500, 5000);
    
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
     */
    
    private final int MTLB, MTLS, TTP, BASEPRICE, IPL, VARIANCE, RTL, RTH;
    private final Event IE, DE;
    private final Resource CR, ER;
    
    private TradeGood(int mtlb, int mtls, int ttp, int basePrice, int ipl,
            int variance, Event ie, Event de, Resource cr, Resource er, int rtl, int rth) {
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
        int price = BASEPRICE + (IPL * (MTLB)) + r.nextInt(VARIANCE + 1);
        //if current planet tech level = TTP, 5% decrease in price
        //if current event = IE, 20% increase in price
        //if current event = DE, 20% decrease in price
        //if conditions = CR, 15% increase in price
        //if conditions = ER, 15% decrease in price
        return price;
    }
    
    /**
     *
     * @return
     */
    public int calcTraderPrice() {
        Random r = new Random();
        return RTL + r.nextInt(RTH - RTL);
    }
}
