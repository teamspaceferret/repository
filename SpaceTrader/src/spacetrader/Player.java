package spacetrader;

public class Player {
    
    private String name;
    private int[] stats;
    
    public Player() {
        this.name = null;
        this.stats = null;
    }
    
    public Player(String name, int[] stats) {
        this.name = name;
        this.stats = stats;
    }
    
    public String getName() {
        return name;
    }
    
    public int[] getStats() {
        return stats;
    }
    
    public int getInvestor() {
        return stats[0];
    }
    
    public int getPilot() {
        return stats[1];
    }
    
    public int getTrader() {
        return stats[2];
    }
    
    public int getFighter() {
        return stats[3];
    }
    
    public int getEngineer() {
        return stats[4];
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setStates(int[] stats) {
        this.stats = stats;
    }
    
    public String toString() {
        return name + " with stats: Investor: " + stats[0] + " Pilot: "
                + stats[1] + " Trader: " + stats[2] + " Fighter: " + stats[3] 
                + " Engineer: " + stats[4];
    }
}
