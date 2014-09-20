package spacetrader;

public class Context {
    private final static Context instance = new Context();
    
    public static Context getInstance() {
        return instance;
    }
    
    public static final int NUM_SOLAR_SYSTEMS = 6;
    public static final int MIN_PLANETS_PER_SOLAR_SYSTEM = 4;
    public static final int MAX_PLANETS_PER_SOLAR_SYSTEM = 8;
    
    private Player player = new Player();
    private Universe universe = new Universe();
    
    public Player getPlayer() {
        return player;
    }
    
    public Universe getUniverse() {
        return universe;
    }
}
