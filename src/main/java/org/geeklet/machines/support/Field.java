package org.geeklet.machines.support;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import org.geeklet.machines.support.objects.FieldObject;
import org.geeklet.machines.support.objects.IFieldObject;
import org.geeklet.machines.support.sensors.ISensor;

@SuppressWarnings("serial")
public abstract class Field extends Frame {
	public int width; // width and height of field area
	public int height;

	List<DrawableRobot> robots = new LinkedList<DrawableRobot>();
	List<FieldObject> objects = new LinkedList<FieldObject>();
	
	public Field(String gameName) {
		this(gameName, 400, 300);
	}

	public Field(String gameName, int width, int height) {
		super(gameName);
		this.width = width;
		this.height = height;
		setSize(width, height);

		// Now, we want to be sure we properly dispose of resources
		// this frame is using when the window is closed. We use
		// an anonymous inner class adapter for this.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
		setVisible(true);
		setResizable(false);
	}

	public void addRobot(DrawableRobot robot) {
		robots.add(robot);
		for (ISensor sensor : robot.getSensors()) {
			sensor.addField(this);
			sensor.addRobot(robot);
		}
	}

	public void runGame() {
		while(true) {
			for(DrawableRobot robot : robots) {
				robot.step();
				this.repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	abstract public void paint(Graphics g);
	
	public void paintAll(Graphics g) {
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
	 * @param x the X coordinate of a position on the field
	 * @param y the Y coordinate of a position on the field
	 * @return <code>true</code> if the coordinates are inside the borders of the playing field.
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
	 * @return A random number between 0 and HEIGHT ... about, as it will take into consideration the TOPEDGE value.
	 */
	public int randomY() {
		return MagicSpells.randomInt(height);
	}

	/**
	 * @return A random place on the field formatted as a coordinate. Pull the pieces out in your code with something like: <code>Coordinate place = field.random();
	 * x = place.x;</code>
	 */
	public Coordinate randomCoordinate() {
		return new Coordinate(randomX(), randomY());
	}
	
	/**
	 * @return A coordinate position in the center of the field.
	 */
	public Coordinate centerField() {
		return new Coordinate(width/2, height/2);
	}
	
	protected void addFieldObject(FieldObject obj) {
		objects.add(obj);
	}
	
	public List<FieldObject> getFieldObjects() {
		return objects;
	}
}
