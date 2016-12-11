/*
 * The example robot that will accompany the lesson chapter:
 *     docs/20-learning-java
 */
package org.geeklet.machines.examples;

import org.geeklet.machines.support.Robot;

/**
 * The most simplest robot that still functions in our virtual world.
 * Create the coordinate variables, <code>x</code> and <code>y</code>,
 * as well as the <code>direction</code> (which is in degrees).
 * <p>
 * The behavior simply moves the robot along the X axis.
 */
public class MyBot extends Robot {
    /** The left-right coordinate for the Robot's placement on the field. */
    public int x;
    /** The up-down coordinate for the Robot's placement on the field. */
    public int y;
    /** The angle in degrees of the Robot's direction. */
    public int direction;

    /**
     * The typical constructor that takes the field coordinate
     * placement as well as the initial pointing direction.
     *
     * @param x   The desired placement coordinate on the field
     * @param y   Desired placement coordinate on the field
     * @param direction Initial requested direction for the robot,
     *            where <code>0</code> degrees points to the right.
     */
    public MyBot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /**
     * The <i>robotic world</i> will repeatedly call this function for
     * the robot to alter its behavior. In this case, we simply
     * increment the <code>x</code> variable.
     */
    @Override
    public void step() {
        x = x + 1; // This could be written either: x += 1 or x++
    }
}
