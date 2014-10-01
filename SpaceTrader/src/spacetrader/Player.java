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
        this.credits = 1000;
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
     * Adds the given number of credits to the player's credit amount
     * Returns true if the addition was successful, false otherwise
     * @param creditsToAdd the number of credits to add
     * @return true if successful, false otherwise
     */
    public boolean addCredits(int creditsToAdd){
        if (this.credits < 0){
            System.out.println("Cannot add negative number");
            return false;
        }
        else {
            this.credits += creditsToAdd;
            return true;
        }   
    }
    
    /**
     * Removes the given number of credits from the player's credit pool
     * Returns true if the removal succeeded
     * If the player does not have enough credits to remove the given amount, returns false
     * @param creditsToRemove the number of credits to remove from the player
     * @return true if the operation succeeded, false otherwise
     */
    public boolean removeCredits(int creditsToRemove){
        int newCreditAmt = this.credits - creditsToRemove;
        if(newCreditAmt < 0 || creditsToRemove < 0){
            System.out.println("Not enough credits to remove that amount");
            return false;
        }
        else {
            this.credits = newCreditAmt;
            return true;
        }   
    }
    
    /**
     * Gets the player's name
     * @return The player's name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gets the player's ship
     * @return  The player's ship
     */
    public Ship getShip(){
        return this.ship;
    }
    
    /**
     * Gets the player's credits
     * @return The player's credits
     */
    public int getCredits(){
        return this.credits;
    }
    
    /**
     * Gets the player's stats
     * @return the array of the player's stats
     */
    public int[] getStats() {
        return this.stats;
    }
    
    /**
     * Gets the player's investor level
     * @return The value of the investor stat
     */
    public int getInvestor() {
        return this.stats[0];
    }
    
    /**
     * Gets the player's pilot level
     * @return The value of the pilot stat
     */
    public int getPilot() {
        return this.stats[1];
    }
    
    /**
     * Gets the player's trader level
     * @return The value of the trader stat
     */
    public int getTrader() {
        return this.stats[2];
    }
    
    /**
     * Gets the player's fighter level
     * @return The value of the fighter stat
     */
    public int getFighter() {
        return this.stats[3];
    }
    
    /**
     * Gets the player's engineer level
     * @return The value of the engineer stat
     */
    public int getEngineer() {
        return this.stats[4];
    }
    
    public Planet getCurrentPlanet() {
        return this.currentPlanet;
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
    public void setStats(int[] stats) {
        this.stats = stats;
    }
    
    /**
     * Sets the currently visited planet
     * @param planet the current planet
     */
    public void setCurrentPlanet(Planet planet) {
        currentPlanet = planet;
    }
    
    /**
     * Sets the current ship
     * @param newShip ship that is being set
     */
    public void setShip(Ship newShip){
        ship = newShip;
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
