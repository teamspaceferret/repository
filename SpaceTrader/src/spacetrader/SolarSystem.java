package spacetrader;

import java.util.Arrays;
import java.util.Random;

public class SolarSystem {
    private String name;
    private Coordinate coords;
    private Planet[] planets;
    
    /**
     * Constructs a solar system.
     */
    public SolarSystem() {
        this.name = "";
        this.coords = new Coordinate();
    }
    
    /**
     * Constructs a solar system with the given name.
     * @param name name of the solar system
     */
    public SolarSystem(String name) {
        this();
        this.name = name;
    }
    
    public SolarSystem(int x, int y) {
        this();
        this.coords = new Coordinate(x, y);
    }
    
    /**
     * Generates a solar system with a random name, randomized coordinates, and a random number of planets.
     * Then randomizes each planet in the solar system.
     */
    public void generateSolarSystem() {
        Random rand = new Random();
        String randomName;
        
        // Selects random names until it finds an unused name
        do {
            randomName = Context.SOLAR_SYSTEM_NAMES[rand.nextInt(Context.SOLAR_SYSTEM_NAMES.length)];
        } while (Arrays.asList(Context.getInstance().getUniverse().getSolarSystemNames()).contains(randomName));
        
        this.name = randomName;
        
        int numPlanets = rand.nextInt(Context.MAX_PLANETS_PER_SOLAR_SYSTEM
                - Context.MIN_PLANETS_PER_SOLAR_SYSTEM)
                + Context.MIN_PLANETS_PER_SOLAR_SYSTEM;
        
        this.planets = new Planet[numPlanets];
        
        // Generate random coordiantes that aren't too close
        Coordinate planetCoords[] = new Coordinate[this.planets.length]; 
        for (int i = 0; i < this.planets.length; i++) {
            boolean tooClose;
            Coordinate newCoord;
            do {
                tooClose = false;
                newCoord = new Coordinate(rand.nextInt(300),
                        rand.nextInt(300));

                if (i > 0) {
                    for (int j = 0; j < i; j++) {
                        if (newCoord.istooCloseTo(planetCoords[j])) {
                            tooClose = true;
                        }
                    }
                }
            } while (tooClose);
            
            planetCoords[i] = newCoord;
        }
        
        // Create all the planets
        for (int i = 0; i < planetCoords.length; i++) {
            this.planets[i] = new Planet(planetCoords[i].getX(), planetCoords[i].getY());
        }
    }
    
    /**
     * Returns the name of the solar system
     * @return the name of the solar system
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the coordinates of the solar system
     * @return the coordinates of the solar system
     */
    public Coordinate getCoords() {
        return this.coords;
    }
    
    /**
     * Returns an array of planets in the solar system
     * @return an array of planets in the solar system
     */
    public Planet[] getPlanets() {
        return this.planets;
    }
    
    /**
     * Sets the coordinates of the solar system
     * @param x the x coordinate of the solar system
     * @param y the y coordinate of the solar system
     */
    public void setCoords(int x, int y) {
        this.coords.setCoords(x, y);
    }
    
    /**
     * Returns a string representation of the solar system
     * @return a string representation of the solar system
     */
    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < this.planets.length; i++) {
            string += "    " + this.planets[i].toString() + "\n";
        }
        
        return string;
    }
}
