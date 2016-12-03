/**
 * 
 */
package org.geeklet.machines.support.sensors;

import org.geeklet.machines.support.Field;
import org.geeklet.machines.support.IRobot;

/**
 * @author howard.abrams
 *
 */
public abstract class Sensor implements ISensor {
    /** Reference to the field that the robot is attached to. */
    public Field playingField;

    /** Reference to the robot that this sensor is attached to. */
    public IRobot attachedTo;

    /**
     * Stores a reference to the field the sensor will be viewing.
     */
    @Override
    public void addField(Field field) {
        playingField = field;
    }

    /**
     * Stores a reference to the robot that this sensor is attached.
     */
    @Override
    public void addRobot(IRobot robot) {
        attachedTo = robot;
    }
}
