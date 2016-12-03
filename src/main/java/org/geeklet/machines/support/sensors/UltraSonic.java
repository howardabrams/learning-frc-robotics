/*
 * Basic sensor that emulates a short-range ultrasonic electrical
 * sensor.
 */
package org.geeklet.machines.support.sensors;

import java.awt.Rectangle;

import org.geeklet.machines.support.Field;
import org.geeklet.machines.support.IRobot;

/**
 * A sensor that gets a list of field objects from the field, and
 * looks in a particular direction (relative to the robot's
 * direction), and its {@link #triggered} returns true if an object is
 * immediately in front.
 */
public class UltraSonic extends DirectionalSensor {
    /** Constant value of the number of pixels to scan in front for an
     * object. */
    int RANGE = 30;

    /** Reference to the field that the robot is attached to. */
    public Field playingField;

    /** Reference to the robot that this sensor is attached to. */
    IRobot attachedTo;

    /**
     * Constructor that takes the relative sensor direction.
     * @param direction of the sensor in relation to the Robot it is
     * attached to. 0 degrees points to the front of the robot.
     */
    public UltraSonic(int direction) {
        this.direction = direction;
    }

    /**
     * @return <code>true</code> if a field object is directly in
     * front of this sensor up to the {@link RANGE}.
     */
    @Override
    public boolean triggered() {
        int x = attachedTo.getX();
        int y = attachedTo.getY();
        int d = attachedTo.getDirection() + direction;
        double rads = Math.toRadians(d);

        for (int a = 0; a < RANGE; a++) {
            int vx = (int) (x + Math.cos(rads) * a);
            int vy = (int) (y + Math.sin(rads) * a);

            if (playingField.insideAnyObject(vx, vy))
                return true;
        }
        return false;
    }

    /**
     * Checks a <i>viewing coordinate</i> with a rectangle of a
     * particular object.
     * @param vx      <code>x</code> coordinate of a viewing coordinate
     * @param vy      <code>y</code> coordinate of a viewing coordinate
     * @param x       <code>x</code> coordinate of an object's rectangle
     * @param y       <code>y</code> coordinate of an object's rectangle
     * @param width   of an object
     * @param height  of an object
     * @return <code>true</code> if the viewing coordinate is within
     * the rectangle specified by the parameters.
     */
    boolean isIn(int vx, int vy, int x, int y, int width, int height) {
        final Rectangle o = new Rectangle(x, y, width, height);
        return o.contains(vx, vy);
    }

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
