package spacetrader;

public class Planet {
    //make some int things for the techlvl, resource, and govt
    private String name;
    private Coordinate coords;
    private int techLevel;
    private SC specialResources;
    private int government;
    private double chancePolice, chancePirate, chanceTrader;
    private SE specialEvent; //make not-an-int?? Probably
    
    /**
     * Constructs a planet with default values
     */
    public Planet() {
        this.name = "Name";
        this.coords = new Coordinate();
        this.techLevel = 0;
        this.government = 0;
        this.specialResources = SC.NONE;
    }
    
    /**
     * Constructs a planet with given coordinates
     * @param x planet's x coordinate
     * @param y planet's y coordinate
     */
    public Planet(int x, int y) {
        this();
        this.coords = new Coordinate(x, y);
    }
    
    /**
     * Constructs a planet with given values
     * @param name planet's name
     * @param tech which tech level planet has
     * @param resource which special resource planet has
     * @param govt which government planet has
     * @param x planet's x coordinate
     * @param y planet's y coordinate
     * @param specialEvent event happening on planet
     */
    public Planet(String name, int tech, SC resource, int govt, int x, int y, SE specialEvent) {
        this.name = name;
        this.techLevel = tech;
        this.specialResources = resource;
        this.government = govt;
        this.coords = new Coordinate(x, y);
        this.specialEvent = specialEvent;
        this.chancePolice = 0;
        this.chancePirate = 0;
        this.chanceTrader = 0;
    }
    
    /**
     * Gets the planet's name
     * @return the planet's name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gets the planet's coordinates
     * @return the planet's coordinates
     */
    public Coordinate getCoords() {
        return this.coords;
    }
    
    /**
     * Makes a string representation of the planet
     * @return a string representation of the planet
     */
    @Override
    public String toString() {
        String string = this.name + " at " + this.coords.getX() + "," + this.coords.getY();
        return string;
    }
}
