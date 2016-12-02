/**
 * 
 */
package org.geeklet.machines.support;

import java.awt.Color;
import java.awt.Graphics;

import org.geeklet.machines.support.objects.Wall;

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
		final int size = 20;
		addFieldObject(new Wall(0,0,width,size));
		addFieldObject(new Wall(0,0,size,height));
		addFieldObject(new Wall(width-size,0,size,height));
		addFieldObject(new Wall(0,height-size,width,size));
	}

	@Override
	public void paint(Graphics g) {
		// Erase the field and draw the border:
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        for(int sx = 0; sx < width; sx+=50) {
        		g.fillRect(sx, 0, 2, height);
        }

        paintAll(g);
	}
}
