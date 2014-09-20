package spacetrader;

public class Coordinate {
    private int x, y;
    
    public Coordinate() {
        this.x = 0;
        this.y = 0;
    }
    
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
