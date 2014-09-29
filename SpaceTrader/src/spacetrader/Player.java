package spacetrader;

public class Player {
    
    private String name;
    private int[] stats;
    private SolarSystem currentSolar;
    private Planet currentPlanet;
    private int credits;
    private Ship ship;
    
    /**
     * Constructs a player without a name or stats and 0 credits
     */
    public Player() {
        this.name = null;
        this.stats = null;
        this.credits = 0;
    }
    
    /**
     * Constructs a player with the given stats and name
     * and gives them the default number of credits and default ship
     * @param name The name for the player
     * @param stats The stats for the player
     */
    public Player(String name, int[] stats) {
        this.name = name;
        this.stats = stats;
        this.credits = 1000;
        this.ship = new Ship("gnat");
    }
    
    /**
     * Gets the player's name
     * @return The player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the player's ship
     * @return  The player's ship
     */
    public Ship getShip(){
        return ship;
    }
    
    /**
     * Gets the player's credits
     * @return The player's credits
     */
    public int getCredits(){
        return credits;
    }
    
    /**
     * Gets the player's stats
     * @return the array of the player's stats
     */
    public int[] getStats() {
        return stats;
    }
    
    /**
     * Gets the player's investor level
     * @return The value of the investor stat
     */
    public int getInvestor() {
        return stats[0];
    }
    
    /**
     * Gets the player's pilot level
     * @return The value of the pilot stat
     */
    public int getPilot() {
        return stats[1];
    }
    
    /**
     * Gets the player's trader level
     * @return The value of the trader stat
     */
    public int getTrader() {
        return stats[2];
    }
    
    /**
     * Gets the player's fighter level
     * @return The value of the fighter stat
     */
    public int getFighter() {
        return stats[3];
    }
    
    /**
     * Gets the player's engineer level
     * @return The value of the engineer stat
     */
    public int getEngineer() {
        return stats[4];
    }
    
    /**
     * Sets the name of the player
     * @param name Name of the player
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the player's skill stats
     * @param stats The array representing the player's stats
     */
    public void setStates(int[] stats) {
        this.stats = stats;
    }
    /**
     * Sets the currently visited solar system
     * @param solarSystem the current solar system
     */
    public void setCurrentSolar(SolarSystem solarSystem) {
        currentSolar = solarSystem;
    }
    /**
     * Sets the currently visited planet
     * @param planet the current planet
     */
    public void setCurrentPlanet(Planet planet) {
        currentPlanet = planet;
    }
    /**
     * Returns current solar system
     * @return current solar system the player is in
     */
    public SolarSystem getCurrentSolar() {
        return currentSolar;
    }
    /**
     * Returns current planet
     * @return current planet the player is at
     */
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }
    /**
     * Makes a string representation of the player
     * @return a string representation of the player
     */
    public String toString() {
        return name + " with stats: Investor: " + stats[0] + " Pilot: "
                + stats[1] + " Trader: " + stats[2] + " Fighter: " + stats[3] 
                + " Engineer: " + stats[4];
    }
}
