/**
 * 
 */
package org.geeklet.machines.support;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 */
@SuppressWarnings("serial")
public class FootballField extends Field {
	/**
	 * 
	 */
	public FootballField(String gameName) {
		super(gameName);
	}

	@Override
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

	@Override
	public void paint(Graphics g) {
		// GraphicsConfiguration gc = this.getGraphicsConfiguration();
	
		// Erase the field and draw the border:
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        for(int sx = 0; sx < width; sx+=50) {
        		g.fillRect(sx, 0, 2, height);
        }

        for(DrawableRobot robot : robots) {
        		if (robot != null)
        			robot.paint(g);
        }
	}
}
