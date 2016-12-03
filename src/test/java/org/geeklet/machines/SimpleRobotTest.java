package org.geeklet.machines;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for the {@link SimpleRobot} class.
 */
public class SimpleRobotTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public SimpleRobotTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(SimpleRobotTest.class);
	}

	/**
	 * Validate {@link SimpleRobot#move(int)}
	 */
	public void testMoveRight() {
		SimpleRobot someRobot = new SimpleRobot(0, 0, 0);
		someRobot.move(10);
		assertEquals(10, someRobot.x);
		assertEquals(0, someRobot.y);
		assertEquals(0, someRobot.direction);
	}

	public void testMoveUp() {
		SimpleRobot someRobot = new SimpleRobot(0, 0, 90);
		someRobot.move(10);
		assertEquals(0, someRobot.x);
		assertEquals(10, someRobot.y);
		assertEquals(90, someRobot.direction);
	}

	public void testMove45() {
		SimpleRobot suzie = new SimpleRobot(0, 0, 45);
		suzie.move(10);
		assertEquals(suzie.x, suzie.y);
	}

	/**
	 * Test when robot is turning in the positive direction but under 360
	 * degrees
	 */
	public void testTurnPositive() {
		int start = 45;
		int turn_amount = 50;
		SimpleRobot cucumber = new SimpleRobot(0, 0, start);
		cucumber.turn(turn_amount);
		assertEquals(turn_amount + start, cucumber.direction);
	}

	/**
	 * Test when robot is turning in the negative direction but under 360
	 * degrees and the absoulte value of direction is less than degrees, because
	 * since its lower than the start the direction will not be negative
	 */
	public void testTurnLowNegative() {
		int start = 45;
		int turn_amount = -30;
		SimpleRobot cucumber = new SimpleRobot(0, 0, start);
		cucumber.turn(turn_amount);
		assertEquals(start + turn_amount, cucumber.direction);
	}

	/**
	 * Test when robot is turning in the negative direction but under 360
	 * degrees and the absoulte value of direction is greater than degrees
	 */
	public void testTurnHighNegative() {
		int start = 45;
		int turn_amount = -50;
		SimpleRobot cucumber = new SimpleRobot(0, 0, start);
		cucumber.turn(turn_amount);
		assertEquals(360 + (start + turn_amount), cucumber.direction);
	}

	/**
	 * Tests when robot is turning over 360 degrees in the positive direction
	 */
	public void testTurn360Pos() {
		int start = 45;
		int turn_amount = 361;
		int end_direction = start + turn_amount % 360;

		SimpleRobot cucumber = new SimpleRobot(0, 0, start);
		cucumber.turn(turn_amount);

		assertEquals(end_direction, cucumber.direction);
	}

	/**
	 * Tests when robot is turning over 360 degrees in the negative direction
	 */
	public void testTurn360Neg() {
		int start = 45;
		int turn_amount = -361;
		int end_direction = start - Math.abs(turn_amount) % 360;
		SimpleRobot cucumber = new SimpleRobot(0, 0, start);
		cucumber.turn(turn_amount);
		assertEquals(end_direction, cucumber.direction);
	}
}
