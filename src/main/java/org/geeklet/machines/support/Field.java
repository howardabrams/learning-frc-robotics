package org.geeklet.machines.support;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public abstract class Field extends Frame {
	public int width; // width and height of field area
	public int height;

	List<DrawableRobot> robots = new LinkedList<DrawableRobot>();

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
	}

	abstract public void runGame();

	abstract public void paint(Graphics g);
	
	/**
	 * @param x the X coordinate of a position on the field
	 * @param y the Y coordinate of a position on the field
	 * @return <code>true</code> if the coordinates are inside the borders of the playing field.
	 */
	boolean onField(int x, int y) {
	    return x > 0 && x < width && y > 0 && y < height;
	}
	

	/**
	 * Compute a random place on the field, that isn't exactly on the border.
	 * @return A random number between 0 and WIDTH ... about.
	 */
	int randomX() {
		return MagicSpells.randomInt(width);
	}

	/**
	 * Compute a random y coordinate on the field, that isn't exactly on the border.
	 * @return A random number between 0 and HEIGHT ... about, as it will take into consideration the TOPEDGE value.
	 */
	int randomY() {
		return MagicSpells.randomInt(height);
	}
}
