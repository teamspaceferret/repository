package spacetrader;

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
        Planet dummyPlanet = new Planet();
        Planet dummyOtherPlanet = new Planet();
        
        planetCoords = new Coordinate[this.planets.length]; 
        this.name = Context.getInstance().getNames().getRandomName();
        
        int numPlanets = rand.nextInt(Context.MAX_PLANETS_PER_SOLAR_SYSTEM
                - Context.MIN_PLANETS_PER_SOLAR_SYSTEM)
                + Context.MIN_PLANETS_PER_SOLAR_SYSTEM;
        
        this.planets = new Planet[numPlanets];
        
        for (int i = 0; i < this.planets.length; i++) {
            do {
                tooClose = false;
                newCoord = new Coordinate(rand.nextInt(Context.BOUNDARY),
                        rand.nextInt(Context.BOUNDARY));
                dummyPlanet.setCoords(newCoord);

                if (i > 0) {
                    for (int j = 0; j < i; j++) {
                        dummyOtherPlanet.setCoords(planetCoords[j]);
                        if (dummyPlanet.istooCloseTo(dummyOtherPlanet)) {
                            tooClose = true;
                        }
                    }
                }
            } while (tooClose);
            
            planetCoords[i] = newCoord;
        }
        
        // Create all the planets
        for (int i = 0; i < planetCoords.length; i++) {
            this.planets[i] = new Planet(Context.getInstance().getNames().getRandomName(),
                    rand.nextInt(Context.TECH_LEVELS.length),
                    Resource.NOSPECIALRESOURCES.randomResource(),
                    rand.nextInt(Context.GOVERNMENTS.length),
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
