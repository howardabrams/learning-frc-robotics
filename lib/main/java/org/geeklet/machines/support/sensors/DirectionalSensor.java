/*
 * A category of sensors that keep track of <i>how</i>
 * they are attached to a robot.
 */
package org.geeklet.machines.support.sensors;

/**
 * These types of sensors carrying their own direction
 * of how they are attached to a robot.
 */
public abstract class DirectionalSensor extends Sensor {
	/** The attachment direction (whatever that means for a given sensor). */
	int direction;

	/**
	 * Constructor stores the direction of attachment
	 * @param direction Degrees where 0 is in front of the robot
	 */
	public DirectionalSensor(int direction) {
		this.direction = direction;
	}
}
