package spacetrader;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.io.Serializable;

public enum Government implements Serializable {
    ANARCHY("Anarchy", 0, 0, 9, 1, new TradeGood[0]),
    CAPITALIST("Captialist", 1, 2, 1, 7, new TradeGood[] {TradeGood.FIREARMS,
        TradeGood.NARCOTICS}),
    COMMUNIST("Communist", 2, 8, 0, 2, new TradeGood[] {TradeGood.FIREARMS,
        TradeGood.NARCOTICS}),
    CONFEDERACY("Confederacy", 3, 4, 2, 5, new TradeGood[] {TradeGood.FIREARMS,
        TradeGood.NARCOTICS}),
    CORPORATE("Corporate", 4, 5, 0, 5, new TradeGood[0]),
    CYBERNETIC("Cybernetic", 5, 3, 2, 5, new TradeGood[] {TradeGood.FIREARMS,
        TradeGood.NARCOTICS}),
    DEMOCRACY("Democracy", 6, 1, 1, 8, new TradeGood[0]),
    DICTATORSHIP("Dictatorship", 7, 4, 3, 3, new TradeGood[0]),
    FACIST("Facist", 8, 9, 0, 1, new TradeGood[] {TradeGood.FIREARMS,
        TradeGood.NARCOTICS}),
    FEUDAL("Feudal", 9, 0, 5, 5, new TradeGood[0]),
    MILITARY("Military State", 10, 7, 0, 3, new TradeGood[]
        {TradeGood.NARCOTICS}),
    MONARCHY("Monarchy", 11, 2, 2, 6, new TradeGood[0]),
    PACIFIST("Pacifist", 12, 1, 1, 8, new TradeGood[] {TradeGood.FIREARMS}),
    SOCIALIST("Socialist", 13, 2, 2, 6, new TradeGood[0]),
    SATORI("State of Satori", 14, 0, 0, 10, new TradeGood[] {TradeGood.FIREARMS,
        TradeGood.NARCOTICS}),
    TECHNOCRACY("Technocracy", 15, 4, 1, 5, new TradeGood[0]),
    THEOCRACY("Theocracy", 16, 5, 1, 4, new TradeGood[] {TradeGood.FIREARMS,
        TradeGood.NARCOTICS});
    
    private static final Random RAND = new Random();
    private final String name;
    private final int id;
    private final int police;
    private final int pirate;
    private final int trader;
    private final TradeGood[] illegalGoods;
    
    /**
     * Creates a Government.
     * @param name name of government
     * @param id id of government
     * @param policeChance chance of police encounter for the government
     * @param pirateChance chance of pirate encounter for the government
     * @param traderChance chance of trader encounter for the government
     */
    private Government(final String name, final int id, final int policeChance,
            final int pirateChance, final int traderChance, final TradeGood[]
            illegalGoods) {
        this.name = name;
        this.id = id;
        this.police = policeChance;
        this.pirate = pirateChance;
        this.trader = traderChance;
        this.illegalGoods = illegalGoods;
    }
    
    /**
     * Returns a random government, based partially on tech level.
     * @param techLevel tech level on which to base government
     * @return a random government appropriate to the tech level
     */
    public Government randomGovernment(final int techLevel) {
        //governments OK for any tech level added to the list initially
        List<Government> govts = new LinkedList<>(Arrays.asList(CAPITALIST,
                COMMUNIST, CONFEDERACY, DEMOCRACY, DICTATORSHIP, PACIFIST,
                SATORI));
        //more specific ones are added here
        if (techLevel == Context.HI_TECH) {
            govts.add(CYBERNETIC);
            govts.add(FACIST);
            govts.add(MILITARY);
            govts.add(TECHNOCRACY);
            govts.add(CORPORATE);
        } else if (techLevel == Context.POST_INDUSTRIAL) {
            govts.add(FACIST);
            govts.add(MILITARY);
            govts.add(TECHNOCRACY);
            govts.add(CORPORATE);
        } else if (techLevel == Context.INDUSTRIAL) {
            govts.add(ANARCHY);
            govts.add(FACIST);
            govts.add(MILITARY);
            govts.add(SOCIALIST);
            govts.add(TECHNOCRACY);
            govts.add(CORPORATE);
        } else if (techLevel == Context.EARLY_INDUSTRIAL) {
            govts.add(ANARCHY);
            govts.add(FACIST);
            govts.add(MILITARY);
            govts.add(MONARCHY);
            govts.add(SOCIALIST);
            govts.add(CORPORATE);
        } else if (techLevel == Context.RENAISSANCE) {
            govts.add(ANARCHY);
            govts.add(FEUDAL);
            govts.add(MILITARY);
            govts.add(MONARCHY);
            govts.add(SOCIALIST);
            govts.add(THEOCRACY);
        } else if (techLevel == Context.MEDIEVAL) {
            govts.add(ANARCHY);
            govts.add(FEUDAL);
            govts.add(MONARCHY);
            govts.add(SOCIALIST);
            govts.add(THEOCRACY);
        } else if (techLevel == Context.AGRICULTURE) {
            govts.add(ANARCHY);
            govts.add(FEUDAL);
            govts.add(MONARCHY);
            govts.add(SOCIALIST);
            govts.add(THEOCRACY);
        } else if (techLevel == Context.PRE_AGRICULTURE) {
            govts.add(ANARCHY);
            govts.add(FEUDAL);
            govts.add(MONARCHY);
            govts.add(SOCIALIST);
            govts.add(THEOCRACY);
        }
        return govts.get(RAND.nextInt(govts.size()));
    }
    
    /**
     * Returns government name.
     * @return government name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns government id.
     * @return government id
     */
    public int getID() {
        return id;
    }
    
    /**
     * Returns chance of police encounter.
     * @return int corresponding to chance of police encounter
     */
    public int getPolice() {
        return police;
    }
    
    /**
     * Returns chance of pirate encounter.
     * @return int corresponding to chance of pirate encounter
     */
    public int getPirate() {
        return pirate;
    }
    
    /**
     * Returns chance of trader encounter.
     * @return int corresponding to chance of trader encounter
     */
    public int getTrader() {
        return trader;
    }
    
    /**
     * Returns array of illegal goods
     * @return array listing illegal goods
     */
    public TradeGood[] getIllegalGoods() {
        return illegalGoods;
    }
    
    /**
     * Returns true if this government has the same name as the other
     * government.
     * @param other the other government
     * @return true if this government has the same name as the other government
     */
    public boolean isEqual(final Government other) {
        return other.getName().equals(this.getName());
    }
}
