package org.geeklet.machines;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.mockito.Mockito.*;

import org.geeklet.machines.Ball;
import org.geeklet.machines.Field;
import org.geeklet.machines.Robot;
import org.geeklet.machines.Sensor;
import org.geeklet.machines.Robot.Event;

/**
 * Unit test for simple App.
 */
public class RobotTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public RobotTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(RobotTest.class);
	}

	public void testMoveRight() {
		Robot someRobot = new Robot("bob", 0, 0, 0);
		someRobot.move(10);
		assertEquals(10, someRobot.x);
		assertEquals(0, someRobot.y);
		assertEquals(0, someRobot.direction);
	}

	public void testMoveUp() {
		Robot someRobot = new Robot("bob", 0, 0, 90);
		someRobot.move(10);
		assertEquals(0, someRobot.x);
		assertEquals(10, someRobot.y);
		assertEquals(90, someRobot.direction);
	}

	public void testMove45() {
		Robot suzie = new Robot("suzie", 0, 0, 45);
		suzie.move(10);

		assertEquals(suzie.x, suzie.y);
	}

	public void testScan_State_Broken() {
		Robot wizzie = new Robot("wiz", 0, 0, 45);

		// Create a "fake" version of our Sensor ... we call these Mocks
		Sensor broken = mock(Sensor.class);
		when(broken.ballDistance()).thenReturn(0);
		wizzie.eyes = broken;

		// If the sensor we gave Wizzie is broken (always returns 0),
		// then we should expect to have a NO_BALL_SEEN.
		assertEquals(wizzie.scan_state(), Event.NO_BALL_SEEN);
	}
	
	public void testScan_State_Always_On() {
		Robot wizzy = new Robot("wizwiz", 0, 0, 45);

		// Create a "fake" version of our Sensor ... we call these Mocks
		Sensor alwayson = mock(Sensor.class);
		when(alwayson.ballDistance()).thenReturn(5);
		wizzy.eyes = alwayson;

		// If the sensor we gave Wizzie is alwayson (always returns 5),
		// then we should expect to have a LOCATED_BALL
		assertEquals(wizzy.scan_state(), Event.LOCATED_BALL);
	}
	
	public void testScan_State_Sometimes_Works() {
		Robot wizzy = new Robot("wizwiz", 0, 0, 45);

		// Create a "fake" version of our Sensor ... we call these Mocks
		Sensor sometimesworks = mock(Sensor.class);
		when(sometimesworks.ballDistance()).thenReturn(0).thenReturn(10);
		wizzy.eyes = sometimesworks;

		// If the sensor we gave Wizzie sometimes works and sometimes breaks (either returns 10 or 0),
		// then we should expect to have a NO_BALL_SEEN or LOCATED_BALL
		assertEquals(wizzy.scan_state(), Event.LOCATED_BALL);
	}
	
	public void testGrabState_Ball() {
		// Create mocks of Field and Ball
	    Field afield = mock(Field.class);
	    Ball aball = mock(Ball.class);
	    
	    Robot hector = new Robot("hector", 0, 0, 0, afield);
	    
	    // Give hector a ball
	    when(afield.ballAt(hector)).thenReturn(aball);
	    
	    // Since hector has a valid ball, we should get a HOLDING_BALL event
	    assertEquals(hector.grab_state(), Event.HOLDING_BALL);
    }
	
	public void testGrabState_No_Ball() {
		// Mock of Field
		Field afield = mock(Field.class);
		
		Robot hector = new Robot("hector", 0, 0, 0, afield);
		
		// Give hector a null ball reference
		when(afield.ballAt(hector)).thenReturn(null);
		
		// Since hector has a null ball reference, we should get a MISSED_BALL
		assertEquals(hector.grab_state(), Event.MISSED_BALL);
	}
	
	public void testGrabState_Null_Field() {
		// Give hector a null field reference
		Robot hector = new Robot("hector", 0, 0, 0, null);
		
		// Since hector has a null field reference, we should get a NO_BALL_SEEN
		assertEquals(hector.grab_state(), Event.NO_BALL_SEEN);
	}

	/**
	 * Test when robot is turning in the positive direction but under 360
	 * degrees
	 */
	public void testTurnPositive() {
		int start = 45;
		int turn_amount = 50;
		Robot cucumber = new Robot("cuc", 0, 0, start);
		cucumber.turn(turn_amount);
		assertEquals(turn_amount + start, cucumber.direction);
		System.out.println(cucumber.direction);
	}

	/**
	 * Test when robot is turning in the negative direction but under 360
	 * degrees and the absoulte value of direction is less than degrees, because
	 * since its lower than the start the direction will not be negative
	 */
	public void testTurnLowNegative() {
		int start = 45;
		int turn_amount = -30;
		Robot cucumber = new Robot("cuc", 0, 0, start);
		cucumber.turn(turn_amount);
		assertEquals(start + turn_amount, cucumber.direction);
		System.out.println(cucumber.direction);
	}

	/**
	 * Test when robot is turning in the negative direction but under 360
	 * degrees and the absoulte value of direction is greater than degrees
	 */
	public void testTurnHighNegative() {
		int start = 45;
		int turn_amount = -50;
		Robot cucumber = new Robot("cuc", 0, 0, start);
		cucumber.turn(turn_amount);
		assertEquals(360 + (start + turn_amount), cucumber.direction);
		System.out.println(cucumber.direction);
	}

	/**
	 * Tests when robot is turning over 360 degrees in the positive direction
	 */
	public void testTurn360Pos() {
		int start = 45;
		int turn_amount = 361;
		int end_direction = start + turn_amount % 360;

		Robot cucumber = new Robot("cuc", 0, 0, start);
		cucumber.turn(turn_amount);

		assertEquals(end_direction, cucumber.direction);
		System.out.println(cucumber.direction);
	}

	/**
	 * Tests when robot is turning over 360 degrees in the negative direction
	 */
	public void testTurn360Neg() {
		int start = 45;
		int turn_amount = -361;
		int end_direction = start - Math.abs(turn_amount) % 360;
		Robot cucumber = new Robot("cuc", 0, 0, start);
		cucumber.turn(turn_amount);
		assertEquals(end_direction, cucumber.direction);
		System.out.println(cucumber.direction);
	}

	/**
	 * A Revised Version of the turnNegative method using correctDirection
	 * Method.
	 */
	public void testTurnNegativeCorrected() {
		Robot test2 = new Robot("test2", 0, 0, 0);
		test2.turn(-10);
		test2.correctDirection(test2.direction);
		assertEquals(350, test2.direction);

	}

	public void testhunt_state() {
		Robot apple = new Robot("apple", 0, 0, 0);
		Event e = apple.hunt_state();
		int x2 = apple.x * apple.x;
		int y2 = apple.y * apple.y;
		int xy = x2 + y2; 
		double distance = Math.sqrt(xy);
		assertEquals(e, Event.COMPLETED);
		assertTrue(21.0 > distance && 19.0 < distance);
	}
}
