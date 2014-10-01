package spacetrader;

public class Planet {
    private String name;
    private Coordinate coords;
    private int techLevel;
    private Resource resource;
    private int govt;
    private Event event;
    private SolarSystem parentSolarSystem;
    
    /**
     * Constructs a planet with default values.
     */
    public Planet() {
        this.name = "";
        this.coords = new Coordinate();
        this.techLevel = 0;
        this.resource = Resource.NOSPECIALRESOURCES;
        this.govt = 0;
        this.event = Event.NONE;
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
     * @param x the x coordinate of the parent
     * @param y the y coordinate of the parent
     * @param parentSolarSystem the parent solar system
     */
    public Planet(String name, int techLevel, Resource resource, int govt,
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
     * Returns the distance between a planet and another planet.
     * 
     * @param otherPlanet the other planet
     * @return the distance between a planet and another planets
     */
    public double distanceToPlanet(Planet otherPlanet) {
        System.out.println("this.parent: " + this.getParentSolarSystem().getCoords());
        System.out.println("this.x: " + String.valueOf(this.getParentSolarSystem().getCoords().getX()+(this.getCoords().getX() - Context.BOUNDARY/2)*2*Context.MIN_DISTANCE_BETWEEN_PLANETS/Context.BOUNDARY));
        System.out.println("this.y: " + String.valueOf(this.getParentSolarSystem().getCoords().getY()+(this.getCoords().getY() - Context.BOUNDARY/2)*2*Context.MIN_DISTANCE_BETWEEN_PLANETS/Context.BOUNDARY));
        System.out.println("other.parent: " + otherPlanet.getParentSolarSystem().getCoords());
        System.out.println("other.x: " + String.valueOf(otherPlanet.getParentSolarSystem().getCoords().getX()+(otherPlanet.getCoords().getX() - Context.BOUNDARY/2)*2*Context.MIN_DISTANCE_BETWEEN_PLANETS/Context.BOUNDARY));
        System.out.println("other.y: " + String.valueOf(otherPlanet.getParentSolarSystem().getCoords().getY()+(otherPlanet.getCoords().getY() - Context.BOUNDARY/2)*2*Context.MIN_DISTANCE_BETWEEN_PLANETS/Context.BOUNDARY));
        
        return Math.sqrt(Math.pow(Math.abs(this.getParentSolarSystem().getCoords().getX()
                + (this.getCoords().getX() - Context.BOUNDARY/2)
                        * 2*Context.MIN_DISTANCE_BETWEEN_PLANETS/Context.BOUNDARY)
                - (otherPlanet.getParentSolarSystem().getCoords().getX()
                        + (otherPlanet.getCoords().getX() - Context.BOUNDARY/2)
                                * 2*Context.MIN_DISTANCE_BETWEEN_PLANETS/Context.BOUNDARY), 2)
                + Math.pow(Math.abs(this.getParentSolarSystem().getCoords().getY()
                        + (this.getCoords().getY() - Context.BOUNDARY/2)
                                * 2*Context.MIN_DISTANCE_BETWEEN_PLANETS/Context.BOUNDARY)
                        - (otherPlanet.getParentSolarSystem().getCoords().getY()
                                + (otherPlanet.getCoords().getY() - Context.BOUNDARY/2)
                                        * 2*Context.MIN_DISTANCE_BETWEEN_PLANETS/Context.BOUNDARY), 2));
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
                + Context.GOVERNMENTS[govt] + ", and event "
                + event.getName();
        return string;
    }
}
