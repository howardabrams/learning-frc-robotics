/*
 * Implementation of the {@link IRobot} and {@link DrawableRobot}
 * interfaces.
 */
package org.geeklet.machines.support;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;
import java.util.List;

import org.geeklet.machines.support.sensors.ISensor;

/**
 * A robot that implements the robot interfaces. All robots should
 * extend this class, as it allows a robot to be drawn on a playing
 * field.
 */
public abstract class Robot implements DrawableRobot {
    public Color c = new Color(220, 0, 240);
    protected List<ISensor> sensors = new LinkedList<ISensor>();

    /**
     * Draws a generic, quite boring robot.
     * @param g A graphics context that comes from a {@link Field}'s frame
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.translate(getX(), getY());
        g2d.rotate(Math.toRadians(getDirection()));

        int width = 20;
        int height = 15;

        // Draw the 4 wheels:
        g2d.setColor(Color.BLACK);
        g2d.fillOval(-width/2, -height/2 - 3, 8, 4);
        g2d.fillOval(-width/2, +height/2, 8, 4);
        g2d.fillOval( width/2, -height/2 - 3, 8, 4);
        g2d.fillOval( width/2, +height/2, 8, 4);

        g2d.setColor(c);
        g2d.fillRect(-width/2, -height/2,
                     width + 6, height);
    }

    /**
     * @return the X coordinate of the Robot (in pixels) based on the
     * Robot's fieldname, <code>x</code>
     */
    public float getX() {
        return MagicSpells.getMagicFloat(this, "x");
    }

    /**
     * @return the direction of the Robot (in pixels) based on the
     * Robot's fieldname, <code>y</code>
     */
    public float getY() {
        return MagicSpells.getMagicFloat(this, "y");
    }

    /**
     * @return the direction of the Robot (in degrees) based on the
     * Robot's fieldname, <code>direction</code>
     */
    public int getDirection() {
        return MagicSpells.getMagicInteger(this, "direction");
    }

    /**
     * Set a robot's X coordinate.
     * @param x numeric value, either an <code>int</code> or <code>float</code>
     */
    public void setX(float x) {
        MagicSpells.setMagicNumber(this, "x", x);
    }

    /**
     * Set a robot's Y coordinate.
     * @param y numeric value, either an <code>int</code> or <code>float</code>
     */
    public void setY(float y) {
        MagicSpells.setMagicNumber(this, "y", y);
    }

    /**
     * Set a robot's X and Y coordinates.
     * @param x numeric value, either an <code>int</code> or <code>float</code>
     * @param y numeric value
     */
    public void set(float x, float y) {
        MagicSpells.setMagicNumber(this, "x", x);
        MagicSpells.setMagicNumber(this, "y", y);
    }

    /**
     * @return List of all sensors added to this robot
     */
    public List<ISensor> getSensors() {
        return sensors;
    }

    /**
     * Add the given sensor to the list of sensors the robot
     * has attached.
     * @param sensor A fully configured sensor
     */
    public void addSensor(ISensor sensor) {
        sensors.add(sensor);
    }
}
