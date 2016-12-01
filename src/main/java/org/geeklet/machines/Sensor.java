/*
 * (c) 2016, Pigmice
 */
package org.geeklet.machines;

/**
 * An interface between a Robot and the field of balls.
 */
public class Sensor {
	private Field field;
	private Robot attachedRobot;

	/**
	 * Creates a sensor constructor
	 * 
	 * @param field
	 * @param robot
	 */

	public Sensor(Field field, Robot robot) {
		this.field = field;
		this.attachedRobot = robot;
	}

	/**
	 * Calculates the coordinate point of however many units you want in front
	 * of the robot
	 * 
	 * @param amount:
	 *            the number of units you want the coordinate point of in front
	 *            of attached robot
	 * @return coordinate point of units (specified in amount variable) in front
	 *         of robot
	 */
	private Coordinate coordinateInFront(int amount) {
		double rads = Math.toRadians(attachedRobot.direction);
		return new Coordinate(attachedRobot.x + Math.cos(rads) * amount, attachedRobot.y + Math.sin(rads) * amount);
	}

	/**
	 * Determines if a ball is close enough to try and get
	 * 
	 * @param ball
	 * @param position
	 * 
	 * @return How far away a ball is
	 */
	protected boolean nearBall(Ball ball, Coordinate position) {
		int closeness = 1; // how far the robot can reach to grab a ball
		return ball.x >= position.x - closeness && ball.x <= position.x + closeness && ball.y >= position.y - closeness
				&& ball.y <= position.y + closeness;
	}

	/**
	 * Checks 30 units forward to see if a ball is close to robot
	 * 
	 * @return distance, in paces, of a ball that is front of the robot, or -1
	 *         if no ball is seen.
	 */
	int ballDistance() {
		for (int d = 0; d < 30; d++) { // distance (looking forward 30 units)
			for (Ball ball : field.balls) {
				Coordinate position = coordinateInFront(d);
				if (nearBall(ball, position))
					return d;
			}
		}
		return -1;
	}
}
