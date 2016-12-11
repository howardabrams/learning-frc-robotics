/*
 * Accompanies the lesson chapter: docs/40-adding-sensors.md
 */
package org.geeklet.machines.examples;

import org.geeklet.machines.support.Robot;
import org.geeklet.machines.support.sensors.UltraSonic;

/**
 * Robot that uses a sensor to locate objects in its field environment.
 * If it finds something in front of it, it gets scared and stops.
 */
public class BumpBot extends Robot {
    /** The left-right coordinate for the Robot's placement on the field. */
    public int x;
    /** The up-down coordinate for the Robot's placement on the field. */
    public int y;
    /** The angle in degrees of the Robot's direction. */
    public int direction;

    /** Our {@link UltraSonic} sensor that detects objects at close range. */
    UltraSonic sonic;
    
    /**
     * The typical constructor that takes the field coordinate
     * placement as well as the initial pointing direction.
     * Also adds an {@link UltraSonic} sensor to the front.
     *
     * @param x   The desired placement coordinate on the field
     * @param y   Desired placement coordinate on the field
     * @param direction Initial requested direction for the robot,
     *            where <code>0</code> degrees points to the right.
     */
    public BumpBot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;

        sonic = new UltraSonic(0);
        addSensor(sonic);
    }

    /**
     * The <i>robotic world</i> will repeatedly call this function for
     * the robot to alter its behavior. In this case, we simply
     * increment the <code>x</code> variable.
     */
    @Override
    public void step() {
        if (!sonic.triggered()) {
            turn(1);
            move(3);
        }
    }

    /**
     * Reposition the robot based on its current direction and position.
     *
     * @param amount
     *            The number of units (pixels) to move the robot
     */
    void move(int amount) {
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
