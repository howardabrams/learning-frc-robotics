package org.geeklet.machines.support;

import java.awt.*;

/**
 * A robot that implements
 *
 */
public class SimpleRobot implements IRobot {
    public int x = 0;
    public int y = 0;
    public int direction = 0;

    public Color c = new Color(220, 0, 240);

    /**
     * Draws a generic, quite boring robot.
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(direction));

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
     * While many robots would actually move, any robot that doesn't override this method, will be essentially broken.
     */
    public void go() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(int d) {
        this.direction = d;
    }
}
