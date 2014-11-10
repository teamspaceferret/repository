package spacetrader;

import java.io.Serializable;

public class Planet implements Serializable{
    private String name;
    private Coordinate coords;
    private int techLevel;
    private Resource resource;
    private Government govt;
    private Event event;
    private SolarSystem parentSolarSystem;
    private Market market;
    private Shipyard shipyard;
    
    /**
     * Constructs a planet with default values.
     */
    public Planet() {
        this.name = "";
        this.coords = new Coordinate();
        this.techLevel = 0;
        this.resource = Resource.NOSPECIALRESOURCES;
        this.govt = Government.ANARCHY;
        this.event = Event.NONE;
        this.market = new Market(this);
        this.shipyard = new Shipyard(this);
    }
    
    /**
     * Constructs a planet with given coordinates.
     * @param x planet's x coordinate
     * @param y planet's y coordinate
     */
    public Planet(final int x, final int y) {
        this();
        this.coords = new Coordinate(x, y);
    }
    
    /**
     * Constructs a planet with given coordinates.
     * @param coords planet's coordinate pair
     */
    public Planet(final Coordinate coords) {
        this();
        this.coords = coords;
    }
    
    /**
     * Constructs a planet with given values.
     * @param name planet's name
     * @param techLevel which tech level planet has
     * @param resource which special resource planet has
     * @param govt which government planet has
     * @param x the x coordinate of the parent
     * @param y the y coordinate of the parent
     * @param parentSolarSystem the parent solar system
     */
    public Planet(final String name, final int techLevel
            , final Resource resource, final Government govt, final int x
            , final int y, final SolarSystem parentSolarSystem) {
        this();
        this.name = name;
        this.techLevel = techLevel;
        this.resource = resource;
        this.govt = govt;
        this.coords = new Coordinate(x, y);
        this.parentSolarSystem = parentSolarSystem;
    }
    
    /**
     * Returns the name of the planet.
     * @return the name of the planet
     */
    public final String getName() {
        return this.name;
    }
    
    /**
     * Returns the coordinates of the planet.
     * @return the coordinates of the planet
     */
    public final Coordinate getCoords() {
        return this.coords;
    }
    
    /**
     * Returns the tech level of the planet.
     * @return the tech level of the planet
     */
    public final int getTechLevel() {
        return this.techLevel;
    }
    
    /**
     * Returns the resources of the planet.
     * @return the resources of the planet
     */
    public final Resource getResource() {
        return this.resource;
    }

    /**
     * Returns the resources of the planet.
     * @return the resources of the planet
     */
    public final Government getGovernment() {
        return this.govt;
    }
    
    /**
     * Returns the event of the planet.
     * @return the event of the planet
     */
    public final Event getEvent() {
        return this.event;
    }
    
    /**
     * Returns the parent solar system.
     * @return the parent solar system
     */
    public final SolarSystem getParentSolarSystem() {
        return this.parentSolarSystem;
    }
    
    /**
     * Returns the current absolute location.
     * @return the current absolute location
     */
    public final Coordinate getAbsoluteLocation() {
        return new Coordinate(this.parentSolarSystem.getCoords().getX()
                + Context.MIN_DISTANCE_BETWEEN_PLANETS
                        * this.coords.getX() / Context.BOUNDARY_VISIBLE
                - Context.MIN_DISTANCE_BETWEEN_PLANETS,
                this.parentSolarSystem.getCoords().getY()
                        + Context.MIN_DISTANCE_BETWEEN_PLANETS
                                * this.coords.getY() / Context.BOUNDARY_VISIBLE
                        - Context.MIN_DISTANCE_BETWEEN_PLANETS);
    }
    
    /**
     * Returns the distance between a planet and another planet.
     * @param otherPlanet the other planet
     * @return the distance between a planet and another planets
     */
    public final  double distanceToPlanet(Planet otherPlanet) {
        return Math.sqrt(Math.pow(Math.abs(this.getParentSolarSystem().getCoords().getX()
                + (this.getCoords().getX() - (double) Context.BOUNDARY_VISIBLE / 2)
                        * (double) 2 * Context.MIN_DISTANCE_BETWEEN_PLANETS / Context.BOUNDARY_VISIBLE)
                - (otherPlanet.getParentSolarSystem().getCoords().getX()
                        + ((double) otherPlanet.getCoords().getX() - Context.BOUNDARY_VISIBLE / 2)
                                * (double) 2 * Context.MIN_DISTANCE_BETWEEN_PLANETS / Context.BOUNDARY_VISIBLE), 2)
                + Math.pow(Math.abs(this.getParentSolarSystem().getCoords().getY()
                        + (this.getCoords().getY() - (double) Context.BOUNDARY_VISIBLE / 2)
                                * (double) 2 * Context.MIN_DISTANCE_BETWEEN_PLANETS / Context.BOUNDARY_VISIBLE)
                        - (otherPlanet.getParentSolarSystem().getCoords().getY()
                                + ((double) otherPlanet.getCoords().getY() - Context.BOUNDARY_VISIBLE / 2)
                                        * (double) 2 * Context.MIN_DISTANCE_BETWEEN_PLANETS / Context.BOUNDARY_VISIBLE), 2));
    }
    
    /**
     * Sets the planet name.
     * @param name planet name
     */
    public final void setName(final String name) {
        this.name = name;
    }
    
    /**
     * Sets the planet coords.
     * @param coords planet coords
     */
    public final void setCoords(final Coordinate coords) {
        this.coords = coords;
    }
    
    /**
     * Sets the planet government.
     * @param government planet government
     */
    public final void setGovernment(final Government government) {
        this.govt = government;
    }
    
    /**
     * Sets the planet event.
     * @param event event
     */
    public final void setEvent(final Event event) {
        this.event = event;
    }
    
    /**
     * Sets the parent solar system.
     * @param parentSolarSystem parent solar system
     */
    public final void setParentSolarSystem(final SolarSystem parentSolarSystem) {
        this.parentSolarSystem = parentSolarSystem;
    }
    
    /**
     * Returns a string representation of the planet.
     * @return a string representation of the planet
     */
    @Override
    public String toString() {
        String string = this.name + " at " + this.coords + " with techLevel "
                + Context.TECH_LEVELS[techLevel] + ", resource "
                + resource.getName() + ", govt "
                + govt.getName() + ", and event "
                + event.getName();
        return string;
    }
    
    /**
     * Gets the planet's market.
     * @return the market of the planet
     */
    public final Market getMarket() {
        return market;
    }
    
    /**
     * Gets the planet's shipyard.
     * @return the shipyard of the planet
     */
    public final Shipyard getShipyard() {
        return shipyard;
    }
    
    /**
     * Compares the planets, and if they are the same, returns true, otherwise
     * it returns false.
     * @param other the other planet to compare against
     * @return true if planets are equal, false otherwise
     */
    public final boolean isEqual(final Planet other) {
        return this.coords.equals(other.getCoords())
                && this.name.equals(other.getName());
    }
}
