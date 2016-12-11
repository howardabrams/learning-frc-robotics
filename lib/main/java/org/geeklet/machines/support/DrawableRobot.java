/*
 * Interface to specify that a robot can be displayed.
 */
package org.geeklet.machines.support;

import java.awt.Graphics;
import java.awt.geom.Area;

/**
 * Extension to the {@link IRobot} interface that represents that the
 * Robot classes that implement this interface can draw themselves
 * with a {@link #paint(Graphics)} method.
 */
public interface DrawableRobot extends IRobot {
    /**
     * @return direction in radians for easier transformations
     */
    double getAngle();

    /**
     * Places the robot on a field by adjusting its coordinates.
     * @param x  The X coordinate in pixels
     * @param y  The Y coordinate in pixels
     */
    void set(float x, float y);

    /**
     * Places the robot on a field by adjusting its coordinates (within
     * the Graphics Context's frame).
     * @param x  The X coordinate in pixels
     */
    void setX(float x);

    /**
     * Places the robot on a field by adjusting its coordinates (within
     * the Graphics Context's frame).
     * @param y  The Y coordinate in pixels
     */
    void setY(float y);

    /**
     * Called when a robot should paint itself into a Graphics
     * Context.  We assume that is X,Y coordinates and other field
     * values are correct.
     * @param g Graphics Context that represents the 2D space inside a
     * {@link Frame}.
     */
    void paint(Graphics g);

    /**
     * @return area the robot requires... used for collision purposes.
     */
    Area getArea();

    /**
     * Called when a robot has crashed into some object
     * @param x coordinate point on the robot that intersects with
     *          some field object
     * @param y coordinate point on the robot
     * @param width the width of the area of overlap with field object
     *          (this will be small)
     * @param height the height of the overlap area with field object
     */
    void crash(int x, int y, int width, int height);
}
