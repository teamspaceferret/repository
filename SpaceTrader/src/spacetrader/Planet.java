package spacetrader;

public class Planet {
    private String name;
    private Coordinate coords;
    private int techLevel;
    private int resource;
    private int govt;
    private int event;
    
    /**
     * Constructs a planet with default values.
     */
    public Planet() {
        this.name = "";
        this.coords = new Coordinate();
        this.techLevel = 0;
        this.resource = 0;
        //this.resource = Resource.NOSPECIALRESOURCES;
        this.govt = 0;
        this.event = 0;
        //this.event = Event.NONE;
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
     * Constructs a planet with given values.
     * @param name planet's name
     * @param techLevel which tech level planet has
     * @param resource which special resource planet has
     * @param govt which government planet has
     * @param x planet's x coordinate
     * @param y planet's y coordinate
     * @param event
     */
    public Planet(String name, int techLevel, int resource, int govt, int x, int y, int event) {
        this.name = name;
        this.techLevel = techLevel;
        this.resource = resource;
        this.govt = govt;
        this.coords = new Coordinate(x, y);
        this.event = event;
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
     * Returns a string representation of the planet.
     * @return a string representation of the planet
     */
    @Override
    public String toString() {
        String string = this.name + " at " + this.coords + " with techLevel "
                + Context.TECH_LEVELS[techLevel] + ", resource "
                + Context.RESOURCES[resource] + ", govt "
                + Context.GOVERNMENTS[govt] + ", and event "
                + Context.EVENTS[event];
        return string;
    }
}
