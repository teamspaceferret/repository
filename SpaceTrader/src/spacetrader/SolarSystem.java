package spacetrader;

import java.util.Arrays;
import java.util.Random;

public class SolarSystem {
    private String name;
    private Coordinate coords;
    private Planet[] planets;
    
    /**
     * Constructs a solar system
     */
    public SolarSystem() {
        this.name = "";
        this.coords = new Coordinate();
    }
    
    /**
     * Constructs a solar system with the given name
     * @param name The name of the solar system
     */
    public SolarSystem(String name) {
        this();
        this.name = name;
    }
    
    /**
     * Generates a solar system with a random name, randomized coordinates, and a random number of planets.
     * Then randomizes each planet's location in the solar system.
     */
    public void generateSolarSystem() {
        Random rand = new Random();
        String randomName;
        
        do {
            randomName = Context.SOLAR_SYSTEM_NAMES[rand.nextInt(Context.SOLAR_SYSTEM_NAMES.length)];
        } while (Arrays.asList(Context.getInstance().getUniverse().getSolarSystemNames()).contains(randomName));
        
        this.name = randomName;
        this.coords = new Coordinate(rand.nextInt(300), rand.nextInt(300));
        
        int numPlanets = rand.nextInt(Context.MAX_PLANETS_PER_SOLAR_SYSTEM
                - Context.MIN_PLANETS_PER_SOLAR_SYSTEM)
                + Context.MIN_PLANETS_PER_SOLAR_SYSTEM;
        
        this.planets = new Planet[numPlanets];
        
        for (int i = 0; i < this.planets.length; i++) {
            int x = rand.nextInt(300);
            int y = rand.nextInt(300);
            this.planets[i] = new Planet(x, y);
        }
    }
    
    /**
     * Gets the name of the solar system
     * @return The name of the solar system
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gets the coordinates of the solar system
     * @return The coordinates of the solar system
     */
    public Coordinate getCoords() {
        return this.coords;
    }
    
    /**
     * Gets the planets in the solar system
     * @return The planet array
     */
    public Planet[] getPlanets() {
        return this.planets;
    }
    
    /**
     * Sets the coordinates of the solar system
     * @param x The x coordinate of the solar system
     * @param y The y coordinate of the solar system
     */
    public void setCoords(int x, int y) {
        this.coords.setCoords(x, y);
    }
    
    /**
     * Creates a string representation of the solar system
     * @return string representation of the solar system
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
