package spacetrader;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.io.Serializable;

public enum Government implements Serializable{
    ANARCHY("Anarchy", 0, 0, 9, 1),
    CAPITALIST("Captialist", 1, 2, 1, 7), COMMUNIST("Communist", 2, 8, 0, 2),
    CONFEDERACY("Confederacy", 3, 4, 2, 5), CORPORATE("Corporate", 4, 5, 0, 5),
    CYBERNETIC("Cybernetic", 5, 3, 2, 5), DEMOCRACY("Democracy", 6, 1, 1, 8),
    DICTATORSHIP("Dictatorship", 7, 4, 3, 3), FACIST("Facist", 8, 9, 0, 1),
    FEUDAL("Feudal", 9, 0, 5, 5), MILITARY("Military State", 10, 7, 0, 3),
    MONARCHY("Monarchy", 11, 2, 2, 6), PACIFIST("Pacifist", 12, 1, 1, 8),
    SOCIALIST("Socialist", 13, 2, 2, 6),
    SATORI("State of Satori", 14, 0, 0, 10),
    TECHNOCRACY("Technocracy", 15, 4, 1, 5),
    THEOCRACY("Theocracy", 16, 5, 1, 4);
    
    private static final Random RAND = new Random();
    private final String name;
    private final int id;
    private final int police;
    private final int pirate;    
    private final int trader;
    
    private Government(final String name, int id, int policeChance,
            int pirateChance, int traderChance) {
        this.name = name;
        this.id = id;
        this.police = policeChance;
        this.pirate = pirateChance;
        this.trader = traderChance;
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
        if (techLevel == 7) {
            govts.add(CYBERNETIC);
            govts.add(FACIST);
            govts.add(MILITARY);
            govts.add(TECHNOCRACY);
            govts.add(CORPORATE);
        } else if (techLevel == 6) {
            govts.add(FACIST);
            govts.add(MILITARY);
            govts.add(TECHNOCRACY);
            govts.add(CORPORATE);
        } else if (techLevel == 5) {
            govts.add(ANARCHY);
            govts.add(FACIST);
            govts.add(MILITARY);
            govts.add(SOCIALIST);
            govts.add(TECHNOCRACY);
            govts.add(CORPORATE);
        } else if (techLevel == 4) {
            govts.add(ANARCHY);
            govts.add(FACIST);
            govts.add(MILITARY);
            govts.add(MONARCHY);
            govts.add(SOCIALIST);
            govts.add(CORPORATE);
        } else if (techLevel == 3) {
            govts.add(ANARCHY);
            govts.add(FEUDAL);
            govts.add(MILITARY);
            govts.add(MONARCHY);
            govts.add(SOCIALIST);
            govts.add(THEOCRACY);
        } else if (techLevel == 2) {
            govts.add(ANARCHY);
            govts.add(FEUDAL);
            govts.add(MONARCHY);
            govts.add(SOCIALIST);
            govts.add(THEOCRACY);
        } else if (techLevel == 1) {
            govts.add(ANARCHY);
            govts.add(FEUDAL);
            govts.add(MONARCHY);
            govts.add(SOCIALIST);
            govts.add(THEOCRACY);
        } else if (techLevel == 0) {
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
     * Returns chance of trader encounter
     * @return int corresponding to chance of trader encounter
     */
    public int getTrader() {
        return trader;
    }
    
    /**
     * Returns true if this government has the same name as the other
     * government.
     * @param other the other government
     * @return true if this government has the same name as the other government
     */
    public boolean equals(Government other) {
        return other.getName().equals(this.getName());
    }
}
