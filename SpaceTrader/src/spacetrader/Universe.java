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
        s1.placeSolarSystem(10, 5);
        s1.populateSolarSystem(1);
        
        SolarSystem s2 = new SolarSystem("Vectron");
        s2.placeSolarSystem(1,10);
        s2.populateSolarSystem(2);
        
        SolarSystem s3 = new SolarSystem("Bob");
        s3.placeSolarSystem(9, 2);
        s3.populateSolarSystem(3);
        
        
        solarSystems[0] = s1;
        solarSystems[1] = s2;
        solarSystems[2] = s3;
        
    }
    
    public Universe populateUniverse(){
        Universe universe = new Universe();
        SolarSystem a = new SolarSystem("Alpha");
        
        return universe;
        
    }
    
    @Override
    public String toString(){
       String string = "";
       for(int i = 0; i < (solarSystems.length); i++){
            string += "Solar System " + i + " " + solarSystems[i].toString() + "\n";
        }
        return string;
    }
    
    public SolarSystem[] getSolarSystems() {
        return solarSystems;
    }
}
