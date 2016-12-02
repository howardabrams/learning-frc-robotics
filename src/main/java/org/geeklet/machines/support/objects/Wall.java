/**
 * 
 */
package org.geeklet.machines.support.objects;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author howard.abrams
 *
 */
public class Wall extends FieldObject {
	public Wall(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	/* (non-Javadoc)
	 * @see org.geeklet.machines.support.objects.IFieldObject#movable()
	 */
	@Override
	public boolean movable() {
		return false;
	}

	@Override
	public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);	
	}

}
