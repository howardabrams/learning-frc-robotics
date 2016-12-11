/*
 * Basic sensor that emulates a short-range ultrasonic electrical
 * sensor.
 */
package org.geeklet.machines.support.sensors;

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

    /**
     * Constructor that takes the relative sensor direction.
     * @param direction of the sensor in relation to the Robot it is
     * attached to. 0 degrees points to the front of the robot.
     */
    public UltraSonic(int direction) {
        super(direction);
    }

    /**
     * @return <code>true</code> if a field object is directly in
     * front of this sensor up to the {@link RANGE}.
     */
    @Override
    public boolean triggered() {
        float x = attachedTo.getX();
        float y = attachedTo.getY();
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
}
