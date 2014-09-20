package spacetrader;

import java.util.Random;

public class SolarSystem {
    private String name;
    
    private Coordinate coords;
    private Planet[] planets;
    
    public SolarSystem() {
        this.coords = new Coordinate();
    }
    
    public SolarSystem(String name) {
        super();
        this.name = name;
    }
    
    public void generateSolarSystem() {
        Random rand = new Random();
        
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
    
    public String getName() {
        return this.name;
    }
    
    public Coordinate getCoords() {
        return this.coords;
    }
    
    public Planet[] getPlanets() {
        return this.planets;
    }
    
    public void setCoords(int x, int y) {
        this.coords.setCoords(x, y);
    }
    
    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < this.planets.length; i++) {
            string += " " + this.planets[i].toString() + " ";
        }
        
        return string;
    }
}
