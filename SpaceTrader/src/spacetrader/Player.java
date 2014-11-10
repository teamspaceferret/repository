package spacetrader;

import java.io.Serializable;

public class Player implements Serializable{
    
    private String name;
    private int[] stats;
    private Planet currentPlanet;
    private Planet previousPlanet;
    private int credits;
    private Ship ship;
    
    /**
     * Constructs a player without a name or stats and 0 credits.
     */
    public Player() {
        this.name = null;
        this.stats = null;
        this.credits = Context.STARTING_CREDITS;
    }
    
    /**
     * Constructs a player with the given stats and name
     * and gives them the default number of credits and default ship.
     * @param name The name for the player
     * @param stats The stats for the player
     */
    public Player(final String name, final int[] stats) {
        this.name = name;
        this.stats = stats;
        this.credits = Context.STARTING_CREDITS;
        this.ship = new Ship("gnat");
    }
    
    /**
     * Adds the given number of credits to the player's credit amount.
     * Returns true if the addition was successful, false otherwise
     * @param creditsToAdd the number of credits to add
     * @return true if successful, false otherwise
     */
    public final boolean addCredits(final int creditsToAdd) {
        if (this.credits < 0) {
            System.out.println("Cannot add negative number");
            return false;
        } else {
            this.credits += creditsToAdd;
            return true;
        }
    }
    
    /**
     * Removes the given number of credits from the player's credit pool.
     * Returns true if the removal succeeded
     * If the player does not have enough credits to remove the given amount
     * and returns false
     * @param creditsToRemove the number of credits to remove from the player
     * @return true if the operation succeeded, false otherwise
     */
    public final boolean removeCredits(final int creditsToRemove) {
        int newCreditAmt = this.credits - creditsToRemove;
        if (newCreditAmt < 0 || creditsToRemove < 0) {
            System.out.println("Not enough credits to remove that amount");
            return false;
        } else {
            this.credits = newCreditAmt;
            return true;
        }
    }
    
    /**
     * Gets the player's name.
     * @return The player's name
     */
    public final String getName() {
        return this.name;
    }
    
    /**
     * Gets the player's ship.
     * @return  The player's ship
     */
    public final Ship getShip() {
        return this.ship;
    }
    
    /**
     * Gets the player's credits.
     * @return The player's credits
     */
    public final int getCredits() {
        return this.credits;
    }
    
    /**
     * Gets the player's stats.
     * @return the array of the player's stats
     */
    public final int[] getStats() {
        return this.stats;
    }
    
    /**
     * Gets the player's investor level.
     * @return The value of the investor stat
     */
    public final int getInvestor() {
        return this.stats[Context.INVESTOR_ID];
    }
    
    /**
     * Gets the player's pilot level.
     * @return The value of the pilot stat
     */
    public final int getPilot() {
        return this.stats[Context.PILOT_ID];
    }
    
    /**
     * Gets the player's trader level.
     * @return The value of the trader stat
     */
    public final int getTrader() {
        return this.stats[Context.TRADER_ID];
    }
    
    /**
     * Gets the player's fighter level.
     * @return The value of the fighter stat
     */
    public final int getFighter() {
        return this.stats[Context.FIGHTER_ID];
    }
    
    /**
     * Gets the player's engineer level.
     * @return The value of the engineer stat
     */
    public final int getEngineer() {
        return this.stats[Context.ENGINEER_ID];
    }
    
    /**
     * Returns the current planet.
     * @return the current planet
     */
    public final Planet getCurrentPlanet() {
        return this.currentPlanet;
    }
    
    /**
     * Returns the previous planet.
     * @return the previous planet
     */
    public final Planet getPreviousPlanet() {
        return this.previousPlanet;
    }
    
    /**
     * Returns the current absolute location.
     * @return the current absolute location
     */
    public final Coordinate getAbsoluteLocation() {
        return new Coordinate(this.currentPlanet.getParentSolarSystem().getCoords().getX()
                + Context.MIN_DISTANCE_BETWEEN_PLANETS
                        * this.currentPlanet.getCoords().getX() / Context.BOUNDARY_VISIBLE
                - Context.MIN_DISTANCE_BETWEEN_PLANETS,
                this.currentPlanet.getParentSolarSystem().getCoords().getY()
                        + Context.MIN_DISTANCE_BETWEEN_PLANETS
                                * this.currentPlanet.getCoords().getY() / Context.BOUNDARY_VISIBLE
                        - Context.MIN_DISTANCE_BETWEEN_PLANETS);
    }
    
    /**
     * Sets the name of the player.
     * @param name Name of the player
     */
    public final void setName(final String name) {
        this.name = name;
    }
    
    /**
     * Sets the player's skill stats.
     * @param stats The array representing the player's stats
     */
    public final void setStats(final int[] stats) {
        this.stats = stats;
    }
    
    /**
     * Sets the currently visited planet.
     * @param planet the current planet
     */
    public final void setCurrentPlanet(final Planet planet) {
        currentPlanet = planet;
    }
    
    /**
     * Sets the previously visited planet.
     * @param planet the previous planet
     */
    public final void setPreviousPlanet(final Planet planet) {
        previousPlanet = planet;
    }
    
    /**
     * Sets the current ship.
     * @param newShip ship that is being set
     */
    public final void setShip(final Ship newShip){
        ship = newShip;
    }
    
    /**
     * Makes a string representation of the player.
     * @return a string representation of the player
     */
    @Override
    public String toString() {
        return name + " with stats: Investor: " + stats[Context.INVESTOR_ID]
                + " Pilot: " + stats[Context.PILOT_ID] + " Trader: "
                + stats[Context.TRADER_ID] + " Fighter: "
                + stats[Context.FIGHTER_ID] + " Engineer: "
                + stats[Context.ENGINEER_ID];
    }
}
