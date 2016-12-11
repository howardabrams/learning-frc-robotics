/* Junit test case for DefaultRobot
 * Note: We only test the unique features to that class.
 */
package org.geeklet.machines.support;

import junit.framework.TestCase;

/**
 * JUnit test suite to validate the methods unique to the {@link
 * DefaultRobot} class.
 *
 * @see RobotTest
 */
public class DefaultRobotTest extends TestCase {
    static final float START_X = 50;
    static final float START_Y = 20;
    static final int START_DIRECTION = 0;

    class MockDefaultRobot extends DefaultRobot {
        public MockDefaultRobot(float startX, float startY, int direction) {
            super(startX, startY, direction);
        }

        public void step() {	}
    }

    DefaultRobot robbie;

    /**
     * Instantiates {@link #robbie}
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        robbie = new MockDefaultRobot(START_X, START_Y, START_DIRECTION);
    }

    /**
     * Test method for {@link org.geeklet.machines.support.DefaultRobot#forward()}.
     */
    public void testForward() {
        float end_X = START_X + 1;
        robbie.forward();
        assertEquals(end_X, robbie.getX());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.DefaultRobot#backward()}.
     */
    public void testBackward() {
        float end_X = START_X - 1;
        robbie.backward();
        assertEquals(end_X, robbie.getX());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.DefaultRobot#turnLeft()}.
     */
    public void testTurnLeft() {
        final float end_Y = START_Y - 1;
        for (int i = 0; i < 90; i++) {
            robbie.turnLeft();
        }
        robbie.forward();  // To prove that the robot actually turned.

        assertEquals(START_X, robbie.getX());
        assertEquals(end_Y, robbie.getY());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.DefaultRobot#turnRight()}.
     */
    public void testTurnRight() {
        final float end_Y = START_Y + 1;
        for (int i = 0; i < 90; i++) {
            robbie.turnRight();
        }
        robbie.forward();  // To prove that the robot actually turned.

        assertEquals(START_X, robbie.getX());
        assertEquals(end_Y, robbie.getY());
    }
}
