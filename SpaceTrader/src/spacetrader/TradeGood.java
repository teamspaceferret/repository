package spacetrader;
import java.util.Random;
/**
 * Enum for all of the different trade goods in Space Trader.
 *
 */
public enum TradeGood {
    WATER(0, 0, 2, 30, 3, 4, SE.DROUGHT, SE.NONE, SC.LOTSOFWATER, SC.DESERT, 30, 50),
    FURS(0, 0, 0, 250, 10, 10, SE.COLD, SE.MANYHUNTERS, SC.RICHFAUNA, SC.LIFELESS, 230, 280),
    FOOD(1, 0, 1, 100, 5, 5, SE.CROPFAIL, SE.HARVEST, SC.RICHSOIL, SC.POORSOIL, 90, 160),
    ORE(2, 2, 3, 350, 20, 10, SE.WAR, SE.NONE, SC.MINERALRICH, SC.MINERALPOOR, 350, 420),
    GAMES(3, 1, 6, 250, -10, 5, SE.BOREDOM, SE.NONE, SC.ARTISTIC, SC.NONE, 160, 270),
    FIREARMS(3, 1, 5, 1250, -75, 100, SE.CRIMEWAVE, SE.POLICE, SC.WARLIKE, SC.PACIFIST, 600, 1100),
    MEDICINE(4, 1, 6, 650, -20, 10, SE.PLAGUE, SE.NONE, SC.LOTSOFHERBS, SC.LIFELESS, 400, 700),
    MACHINES(4, 3, 5, 900, -30, 5, SE.LACKOFWORKERS, SE.LUDDITES, SC.NONE, SC.NONE, 600, 800),
    NARCOTICS(5, 0, 5, 3500, -125, 150, SE.POLICE, SE.STRAIGHTEDGE, SC.WEIRDMUSHROOMS, SC.NONE, 2000, 3000),
    ROBOTS(6, 4, 7, 5000, -150, 100, SE.LACKOFWORKERS, SE.LUDDITES, SC.NONE, SC.NONE, 3500, 5000);
    
    /**
     * TradeGoods have many parameters.
     * 
     * @param MTLB Minimum Tech Level to Buy this resource
     * @param MTLS Minimum Tech Level to Sell this resource
     * @param TTP Tech level which produces the most of this resource
     * @param basePrice Base price of this resource
     * @param IPL Price increase per tech level
     * @param variance max percent this good can vary above or below the base
     * @param IE price Increase Event, raises price astronomically
     * @param DE price Decrease Event, drops price astronomically
     * @param CR if this is true, price of this good is unusually low
     * @param ER if this is true, price of this good is expensive
     * @param RTL Random Trader Lowest price, outside of marketplace
     * @param RTH Random Trader Highest price, outside of marketplace
     */
    
    private final int MTLB, MTLS, TTP, BASEPRICE, IPL, VARIANCE, RTL, RTH;
    private final SE IE, DE;
    private final SC CR, ER;
    
    private TradeGood(int mtlb, int mtls, int ttp, int basePrice, int ipl,
            int variance, SE ie, SE de, SC cr, SC er, int rtl, int rth) {
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
    
    public int calcTraderPrice() {
        Random r = new Random();
        return RTL + r.nextInt(RTH - RTL);
    }
}
