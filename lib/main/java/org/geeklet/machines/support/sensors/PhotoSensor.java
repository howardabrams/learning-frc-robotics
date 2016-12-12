/*
 * Locate particular shapes at distance
 */
package org.geeklet.machines.support.sensors;

import org.geeklet.machines.support.objects.FieldObject;

/**
 * A PhotoSensor is an class that allows a Robot to detect a
 * particular shape (or class) and return a value as to <i>where</i>
 * the object is (left or right, up or down).
 */
public abstract class PhotoSensor extends DirectionalSensor {
    /**
     * Returned by {@link #locate()} to indicate the results of a
     * <i>tracking</i> search of a particular {@link FieldObject}:
     * <ul>
     * <li> <code>IN_FRONT</code> in front with a slight margin of error
     * <li> <code>TO_LEFT</code>
     * <li> <code>TO_RIGHT</code>
     * <li> <code>NOT_FOUND</code> if no object was found
     * </ul>
     */
    public enum Location {
        IN_FRONT(0), TO_LEFT(-1), TO_RIGHT(1), NOT_FOUND(-2);

        private int value;

        Location(int value) {
            this.value = value;
        }

        public int get() {
            return value;
        }
    }

    /**
     * Number of pixels in front of the attached sensor to scan.
     */
    final static int RANGE = 600;

    /**
     * The entire field of view to scan where <code>ANGLE_OF_VIEW /
     * 2</code> on either side of the {@link Robot}'s direction and
     * angle of attachment to the robot.
     */
    final static int ANGLE_OF_VIEW = 45;

    /**
     * Returned by {@link #triggered()} if a search tracked an object.
     */
    boolean found = false;

    /**
     * @param direction The <i>attachment</i> of this sensor to the
     *                  {@link Robot}, where 0 0 degrees means it was
     *                  attached to the front of the Robot.
     */
    public PhotoSensor(int direction) {
        super(direction);
    }

    /**
     * @return <code>true</code> if a call to {@link #locate()}
     * indicates a tracked object in our field of view.
     */
    public boolean triggered() {
        return found;
    }

    /**
     * Each subclass should implement this as a call to {@link
     * #conicSearch(Class)} for a particular class the sensor will
     * track.
     * @return A value based on the location of a found object:
     * <ul>
     * <li> <code>IN_FRONT</code> in front with a slight margin of error
     * <li> <code>TO_LEFT</code>
     * <li> <code>TO_RIGHT</code>
     * <li> <code>NOT_FOUND</code> if no object was found
     * </ul>
     */
    public abstract Location locate();

    /**
     * Creates a field of view extending from the robot's direction
     * (and this sensor's attachment direction), in a wide triangular
     * arc of {@link #RANGE} by {@link #ANGLE_OF_VIEW}.
     * @param o Search the field for a particular object
     * @return an enumerated type based on if the <i>tracked</i>
     * object is to the left, right or in front of the robot.  As a
     * side effect, the {@link #triggered()} method will return
     * <i>true</i> if this call succeeded in finding the correct
     * object type.
     */
    protected Location conicSearch(Class<? extends FieldObject> o) {
        final int angle = attachedTo.getDirection() + direction;

        found = true;
        if (searchFieldofView(o, angle - 2, angle + 2, RANGE))
            return Location.IN_FRONT;
        if (searchFieldofView(o, angle, angle + ANGLE_OF_VIEW/2, RANGE))
            return Location.TO_RIGHT;
        if (searchFieldofView(o, angle - ANGLE_OF_VIEW/2, angle, RANGE))
            return Location.TO_LEFT;

        found = false;
        return Location.NOT_FOUND;
    }
}
