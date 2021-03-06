package spacetrader;

import java.util.Random;
import java.io.Serializable;

public class SolarSystem implements Serializable{
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
     * Constructs a solar system with the given x and y coordinates.
     * @param x the given x coordinate
     * @param y the given y coordinate
     */
    public SolarSystem(int x, int y) {
        this();
        this.coords = new Coordinate(x, y);
    }
    
    /**
     * Generates a solar system with a random name, randomized coordinates, and a random number of planets.
     * Then randomizes each planet in the solar system.
     */
    public void generateSolarSystem() {
        boolean tooClose;
        Coordinate newCoord;
        Coordinate planetCoords[];
        Random rand = new Random();
        
        this.name = Context.getInstance().getNames().getRandomName();
        
        int numPlanets = rand.nextInt(Context.MAX_PLANETS_PER_SOLAR_SYSTEM
                - Context.MIN_PLANETS_PER_SOLAR_SYSTEM)
                + Context.MIN_PLANETS_PER_SOLAR_SYSTEM;
        
        this.planets = new Planet[numPlanets];
        planetCoords = new Coordinate[this.planets.length]; 
        
        for (int i = 0; i < this.planets.length; i++) {
            do {
                tooClose = false;
                newCoord = new Coordinate(this.coords.getX() 
                        + rand.nextInt(Context.PLANET_BOUNDARY)
                        - Context.PLANET_BOUNDARY/2,
                        this.getCoords().getY()
                                + rand.nextInt(Context.PLANET_BOUNDARY) 
                                - Context.PLANET_BOUNDARY/2);

                if (i > 0) {
                    for (int j = 0; j < i; j++) {
                        if ((Math.abs(newCoord.getX() - planetCoords[j].getX())
                                < Context.MIN_DISTANCE_BETWEEN_PLANETS)
                                || (Math.abs(newCoord.getY() - planetCoords[j]
                                        .getY())
                                < Context.MIN_DISTANCE_BETWEEN_PLANETS)) {
                            tooClose = true;
                        }
                    }
                }
            } while (tooClose);
            
            planetCoords[i] = newCoord;
        }
        
        // Create all the planets
        for (int i = 0; i < planetCoords.length; i++) {
            int techLevel = rand.nextInt(Context.TECH_LEVELS.length);
            this.planets[i] = new Planet(Context.getInstance().getNames()
                    .getRandomName(),
                    techLevel,
                    Resource.NOSPECIALRESOURCES.randomResource(),
                    Government.ANARCHY.randomGovernment(techLevel),
                    planetCoords[i].getX(), planetCoords[i].getY(), this);
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
        return "Solar System \"" + this.name + "\" at " + this.coords;
    }
}
