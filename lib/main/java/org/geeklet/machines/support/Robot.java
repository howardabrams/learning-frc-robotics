/*
 * Implementation of the {@link IRobot} and {@link DrawableRobot}
 * interfaces.
 */
package org.geeklet.machines.support;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
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

    Area area = null;
    Graphics cachedGraphics = null;

    /**
     * Draws a generic, quite boring robot.
     * @param g A graphics context that comes from a {@link Field}'s frame
     */
    public void paint(Graphics g) {
        final int width = 20;
        final int height = 15;

        cachedGraphics = g;
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // The definition of the entire area of the robot can also
        // be used to make a shadow:
        area = new Area(new Rectangle(-width / 2, -height / 2, width + 6, height));
        area.add(new Area(new Ellipse2D.Float(-width / 2, -height / 2 - 3, 8, 4)));
        area.add(new Area(new Ellipse2D.Float(-width / 2, +height / 2, 8, 4)));
        area.add(new Area(new Ellipse2D.Float(width / 2, -height / 2 - 3, 8, 4)));
        area.add(new Area(new Ellipse2D.Float(width / 2, +height / 2, 8, 4)));

        area.transform(AffineTransform.getRotateInstance(getAngle()));
        area.transform(AffineTransform.getTranslateInstance(getX(), getY()));
        g2d.translate(getX(), getY());
        g2d.rotate(getAngle());

        // Draw the 4 wheels:
        g2d.setColor(Color.BLACK);
        g2d.fillOval(-width/2, -height/2 - 3, 8, 4);
        g2d.fillOval(-width/2, +height/2, 8, 4);
        g2d.fillOval( width/2, -height/2 - 3, 8, 4);
        g2d.fillOval( width/2, +height/2, 8, 4);

        g2d.setColor(c);
        g2d.fillRect(-width/2, -height/2, width + 6, height);
    }

    /**
     * Called when a robot has crashed into some object
     * @param x coordinate point on the robot that intersects with some field object
     * @param y coordinate point on the robot
     * @param width the width of the area of overlap with field object (this will be small)
     * @param height the height of the overlap area with field object
     */
    public void crash(int x, int y, int w, int h) {
        System.out.println("CRASH!");
        if (cachedGraphics != null) {
            Graphics2D g = (Graphics2D) cachedGraphics;
            g.setPaint(new RadialGradientPaint(
                             new Point(200, 400), 50, new float[] { 0, 0.3f, 1 },
                             new Color[] { Color.RED, Color.YELLOW, Color.ORANGE }));
            g.fill(createStar(x, y, 10, 25, 3, 0));
        }
    }

    /**
     * Create a 'star' shape
     * @param centerX
     * @param centerY
     * @param innerRadius
     * @param outerRadius
     * @param numRays
     * @param startAngleRad
     * @return
     */
    private static Shape createStar(double centerX, double centerY,
                                    double innerRadius, double outerRadius,
                                    int numRays, double startAngleRad) {
        Path2D path = new Path2D.Double();
        double deltaAngleRad = Math.PI / numRays;
        for (int i = 0; i < numRays * 2; i++) {
            double angleRad = startAngleRad + i * deltaAngleRad;
            double ca = Math.cos(angleRad);
            double sa = Math.sin(angleRad);
            double relX = ca;
            double relY = sa;
            if ((i & 1) == 0) {
                relX *= outerRadius;
                relY *= outerRadius;
            } else {
                relX *= innerRadius;
                relY *= innerRadius;
            }
            if (i == 0) {
                path.moveTo(centerX + relX, centerY + relY);
            } else {
                path.lineTo(centerX + relX, centerY + relY);
            }
        }
        path.closePath();
        return path;
    }

    /** @return the entire area occupied by the Robot for purposes of collision detection. */
    public Area getArea() {
        return area;
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
     * @return robot direction in radians
     */
    public double getAngle() {
        return Math.toRadians(getDirection());
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
