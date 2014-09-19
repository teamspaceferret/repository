package spacetrader;

public class SolarSystem {
    private String name;
    private int xCoord, yCoord;
    private Planet[] planets;
    //have a list of available coordinates??
    
    public SolarSystem(String name){
        this.name = name; 
    }
    
    public void populateSolarSystem(int numPlanets){
        planets = new Planet[numPlanets];
        for(int i = 0; i<numPlanets; i++){
            String name = this.name + " Planet " + i;
            int x = this.xCoord + i*10;
            int y = this.yCoord + i*10;
            Planet p = new Planet(name,0,0,0,x,y,0);
            
            planets[i] = p;
        }
    }
    
    public void placeSolarSystem(int x, int y){
        //reserve a bounding box around the x and y for planets
        //can make it so that each "solarsystem coord" is equiv to ~100 planet coords
        xCoord = x*100;
        yCoord = y*100;
    }
}
