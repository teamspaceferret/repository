package spacetrader;

import java.util.Random;

public class Universe {
    private SolarSystem[] solarSystems;
    
    public Universe() {
        this.solarSystems = null;
    }
    
    public void generateUniverse() {
        Random rand = new Random();
        
        this.solarSystems = new SolarSystem[Context.NUM_SOLAR_SYSTEMS];
        
        for (int i = 0; i < solarSystems.length; i++) {
            this.solarSystems[i] = new SolarSystem();
            this.solarSystems[i].generateSolarSystem();
        }
    }
    
    public SolarSystem[] getSolarSystems() {
        return this.solarSystems;
    }
    
    public String[] getSolarSystemNames() {
        String[] names = new String[solarSystems.length];
        
        for (int i = 0; i < names.length; i++) {
            try {
                names[i] = solarSystems[i].getName();
            } catch (NullPointerException e) {
                names[i] = "";
            }
        }
        
        return names;
    }
    
    @Override
    public String toString(){
       String string = "";
       for(int i = 0; i < this.solarSystems.length; i++){
            string += "Solar System " + i + " \"" + this.solarSystems[i].getName() + "\": \n";
            string += this.solarSystems[i].toString() + "\n";
        }
       
        return string;
    }
}
