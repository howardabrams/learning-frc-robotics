package org.geeklet.machines.support.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * A representation of a ball.
 */
public class Ball extends FieldObject {
    public boolean held;

    final static int RADIUS = 10;

    final Color color;

    /**
     * Constructor for a <i>blue</i> Ball object on a field. Assumes that the
     * ball is not being held.
     *
     * @param x
     *            X Coordinate for the location of the ball
     * @param y
     *            Y Coordinate for the location of the ball
     */
    public Ball(int x, int y) {
        this(Color.BLUE, x, y);
    }

    /**
     * Constructor for a ball of a particular color
     *
     * @param color
     *            The color for the ball, e.g. {@link Color#RED}
     * @param x
     *            X Coordinate for the location of the ball
     * @param y
     *            Y Coordinate for the location of the ball
     */
    public Ball(Color color, int x, int y) {
        this(color, x, y, RADIUS);
    }

    /**
     * Constructor for a ball of a particular color and size.
     *
     * @param color
     *            The color for the ball, e.g. {@link Color#RED}
     * @param x
     *            X Coordinate for the location of the ball
     * @param y
     *            Y Coordinate for the location of the ball
     * @param size
     *            The radius of the ball
     */
    public Ball(Color color, int x, int y, int size) {
        super(new Area(new Ellipse2D.Double(x, y, size, size)));
        this.held = false;
        this.color = color;
    }

    /**
     * Displays an ellipse of the given ball's size and color,
     * but only if the ball is not being held.
     */
    @Override
    public void paint(Graphics g) {
        if (!held) {
            defaultPaint(g, color);
        }
    }
}