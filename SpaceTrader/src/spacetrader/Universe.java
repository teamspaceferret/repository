package spacetrader;

import java.util.Random;

public class Universe {
    private SolarSystem[] solarSystems;
    
    public Universe() {
        this.solarSystems = null;
    }
    
    public void generateUniverse() {
        Random rand = new Random();
        
        solarSystems = new SolarSystem[Context.NUM_SOLAR_SYSTEMS];
        
        for (int i = 0; i < solarSystems.length; i++) {
            solarSystems[i] = new SolarSystem("SOLAR SYSTEM NAME");
            solarSystems[i].generateSolarSystem();
        }
    }
    
    public SolarSystem[] getSolarSystems() {
        return solarSystems;
    }
        
    @Override
    public String toString(){
       String string = "";
       for(int i = 0; i < (solarSystems.length); i++){
            string += "Solar System " + i + " " + solarSystems[i].toString() + "\n";
        }
        return string;
    }
}
