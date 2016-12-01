package org.geeklet.machines.support;

import java.util.List;

import org.geeklet.machines.Robot;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

/**
 * A Field of balls and a single robot.
 */
@SuppressWarnings("serial")
public class Field extends Frame {
    // The 'static' makes these variables constant
    static int WIDTH = 400; // width and height of field area
    static int HEIGHT = 300;
    static int BORDER = 6;
    // At least on the Mac, the top of the actual graphical area is
    // covered by the window title, which seems to be about 22 pixels
    // tall. We'll need to add some offsets.
    static int TOPEDGE = 22;

    IRobot robot;
    List<Ball> balls = new ArrayList<Ball>();

    // The 'static' on a method turns it into a function which means
    // we can just call it, without making a "new" Field.

    /**
     * Compute a random place on the field, that isn't exactly on the border.
     * @return A random number between 0 and WIDTH ... about.
     */
    static int randomX() {
        return (int) Math.round(Math.random() * (WIDTH-BORDER*4) + BORDER*2);
    }

    /**
     * Compute a random y coordinate on the field, that isn't exactly on the border.
     * @return A random number between 0 and HEIGHT ... about, as it will take into consideration the TOPEDGE value.
     */
    static int randomY() {
        return (int) Math.round(Math.random() * (HEIGHT-BORDER*4-TOPEDGE) + BORDER*2 + TOPEDGE);
    }

    static Coordinate center() {
        return new Coordinate( WIDTH/2,
                               (HEIGHT-TOPEDGE)/2 + TOPEDGE/2);
    }

    /**
     * @param x the X coordinate of a position on the field
     * @param y the Y coordinate of a position on the field
     * @return <code>true</code> if the coordinates are inside the borders of the playing field.
     */
    static boolean onField(int x, int y) {
        return x > BORDER && x < WIDTH-BORDER && y > BORDER && y < HEIGHT-BORDER;
    }

    /**
     * @param ball a reference to a ball and its coordinates
     * @return <code>true</code> if the ball's coordinates are inside the borders of the playing field.
     */
    static boolean onField(Ball ball) {
        return onField(ball.x, ball.y);
    }

    boolean allOnField() {
        for (Ball ball: balls) {
            if (! onField(ball))
                return false;
        }
        return true;
    }

    /**
     * @param x  The x coordinate where a ball may be
     * @param y  The y coordinate of a potential position of a ball
     * @return A {@link Ball} at the particular coordinates, or
     *         <code>null</code> if the space is empty.
     */
    Ball ballAt(int x, int y) {
        for (Ball ball : balls) {
            if (ball.x == x && ball.y == y)
                return ball;
        }
        return null;
    }

    /**
     * @param robot
     *            A reference to a {@link Robot}
     * @return A {@link Ball} at the coordinates of the specified robot, or
     *         <code>null</code> otherwise.
     */
    public Ball ballAt(IRobot robot) {
        return ballAt(robot.getX(), robot.getY());
    }

    /**
     * Places a ball on the playing field
     * @param ball An active {@link Ball} with coordinates within the playing field.
     */
    void addBall(Ball ball) {
        balls.add(ball);
    }

    /**
     * Create a ball instance at a random location on the playing field.
     */
    void addBall() {
        Ball aball = new Ball(randomX(), randomY());
        balls.add(aball);
    }

    /**
     * Constructor creates a "viewport" on the operating systems' desktop... a window, if you will.
     */
    public Field() {
        super("Bunnybot Simulator");
        setSize(WIDTH,HEIGHT);
        setVisible(true);

        // Now, we want to be sure we properly dispose of resources
        // this frame is using when the window is closed. We use
        // an anonymous inner class adapter for this.
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                    System.exit(0);
                }
            });
    }

    /**
     * Renders the playing field including all robots and balls.
     */
    public void paint(Graphics g) {
        // Erase the field and draw the border:
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw the background field:
        g.setColor(Color.green);
        g.fillRect(BORDER, BORDER+TOPEDGE,
                   WIDTH-BORDER*2, HEIGHT-BORDER*2-TOPEDGE);

        // Draw the field grid lines:
        g.setColor(Color.WHITE);
        g.fillRect(WIDTH/2 - BORDER/2, 0, BORDER, HEIGHT);
        g.fillRect(0, HEIGHT/2 - BORDER/2, WIDTH, BORDER);

        // Draw the balls on the field
        for (Ball ball : balls) {
            g.setColor(Color.red);
            g.fillOval(ball.x - 4, ball.y - 4, 8, 8);
        }

        if (robot != null)
            robot.paint(g);
    }

    public static void main(String[] args) {
        Field field = new Field();
        Coordinate center = Field.center();
        field.robot = new Robot("bob", center.x, center.y, 0, field);

        // Place three balls on the field:
        for (int b = 0; b < 3; b++) {
            field.addBall();
        }

        while (field.allOnField()) {
            // field.robot.setDirection(d);
            field.robot.go();
            field.repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }


        System.out.println("FINISHED");
    }
}
