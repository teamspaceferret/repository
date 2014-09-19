package spacetrader;

public class Planet {
    //make some int things for the techlvl, resource, and govt
    private String name;
    private int techLevel;
    private int specialResources;
    private int government;
    private int xCoord, yCoord;
    private double chancePolice, chancePirate, chanceTrader;
    private int specialEvent; //make not-an-int?? Probably
    
    
    public Planet(){
        this.name = "Name";
        this.techLevel = 0;
        this.government = 0;
        this.specialResources = 0;
    }
    
    public Planet(String name, int tech, int resource, int govt, int x, int y, int specialEvent){
        this.name = name;
        this.techLevel = tech;
        this.specialResources = resource;
        this.government = govt;
        this.xCoord = x;
        this.yCoord = y;
        this.specialEvent = specialEvent;
        this.chancePolice = 0;
        this.chancePirate = 0;
        this.chanceTrader = 0;
    }
    
}
