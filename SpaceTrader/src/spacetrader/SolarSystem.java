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
            //System.out.println(planets[i].toString());
        }
    }
    
    public void placeSolarSystem(int x, int y){
        //reserve coords for planets
        //*30 to make input from 1 to 10 be appropriately placed on canvas
        xCoord = x*30;
        yCoord = y*30;
    }
    
    @Override
    public String toString(){
        String string = "";
        for (int i = 0; i < (planets.length); i++){
            string += " " + planets[i].toString() + " ";
        }
        
        return string;
    }
    
    public Planet[] getPlanets() {
        return planets;
    }
    
    public String getName() {
        return name;
    }
    
    public int getX() {
        return xCoord;
    }
    
    public int getY() {
        return yCoord;
    }
}
