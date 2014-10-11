package spacetrader;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Government {
    ANARCHY("Anarchy", 0, 0, 9, 1),
    CAPITALIST("Captialist", 1, 2, 1, 7), COMMUNIST("Communist", 2, 8, 0, 2),
    CONFEDERACY("Confederacy", 3, 4, 2, 5), CORPORATE("Corporate", 4, 5, 0, 5),
    CYBERNETIC("Cybernetic", 5, 3, 2, 5), DEMOCRACY("Democracy", 6, 1, 1, 8),
    DICTATORSHIP("Dictatorship", 7, 4, 3, 3), FACIST("Facist", 8, 9, 0, 1),
    FEUDAL("Feudal", 9, 0, 5, 5), MILITARY("Military State", 10, 7, 0, 3),
    MONARCHY("Monarchy", 11, 2, 2, 6), PACIFIST("Pacifist", 12, 1, 1, 8),
    SOCIALIST("Socialist", 13, 2, 2, 6), SATORI("State of Satori", 14, 0, 0, 10),
    TECHNOCRACY("Technocracy", 15, 4, 1, 5), THEOCRACY("Theocracy", 16, 5, 1, 4),;
    
    private static final Random rand = new Random();
    private static final List<Government> VALUES = Arrays.asList(values());
    private final String NAME;
    private final int ID;
    private final int POLICE;
    private final int PIRATE;    
    private final int TRADER;
    
    private Government(String name, int id, int policeChance, int pirateChance, int traderChance) {
        NAME = name;
        ID = id;
        POLICE = policeChance;
        PIRATE = pirateChance;
        TRADER = traderChance;
    }
    
    /**
     * Returns a random resource.
     * @return a random resource
     */
    public Government randomGovernment() {
        return VALUES.get(rand.nextInt(VALUES.size()));
    }
    
    /**
     * Returns government name
     * @return government name
     */
    public String getName() {
        return NAME;
    }
    
    /**
     * Returns government id
     * @return government id
     */
    public int getID() {
        return ID;
    }
    
    /**
     * Returns chance of police encounter
     * @return int corresponding to chance of police encounter
     */
    public int getPolice() {
        return POLICE;
    }
    
    /**
     * Returns chance of pirate encounter
     * @return int corresponding to chance of pirate encounter
     */
    public int getPirate() {
        return PIRATE;
    }
    
    /**
     * Returns chance of trader encounter
     * @return int corresponding to chance of trader encounter
     */
    public int getTrader() {
        return TRADER;
    }
    
    public boolean equals(Government other) {
        if (other.getName().equals(this.getName())){
            return true;
        }
        return false;
    }
    
}
