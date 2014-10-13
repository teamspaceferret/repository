package spacetrader;

public class Coordinate {
    
    private int x, y;

    /**
     * Constructs a coordinate with default values.
     */
    public Coordinate() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructs a coordinate with x and y values. 
     * @param x the x coordinate
     * @param y the y coordinate 
     */

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the distance between the coordinate and another coordinate.
     * @param otherCoord another coordinate
     * @return the distance between the coordinate and another coordinate
     */
    public double distanceTo(Coordinate otherCoord) {
        return Math.sqrt(Math.pow(Math.abs(x-otherCoord.getX()), 2)
                + Math.pow(Math.abs(y-otherCoord.getY()), 2));
    }
    
    /**
     * Returns the x coordinate.
     * @return the x coordinate
     */

    public int getX() {
        return x;
    }


    /**
     * Returns the y coordinate.
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x and y coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Sets the x coordinate.
     * @param x the x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Sets the y coordinate
     * @param y the y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "(" + String.valueOf(this.x) + ", "
                + String.valueOf(this.y)+ ")";
    }
}
