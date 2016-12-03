/*
 * A field that contains a bordered wall, stripes marking areas on the field,
 * as well a single ball for the robots to locate.
 */
package org.geeklet.machines.support;

import java.awt.Color;
import java.awt.Graphics;

import org.geeklet.machines.support.objects.Ball;

/**
 * A dark green field with a single bordered wall surrounding it.
 * Also contains a {@link Ball} for the robot to locate and find.
 */
@SuppressWarnings("serial")
public class FootballField extends ArenaField {
    /**
     * Creates a field of the default size and adds a bordered wall.
     * @param gameName The name of the display frame window
     */
    public FootballField(String gameName, int width, int height) {
        super(gameName, width, height);
        addBalls();
    }

    /**
     * Creates a field of the specified size and adds a bordered wall.
     * @param gameName The name of the display frame window
     * @param width    The width of the frame, in pixels
     * @param height   The height of the frame, in pixels
     */
    public FootballField(String gameName) {
        super(gameName);
        addBalls();
    }

    public void addBalls() {
        addFieldObject(new Ball(randomX(), randomY()));
    }

    /**
     * Paints the field, and then has all the field objects draw themselves.
     * @param g   The graphics primitive from the field's frame
     */
    @Override
    public void paint(Graphics g) {
        // Erase the field and draw the border:
        final Color backgd = new Color(0, 200, 0);
        g.setColor(backgd);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        for(int sx = 0; sx < width; sx+=50) {
            g.fillRect(sx, 0, 2, height);
        }

        paintAll(g);
    }
}
