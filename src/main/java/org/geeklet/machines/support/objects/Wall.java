/**
 *
 */
package org.geeklet.machines.support.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Area;

/**
 * @author howard.abrams
 *
 */
public class Wall extends FieldObject {
    /** The color of the wall defaults to a slimming black. */
    final Color color;
    final int thickness;
    final Color strokeColor;

    /**
     * A wall is little more than a single rectangle at a specific
     * location on a field given by the x,y coordinates.
     *
     * @param x
     *            The X coordinate on the field, where 0,0 is upper left
     * @param y
     *            The Y coordinate on the field
     * @param width
     *            The width of the object in pixels
     * @param height
     *            The height of the object
     */
    public Wall(int x, int y, int width, int height) {
        this(Color.DARK_GRAY, 1, Color.BLACK, x, y, width, height);
    }

    /**
     * A wall is little more than a single rectangle at a specific
     * location on a field given by the x,y coordinates.
     *
     * @param color
     *            The paint color used to fill in the area of the wall
     * @param x
     *            The X coordinate on the field, where 0,0 is upper left
     * @param y
     *            The Y coordinate on the field
     * @param width
     *            The width of the object in pixels
     * @param height
     *            The height of the object
     */
    public Wall(Color color, int borderWidth, Color borderColor,
                int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = color;
        this.thickness = borderWidth;
        this.strokeColor = borderColor;
    }

    /**
     * Renders the wall as a rectangular area on the field.
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Stroke oldStroke = g2.getStroke();

        // Fill the resulting Area.
        g2.setPaint(color);
        g2.fill(area);

        // Draw the outline of the resulting Area.
        g2.setStroke(new BasicStroke(thickness));
        g2.setPaint(strokeColor);
        g2.draw(area);

        g2.setStroke(oldStroke);
    }
}
