package org.geeklet.machines;

/**
 * An class to hold an X,Y coordinate position on the field.
 */
public class Coordinate {
    public int x;
    public int y;
    
    /**
     * A constructor holding a position
     * 
     * @param x
     * @param y
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Take position and stores them as variables x and y
     * 
     * @param x
     * @param y
     */
    public Coordinate(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }
}