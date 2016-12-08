/*
 * Interface to specify that a robot can be displayed.
 */
package org.geeklet.machines.support;

import java.awt.Graphics;

/**
 * Extension to the {@link IRobot} interface that represents that the Robot classes that implement
 * this interface can draw themselves with a {@link #paint(Graphics)} method.
 */
public interface DrawableRobot extends IRobot {
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
     * Called when a robot should paint itself into a Graphics Context.
     * We assume that is X,Y coordinates and other field values are correct.
     * @param g Graphics Context that represents the 2D space inside a {@link Frame}.
     */
    void paint(Graphics g);
}
