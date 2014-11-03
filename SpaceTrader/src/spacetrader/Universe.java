package spacetrader;

import java.util.Random;
import java.io.Serializable;

public class Universe implements Serializable{
    private SolarSystem[] solarSystems;
    
    /**
     * Constructs a default universe.
     */
    public Universe() {
        this.solarSystems = null;
    }
    
    /**
     * Generates a universe with random solar systems
     */
    public void generateUniverse() {
        boolean tooClose;
        Coordinate newCoord;
        Coordinate solarSystemCoords[];
        Random rand = new Random();
        
        this.solarSystems = new SolarSystem[Context.NUM_SOLAR_SYSTEMS];
        solarSystemCoords = new Coordinate[this.solarSystems.length];
        
        for (int i = 0; i < this.solarSystems.length; i++) {
            do {
                tooClose = false;
                newCoord = new Coordinate(rand.nextInt(Context.BOUNDARY),
                        rand.nextInt(Context.BOUNDARY));

                if (i > 0) {
                    for (int j = 0; j < i; j++) {
                        if ((Math.abs(newCoord.getX()
                                - solarSystemCoords[j].getX())
                                < Context.MIN_DISTANCE_BETWEEN_SOLAR_SYSTEMS)
                                || (Math.abs(newCoord.getY()
                                        - solarSystemCoords[j].getY())
                                < Context.MIN_DISTANCE_BETWEEN_SOLAR_SYSTEMS)) {
                            tooClose = true;
                        }
                    }
                }
            } while (tooClose);
            
            solarSystemCoords[i] = newCoord;
        }
        
        // Create all the solar systems
        for (int i = 0; i < solarSystemCoords.length; i++) {
            this.solarSystems[i] = new SolarSystem(solarSystemCoords[i].getX(),
                    solarSystemCoords[i].getY());
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
            string += "Solar System " + i + " \""
                    + this.solarSystems[i].getName() + "\" at "
                    + this.solarSystems[i].getCoords() + ": \n";
            string += this.solarSystems[i].toString() + "\n";
        }
       
        return string;
    }
}
