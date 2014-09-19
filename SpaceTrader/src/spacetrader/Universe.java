package spacetrader;

public class Universe {
     private SolarSystem[] solarSystems;
    
    public Universe(){
        this.solarSystems = null;
        //makeUniverse();
    }
    
    //put solarsystems in the universe
    //static number is 3
    public void makeUniverse(){
        solarSystems = new SolarSystem[3];
        
        SolarSystem s1 = new SolarSystem("N00b Zone");
        s1.placeSolarSystem(4, 5);
        s1.populateSolarSystem(1);
        
        SolarSystem s2 = new SolarSystem("Vectron");
        s2.placeSolarSystem(3,8);
        s2.populateSolarSystem(2);
        
        SolarSystem s3 = new SolarSystem("Matryoshka");
        s3.placeSolarSystem(8, 2);
        s3.populateSolarSystem(3);
    }
    
    public Universe populateUniverse(){
        Universe universe = new Universe();
        SolarSystem a = new SolarSystem("Alpha");
        
        return universe;
        
    } 
}
