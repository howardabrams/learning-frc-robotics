/*
 * A robot that doesn't pay attention to any external sensors or
 * stimuli, but instead runs around in a circle. This demonstrates how
 * to abstract {@link #move} and {@link #turn} methods.
 *
 * Accompanies the lesson chapter: docs/25-more-java.md
 */
package org.geeklet.machines;

import org.geeklet.machines.support.Robot;

/**
 * This simplistic robot simply moves around in a circle.
 */
public class SimpleRobot extends Robot {
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
    public SimpleRobot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /**
     * The <i>robotic world</i> will repeatedly call this function for
     * the robot to alter its behavior. In this case, we turn and move
     * in a large circle.
     */
    @Override
    public void step() {
        turn(3);
        move(3);
    }

    /**
     * Reposition the robot based on its current direction and
     * position.  For this, we use a wee bit of trigonometry as this
     * is a bit faster than calculating the new <code>x</code> and
     * <code>y</code> with the Pythagorean theorem.
     *
     * @param amount
     *            The number of units (pixels) to move the robot
     */
    void move(int amount) {
        // We are keeping our {@link #direction} in degrees, but the
        // cosine and sine functions use a different measurement for
        // angles called radians. Luckily for us, there is a function
        // to do the conversion:
        double rads = Math.toRadians(direction);

        x += Math.cos(rads) * amount;
        y += Math.sin(rads) * amount;
    }

    /**
     * Rotates the robot some degrees left (if positive), or right (if
     * negative).
     *
     * @param degrees
     *            Amount to turn robot, from 0 to 360.
     * @return direction in degrees from 0 to 360
     */
    void turn(double degrees) {
        direction = (int) ((direction + degrees) % 360);

        if (direction < 0) {
            direction = 360 + direction;
        }
    }
}
