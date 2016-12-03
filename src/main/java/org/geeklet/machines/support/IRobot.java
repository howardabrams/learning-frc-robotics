package org.geeklet.machines.support;

import java.util.List;
import org.geeklet.machines.support.sensors.ISensor;

/**
 * The behavior (or interface) that all robots who want to play on the
 * field must adhere.
 */
public interface IRobot {
    int getX();
    int getY();
    int getDirection();

    void step();

    List<ISensor> getSensors();
}
