/**
 * For the purposes of our learning, a Robot has a few fields:
 * 
 *     Coordinates that are labeled `x` and `y`
 *     Direction labeled `direction`
 * 
 */
package org.geeklet.machines;

import org.geeklet.machines.support.Robot;

/**
 *
 */
public class SimpleRobot extends Robot {
	public int x;
	public int y;
	public int direction;
	
	public SimpleRobot(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	@Override
	public void step() {
		turn(1);
		move(4);
	}

	/**
	 * Reposition the robot based on its current direction and position.
	 *
	 * @param amount
	 *            The number of units (pixels) to move the robot
	 */
	void move(int amount) {
		double rads = Math.toRadians(direction);
		x += Math.cos(rads) * amount;
		y += Math.sin(rads) * amount;
	}

	/**
	 * Rotates the robot some degrees left (if positive), or right (if
	 * negative).
	 *
	 * @param degrees
	 *            Amount to turn robot, from 0 to 360.
	 * @return direction in degrees from 0 to 360
	 */
	void turn(double degrees) {
		direction = (int) ((direction + degrees) % 360);

		if (direction < 0) {
			direction = 360 + direction;
		}
	}
	
}
