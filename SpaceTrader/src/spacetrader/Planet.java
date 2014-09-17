package spacetrader;

public class Planet {
    //make some int things for the techlvl, resource, and govt
    private String name;
    private int techLevel;
    private int specialResources;
    private int government;
    
    public Planet(){
        this.name = "Name";
        this.techLevel = 0;
        this.government = 0;
        this.specialResources = 0;
    }
    
    public Planet(String name, int tech, int resource, int govt){
        this.name = name;
        this.techLevel = tech;
        this.specialResources = resource;
        this.government = govt;
    }
    
}
