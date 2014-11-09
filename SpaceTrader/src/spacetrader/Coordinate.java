package spacetrader;

import java.io.Serializable;

public class Coordinate implements Serializable {
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
    public Coordinate(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Returns the distance between the coordinate and another coordinate.
     * @param otherCoord another coordinate
     * @return the distance between the coordinate and another coordinate
     */
    public final double distanceTo(final Coordinate otherCoord) {
        return Math.sqrt(Math.pow(Math.abs(x - otherCoord.getX()), 2)
                + Math.pow(Math.abs(y - otherCoord.getY()), 2));
    }
    
    /**
     * Returns the x coordinate.
     * @return the x coordinate
     */
    public final int getX() {
        return x;
    }
    
    /**
     * Returns the y coordinate.
     * @return the y coordinate
     */
    public final int getY() {
        return y;
    }
    
    /**
     * Sets the x and y coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public final void setCoords(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Sets the x coordinate.
     * @param x the x coordinate
     */
    public final void setX(final int x) {
        this.x = x;
    }
    
    /**
     * Sets the y coordinate.
     * @param y the y coordinate
     */
    public final void setY(final int y) {
        this.y = y;
    }
    
    @Override
    public final String toString() {
        return "(" + String.valueOf(this.x) + ", "
                + String.valueOf(this.y) + ")";
    }
}
