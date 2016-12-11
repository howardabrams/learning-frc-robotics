package org.geeklet.machines.support;

import java.util.List;
import org.geeklet.machines.support.sensors.ISensor;

/**
 * The behavior (or interface) that all robots who want to play on the
 * field must adhere.
 */
public interface IRobot {
    /**
     * @return The robot's X coordinate on the playing field (within
     * the Graphics Context's frame).
     */
    float getX();

    /**
     * @return The robot's Y coordinate on the playing field (within
     * the Graphics Context's frame).
     */
    float getY();

    /**
     * @return The robot's direction (in degrees).
     */
    int getDirection();

    /**
     * Repeatedly called during a game for the robot to perform a step.
     */
    void step();

    /**
     * @return a (potentially empty) list of all sensors attached to
     * the robot, may also be <code>null</code>.
     */
    List<ISensor> getSensors();
}
