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
    }
    
    /**
     * Constructs a planet with given coordinates.
     * @param x planet's x coordinate
     * @param y planet's y coordinate
     */
    public Planet(int x, int y) {
        this();
        this.coords = new Coordinate(x, y);
    }
    
    /**
     * Constructs a planet with given coordinates.
     * @param coords planet's coordinate pair
     */
    public Planet(Coordinate coords) {
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
    public Planet(String name, int techLevel, Resource resource, Government govt,
            int x, int y, SolarSystem parentSolarSystem) {
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
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the coordinates of the planet.
     * @return the coordinates of the planet
     */
    public Coordinate getCoords() {
        return this.coords;
    }
    
    /**
     * Returns the tech level of the planet.
     * @return the tech level of the planet
     */
    public int getTechLevel() {
        return this.techLevel;
    }
    
    /**
     * Returns the resources of the planet.
     * @return the resources of the planet
     */
    public Resource getResource() {
        return this.resource;
    }

    /**
     * Returns the resources of the planet.
     * @return the resources of the planet
     */
    public Government getGovernment() {
        return this.govt;
    }
    
    /**
     * Returns the event of the planet.
     * @return the event of the planet
     */
    public Event getEvent() {
        return this.event;
    }
    
    /**
     * Returns the parent solar system.
     * @return the parent solar system
     */
    public SolarSystem getParentSolarSystem() {
        return this.parentSolarSystem;
    }
    
    /**
     * Returns the current absolute location.
     * @return the current absolute location
     */
    public Coordinate getAbsoluteLocation() {
        return new Coordinate(this.parentSolarSystem.getCoords().getX()
                + Context.MIN_DISTANCE_BETWEEN_PLANETS
                        * this.coords.getX()/Context.BOUNDARY
                - Context.MIN_DISTANCE_BETWEEN_PLANETS,
                this.parentSolarSystem.getCoords().getY()
                        + Context.MIN_DISTANCE_BETWEEN_PLANETS
                                * this.coords.getY()/Context.BOUNDARY
                        - Context.MIN_DISTANCE_BETWEEN_PLANETS);
    }
    
    /**
     * Returns the distance between a planet and another planet.
     * 
     * @param otherPlanet the other planet
     * @return the distance between a planet and another planets
     */
    public double distanceToPlanet(Planet otherPlanet) {
        return Math.sqrt(Math.pow(Math.abs(this.getParentSolarSystem().getCoords().getX()
                + (this.getCoords().getX() - (double)Context.BOUNDARY/2)
                        * (double)2*Context.MIN_DISTANCE_BETWEEN_PLANETS/Context.BOUNDARY)
                - (otherPlanet.getParentSolarSystem().getCoords().getX()
                        + ((double)otherPlanet.getCoords().getX() - Context.BOUNDARY/2)
                                * (double)2*Context.MIN_DISTANCE_BETWEEN_PLANETS/Context.BOUNDARY), 2)
                + Math.pow(Math.abs(this.getParentSolarSystem().getCoords().getY()
                        + (this.getCoords().getY() - (double)Context.BOUNDARY/2)
                                * (double)2*Context.MIN_DISTANCE_BETWEEN_PLANETS/Context.BOUNDARY)
                        - (otherPlanet.getParentSolarSystem().getCoords().getY()
                                + ((double)otherPlanet.getCoords().getY() - Context.BOUNDARY/2)
                                        * (double)2*Context.MIN_DISTANCE_BETWEEN_PLANETS/Context.BOUNDARY), 2));
    }
    
    /**
     * Returns true if a planet is too close to another planet.
     * @param otherPlanet the other planet
     * @return true if the planets are too close
     */
    public boolean istooCloseTo(Planet otherPlanet) {
        return (Math.abs(this.coords.getX() - otherPlanet.getCoords().getX())
                < Context.MIN_DISTANCE_BETWEEN_PLANETS)
                || (Math.abs(this.coords.getY() - otherPlanet.getCoords().getY())
                < Context.MIN_DISTANCE_BETWEEN_PLANETS);
    }
    
    /**
     * Sets the planet name.
     * @param name planet name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public void setCoords(Coordinate coords) {
        this.coords = coords;
    }
    
    /**
     * Sets the planet event.
     * @param event event
     */
    public void setEvent(Event event) {
        this.event = event;
    }
    
    /**
     * Sets the parent solar system.
     * @param parentSolarSystem parent solar system
     */
    public void setParentSolarSystem(SolarSystem parentSolarSystem) {
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
    
    public Market getMarket() {
        return market;
    }
    
    public boolean equals(Planet other) {
        if (other.getAbsoluteLocation().getX() == this.getAbsoluteLocation().getX()) {
            if (other.getAbsoluteLocation().getY() == this.getAbsoluteLocation().getY()) {
                if (other.getName().equals(this.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
