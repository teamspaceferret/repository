package spacetrader;

public class Universe {
    private SolarSystem[] solarSystems;
    
    public Universe(){
        this.solarSystems = null;
    }
    
    public Universe(int numSystems){
        //make a universe with given number of systems
    }
    
    public Universe populateUniverse(){
        Universe universe = new Universe();
        SolarSystem a = new SolarSystem("Alpha");
        
        return universe;
        
    } 
}
