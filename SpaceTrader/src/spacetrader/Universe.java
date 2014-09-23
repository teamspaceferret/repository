package spacetrader;

import java.util.Random;

public class Universe {
    private SolarSystem[] solarSystems;
    
    /**
     * Constructs a default universe
     */
    public Universe() {
        this.solarSystems = null;
    }
    
    /**
     * Constructs a randomly generated universe
     */
    public void generateUniverse() {
        Random rand = new Random();
        
        this.solarSystems = new SolarSystem[Context.NUM_SOLAR_SYSTEMS];
        
        for (int i = 0; i < solarSystems.length; i++) {
            this.solarSystems[i] = new SolarSystem();
            this.solarSystems[i].generateSolarSystem();
        }
    }
    
    /**
     * Gets the universe's solar systems
     * @return the array of all solar systems in the universe
     */
    public SolarSystem[] getSolarSystems() {
        return this.solarSystems;
    }
    
    /**
     * Gets the universe's solar system's names
     * @return an array of all solar system names in the universe
     */
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
    
    /**
     * Makes a string representation of the universe
     * @return a string representation of the universe
     */
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
