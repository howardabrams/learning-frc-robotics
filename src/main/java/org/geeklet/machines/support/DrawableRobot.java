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
     * Called when a robot should paint itself into a Graphics Context.
     * We assume that is X,Y coordinates and other field values are correct.
     * @param g Graphics Context that represents the 2D space inside a {@link Frame}.
     */
    void paint(Graphics g);
}
