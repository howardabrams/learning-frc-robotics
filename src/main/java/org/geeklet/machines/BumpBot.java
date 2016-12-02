/**
 * 
 */
package org.geeklet.machines;

import org.geeklet.machines.support.Robot;
import org.geeklet.machines.support.sensors.UltraSonic;

/**
 * @author howard.abrams
 *
 */
public class BumpBot extends Robot {
	public int x;
	public int y;
	public int direction;

	public BumpBot(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		
		addSensor(new UltraSonic(direction));
	}

	@Override
	public void step() {
		UltraSonic onlySensor = (UltraSonic) sensors.get(0);
		if (!onlySensor.triggered()) {
			turn(3);
			move(3);
		}
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
