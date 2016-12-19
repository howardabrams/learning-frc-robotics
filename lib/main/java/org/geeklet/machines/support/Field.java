/*
 * The Graphics machinery that is shared by all fields in order to render the state of a game.
 */
package org.geeklet.machines.support;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Area;
import java.util.LinkedList;
import java.util.List;

import org.geeklet.machines.support.objects.FieldObject;
import org.geeklet.machines.support.objects.IFieldObject;
import org.geeklet.machines.support.sensors.ISensor;

/**
 * A Field is a graphics frame that draws the state of a game,
 * including calling the mechanism for each {@link FieldObject} and
 * {@link DrawableRobot} to render itself in the field of view.
 */
@SuppressWarnings("serial")
public abstract class Field extends Frame {
    /** The width of the field in rendered Java2D pixels, which we call units. */
    public int width;
    /** The height of the field in rendered Java2D pixels, which we call units. */
    public int height;

    /** State value to determine if the {@link #runGame()} should continue to run. */
    private boolean continueGame = true;

    /** A robots playing on the field, which should include the players. */
    List<DrawableRobot> robots = new LinkedList<DrawableRobot>();
    /** All objects on the field, excluding the robots. */
    List<FieldObject> objects = new LinkedList<FieldObject>();

    /**
     * Construct a field fame
     * @param gameName The name of the frame's window
     */
    public Field(String gameName) {
        this(gameName, 400, 400);
    }

    /**
     * Construct a field frame of a particular size
     * @param gameName  The name of the frame's window
     * @param width     The number of pixels for the frame's width
     * @param height The number of pixels of the <i>complete</i>
     * frame's height, which may include the operating system window
     * decoration. We should probably address that at some point.
     */
    public Field(String gameName, int width, int height) {
        super(gameName);
        this.width = width;
        this.height = height;
        setSize(width, height);

        // Now, we want to be sure we properly dispose of resources
        // this frame is using when the window is closed. We use an
        // anonymous inner class adapter for this.
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                    System.exit(0);
                }
            });
        setVisible(true);
        setResizable(false);
    }

    /**
     * Place a robot on the field. If the robot's internal x,y
     * coordinate are outside of the playing field, this may adjust
     * the robot's position on the field.
     * @param robot a toy with a given position
     */
    public void addRobot(DrawableRobot robot) {
        // If the robot has coordinates of -1,-1
        // we move the robot to some random position on field
        if (robot.getX() == -1 && robot.getY() == -1)
            robot.set(randomX(), randomY());

        // If the robot is positioned too close to the edge of the
        // playing field, we adjust the robot closer to the center.
        final int border_buffer = 20;
        if (robot.getX() < border_buffer)
            robot.setX(border_buffer);
        else if (robot.getX() > width - border_buffer)
            robot.setX(width - border_buffer);

        if (robot.getY() < border_buffer)
            robot.setY(border_buffer);
        else if (robot.getY() > height - border_buffer)
            robot.setY(height - border_buffer);

        robots.add(robot);

        for (ISensor sensor : robot.getSensors()) {
            sensor.addField(this);
            sensor.addRobot(robot);
        }
    }

    /**
     * If the robot given to this class acts like a {@link Robot},
     * and even <i>smells</i> like a {@link Robot}, then for our
     * purposes, it will <i>be</i> a real robot.
     * @param robot An object that implements the robot field variables,
     * <code>x</code>, <code>y</code> and <code>direction</code>.
     */
    public void addRobot(Object robot) {
    		final Robot frank = MagicSpells.createFrankieRobot(robot);
    		this.addRobot(frank);
    }

    /**
     * This will repeated call each robot's {@link Robot#step()}
     * method until the game ends.
     */
    public void runGame() {
        while(continueGame()) {
            for(DrawableRobot robot : robots) {
                robot.step();
                this.repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    /**
     * Checks if any {@link Robot} has collided with any {@link FieldObject}.
     * Override in subclasses to provide an end game check.
     * @return
     */
    protected boolean continueGame() {
        for(final DrawableRobot robot : robots) {
            if (robot.getArea() != null) {
                for(final FieldObject obj : objects) {
                    final Area a = (Area) robot.getArea().clone();
                    a.intersect(obj.getArea());
                    if (!a.isEmpty()) {
                        robot.crash(a.getBounds().x, a.getBounds().y,
                                    a.getBounds().width, a.getBounds().height);
                        continueGame = false;
                        return false;  // break out early
                    }
                }
            }
        }
        return continueGame;
    }

    /**
     * A field must be able to paint itself if it is given a graphics
     * context.
     * @param g a graphic context within a Field's frame
     */
    abstract public void paint(Graphics g);

    /**
     * Subclasses of the {@link Field} should call this in order to
     * paint all {@link Robot}s and {@link FieldObject}s.
     * @param g a graphics context associated with the {@link Frame}
     */
    public void paintAllObjects(Graphics g) {
        for (IFieldObject obj : objects) {
            if (obj != null)
                obj.paint(g);
        }
        for (DrawableRobot robot : robots) {
            if (robot != null)
                robot.paint(g);
        }
    }

    /**
     * Place an field object on the field, which adds it to its list.
     * @param obj some field object with its coordinates and size
     */
    protected void addFieldObject(FieldObject obj) {
        objects.add(obj);
    }

    /**
     * @return list of objects currently placed on the field.
     */
    public List<FieldObject> getFieldObjects() {
        return objects;
    }

    /**
     * Looks to see if FieldObjects of a particular class are located
     * inside the given area.
     * @param type a class reference to a particular {@link FieldObject}
     * @param area an area which should overlap with the field
     * @return <code>true</code> if part of the area occupied by the object overlaps with a section of the given area.
     */
    public boolean isAnyObjectinArea(Class<? extends FieldObject> type, Area area) {
        for (final FieldObject obj : getFieldObjects()) {

            // If a type is given, it must be of a particular class
            if (type == null || obj.getClass() == type) {

                // Create an intersection between the given area
                // and the area of this particular object:
                final Area overlap = (Area) area.clone();
                overlap.intersect(obj.getArea());
                if (!overlap.isEmpty())
                    return true;
            }
        }
        return false;
    }

    /**
     * @param area e.g., like the triangle of a field of view
     * @return <code>true</true> if any {@link FieldObject} on the field <i>intersects</i>
     *         with the given area.
     */
    public boolean isAnyObjectinArea(Area area) {
        return isAnyObjectinArea(null, area);
    }

    /**
     * Predicate function that check if a point is within any object on the field.
     * @param x  X coordinate of a point on the field.
     * @param y  Y coordinate of a point on the field.
     * @return <code>true</code> if the given position is inside the
     * area of any object on the field
     */
    public boolean insideAnyObject(int x, int y) {
        return insideAnyObject(new Point(x, y));
    }

    /**
     * Predicate function that check if a point is within any object on the field.
     * @param p A particular point on the field
     * @return <code>true</code> if the given position is inside the
     * area of any object on the field
     */
    public boolean insideAnyObject(Point p) {
        for (FieldObject o : objects) {
            if (o.inside(p))
                return true;
        }
        return false;
    }

    /**
     * @param x the X coordinate of a position on the field
     * @param y the Y coordinate of a position on the field
     * @return <code>true</code> if the coordinates are inside the
     * borders of the playing field.
     */
    boolean onField(int x, int y) {
        return x > 0 && x < width && y > 0 && y < height;
    }


    /**
     * Compute a random place on the field.
     * @return A random number between 0 and WIDTH ... about.
     */
    public int randomX() {
        return MagicSpells.randomInt(width);
    }

    /**
     * Compute a random y coordinate on the field.
     * @return A random number between 0 and HEIGHT ... about, as it
     * will take into consideration the TOPEDGE value.
     */
    public int randomY() {
        return MagicSpells.randomInt(height);
    }

    /**
     * @return A random place on the field formatted as a
     * coordinate. Pull the pieces out in your code with something
     * like: <pre>Point place = field.random(); x =
     * place.x;</pre>
     */
    public Point randomSpot() {
        return new Point(randomX(), randomY());
    }

    /**
     * @return A coordinate position in the center of the field.
     */
    public Point center() {
        return new Point(width/2, height/2);
    }
}
