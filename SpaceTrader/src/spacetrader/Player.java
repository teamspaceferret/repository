package spacetrader;

public class Player {
    
    private String name;
    private int[] stats;
    
    public Player(String name, int[] stats) {
        this.name = name;
        this.stats = stats;
    }
    
    public int[] getStats() {
        return stats;
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return name + " with stats: Investor: " + stats[0] + " Pilot: " + stats[1]
                + " Trader: " + stats[2] + " Fighter: " + stats[3] 
                + " Engineer: " + stats[4];
    }
    
}
