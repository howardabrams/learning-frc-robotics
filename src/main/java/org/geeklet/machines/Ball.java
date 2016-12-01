package org.geeklet.machines;

/**
 * A representation of a ball.
 */
public class Ball {
	int x;
	int y;
	boolean held;

	/**
	 * Constructor for a Ball object on a field. Assumes that the ball is not
	 * being held.
	 * 
	 * @param x
	 *            Coordinate for the location of the ball
	 * @param y
	 *            Coordinate for the location of the ball
	 */
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.held = false;
	}

	/**
	 * @return coordinate of the ball or states if ball is held.
	 */
	public String toString() {
		if (held)
			return "(held)";
		else
			return "(" + x + ", " + y + ")";
	}
}
