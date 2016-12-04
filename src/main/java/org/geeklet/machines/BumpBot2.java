/*
 * Accompanies the lesson chapter: docs/40-adding-sensors.md
 */
package org.geeklet.machines;

import org.geeklet.machines.support.DefaultRobot;
import org.geeklet.machines.support.Robot;
import org.geeklet.machines.support.sensors.UltraSonic;

/**
 * Robot that uses a sensor to locate objects in its field environment.
 * If it finds something in front of it, it gets scared and stops.
 */
public class BumpBot2 extends DefaultRobot {
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
    public BumpBot2(int x, int y, int direction) {
        super(x, y, direction);

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
            turnRight();
            forward();
        }
    }
}
