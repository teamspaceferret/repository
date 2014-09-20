package spacetrader;

public class Planet {
    //make some int things for the techlvl, resource, and govt
    private String name;
    private Coordinate coords;
    private int techLevel;
    private int specialResources;
    private int government;
    private double chancePolice, chancePirate, chanceTrader;
    private int specialEvent; //make not-an-int?? Probably
        
    public Planet() {
        this.name = "Name";
        this.coords = new Coordinate();
        this.techLevel = 0;
        this.government = 0;
        this.specialResources = 0;
    }
    
    public Planet(int x, int y) {
        this();
        this.coords = new Coordinate(x, y);
    }
    
    public Planet(String name, int tech, int resource, int govt, int x, int y, int specialEvent) {
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
    public String getName() {
        return this.name;
    }
    
    public Coordinate getCoords() {
        return this.coords;
    }
    
    @Override
    public String toString() {
        String string = this.name + " at " + this.coords.getX() + "," + this.coords.getY();
        return string;
    }
}
