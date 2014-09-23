package spacetrader;

public class Coordinate {
    
    private int x, y;
    
/**
* Initializing global variables. 
**/
public Coordinate() {
    this.x = 0;
    this.y = 0;
}

/**
* Setting global variables to the ones in the coordinate. 
*@param x - the x coordinate
*@param y - the y coordinate 
**/

public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
}

/**
* The getter method for the x coordinate
**/

public int getX() {
    return x;
}


/**
* The getter method for the y coordinate
**/
public int getY() {
    return y;
}

/**
* The setter method for the x and y coordinates
**/
public void setCoords(int x, int y) {
    this.x = x;
    this.y = y;
}
}
