/*
 * A category of sensors that keep track of <i>how</i>
 * they are attached to a robot.
 */
package org.geeklet.machines.support.sensors;

import java.awt.geom.Area;
import java.awt.geom.Path2D;

import org.geeklet.machines.support.objects.FieldObject;

/**
 * These types of sensors carrying their own direction
 * of how they are attached to a robot.
 */
public abstract class DirectionalSensor extends Sensor {
    /** The attachment direction (whatever that means for a given sensor). */
    int direction;

    /**
     * Constructor stores the direction of attachment
     * @param direction Degrees where 0 is in front of the robot
     */
    public DirectionalSensor(int direction) {
        this.direction = direction;
    }

    protected Area arealFieldofView(int angleA, int angleB, int range) {
        final Path2D path = new Path2D.Double();

        final float x = attachedTo.getX();
        final float y = attachedTo.getY();
        path.moveTo(x, y);

        final double radsA = Math.toRadians(angleA);
        final double ax = x + Math.cos(radsA) * range;
        final double ay = y + Math.sin(radsA) * range;
        path.lineTo(ax, ay);

        final double radsB = Math.toRadians(angleB);
        final double bx = x + Math.cos(radsB) * range;
        final double by = y + Math.sin(radsB) * range;
        path.lineTo(bx, by);

        path.closePath();
        return new Area(path);
    }

    protected boolean searchFieldofView(Class<? extends FieldObject> o, int a1, int a2, int range) {
        final Area view = arealFieldofView(a1, a2, range);
        return playingField.isAnyObjectinArea(o, view);
    }
}
