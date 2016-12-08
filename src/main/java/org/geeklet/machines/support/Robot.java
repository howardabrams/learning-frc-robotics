package org.geeklet.machines.support;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;
import java.util.List;

import org.geeklet.machines.support.sensors.ISensor;

/**
 * A robot that implements
 *
 */
public abstract class Robot implements DrawableRobot {
    public Color c = new Color(220, 0, 240);
    protected List<ISensor> sensors = new LinkedList<ISensor>();

    /**
     * Draws a generic, quite boring robot.
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

    public float getX() {
        return MagicSpells.getMagicFloat(this, "x");
    }

    public float getY() {
        return MagicSpells.getMagicFloat(this, "y");
    }

    public int getDirection() {
        return MagicSpells.getMagicInteger(this, "direction");
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
