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
     * Checks if a coordinate is too close to another coordinate.
     * @param otherCoord the coordinate pair to check against
     * @return true if the coordinate pairs are too close
     */
    public boolean istooCloseTo(Coordinate otherCoord) {
        return Math.abs(this.x - otherCoord.getX()) < Context.MIN_DISTANCE_BETWEEN_PLANETS
                && Math.abs(this.y - otherCoord.getY()) < Context.MIN_DISTANCE_BETWEEN_PLANETS;
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
