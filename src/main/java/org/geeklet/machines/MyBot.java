/**
 * 
 */
package org.geeklet.machines;

import org.geeklet.machines.support.Robot;

/**
 *
 */
public class MyBot extends Robot {
	public int x;
	public int y;
	public int direction;
	
	/**
	 * 
	 */
	public MyBot(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	@Override
	public void step() {
		x += 2;
	}

}
