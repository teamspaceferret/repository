package spacetrader;

public class Context {
    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    private Player player = new Player();
    private Universe universe = new Universe();

    public Player getPlayer() {
        return player;
    }
    
    public Universe getUniverse(){
        return universe;
    }
}
