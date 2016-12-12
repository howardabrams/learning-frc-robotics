/*
 * An implementation of a Ball-tracking sensor
 */
package org.geeklet.machines.support.sensors;

import org.geeklet.machines.support.objects.Ball;

/**
 * A specific type of <i>visual tracker</i> sensor that represents a
 * component, like a Raspberry Pi with a camera, that is programmed to
 * recognize and <i>track</i> round shapes like a {@link Ball}.
 * <p>
 * To use, call the {@link #locate()} method and either analyze its
 * return code, or call the {@link #triggered()} method, as it returns
 * <code>true</code> if the {@link #locate()} method found a Ball.
 */
public class BallTracker extends PhotoSensor {
    /**
     * @param direction The <i>attachment</i> of this sensor to the {@link Robot}, where 0
     *                  0 degrees means it was attached to the front of the Robot.
     */
    public BallTracker(int direction) {
        super(direction);
    }

    /**
     * Scans the field of view extending from the robot's direction
     * (and this sensor's attachment direction), in a wide triangular
     * arc.
     * @return a value that indicates <i>where</i> in the field of
     * view, the tracked object was located:
     * <ul>
     * <li> <code>IN_FRONT</code> in front with a slight margin of error
     * <li> <code>TO_LEFT</code>
     * <li> <code>TO_RIGHT</code>
     * <li> <code>NOT_FOUND</code> if no object was found
     * </ul>
     * @see org.geeklet.machines.support.sensors.PhotoSensor#location()
     */
    @Override
    public Location locate() {
        return conicSearch(Ball.class);
    }
}
