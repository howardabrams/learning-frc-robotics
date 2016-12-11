/*
 * A field that contains a bordered wall.
 * Granted the robot may, or may not notice the bordered wall...
 */
package org.geeklet.machines.support;

import java.awt.Color;
import java.awt.Graphics;

import org.geeklet.machines.support.objects.Wall;

/**
 * A darker green field with a single bordered wall surrounding it...
 * a simple field to practice moving around.
 */
@SuppressWarnings("serial")
public class ArenaField extends Field {
	/**
	 * Creates a field of the default size and adds a bordered wall.
	 * @param gameName The name of the display frame window 
	 */
	public ArenaField(String gameName) {
		super(gameName);
		addWall();
	}
	
	/**
	 * Creates a field of the specified size and adds a bordered wall.
	 * @param gameName The name of the display frame window 
	 * @param width    The width of the frame, in pixels
	 * @param height   The height of the frame, in pixels
	 */
	public ArenaField(String gameName, int width, int height) {
		super(gameName, width, height);
		addWall();
	}

	/**
	 * Given the size of the field, creates a bordered wall surrounding the field of a particular default size.
	 */
	private void addWall() {
		final int size = 20;
		
		final Wall border = new Wall(0,0,width,size);
		border.addArea(0,0,size,height);
		border.addArea(width-size,0,size,height);
		border.addArea(0,height-size,width,size);

		addFieldObject(border);
	}

	/**
	 * Paints the field, and then has all the field objects draw themselves.
	 * @param g   The graphics primitive from the field's frame
	 */
	@Override
	public void paint(Graphics g) {
		// Erase the field and draw the border:
		final Color backgd = new Color(0, 128, 0);
        g.setColor(backgd);
        g.fillRect(0, 0, width, height);

        paintAllObjects(g);
	}
}
