/*
 * Unit test for the abstract Robot class.
 */
package org.geeklet.machines.support;

import org.geeklet.machines.support.sensors.Sensor;

import junit.framework.TestCase;

/**
 * JUnit Test Suite for the {@link Robot} class.
 *
 * Since the class is abstract, we need to create simplified
 * implementations of it, called {@link MockRobotInts} and {@link
 * MockRobotFloats}.
 */
public class RobotTest extends TestCase {
    static final int INIT_X_INT = 1;
    static final int INIT_Y_INT = 42;
    static final float INIT_X_FLOAT = 4.5f;
    static final float INIT_Y_FLOAT = 5.1f;
    static final int INIT_DIRECTION = 3;

    /**
     * Implementation of {@link Robot} class that contains
     * <code>int</code> types for the X, Y coordinates.
     */
    public class MockRobotInts extends Robot {
        int x;
        int y;
        int direction;

        public MockRobotInts(int i, int j, int k) {
            x = i;
            y = j;
            direction = k;
        }

        public void step() {}
    }

    /**
     * Implementation of {@link Robot} class that contains
     * <code>float</code> types for the X, Y coordinates.
     */
    public class MockRobotFloats extends Robot {
        float x;
        float y;
        int direction;

        public MockRobotFloats(float i, float j, int k) {
            x = i;
            y = j;
            direction = k;
        }

        public void step() {}
    }

    Robot robot1;
    Robot robot2;

    /**
     * Instantiates {@link #robot1} and {@link #robot2}
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        robot1 = new MockRobotInts(INIT_X_INT, INIT_Y_INT, INIT_DIRECTION);
        robot2 = new MockRobotFloats(INIT_X_FLOAT, INIT_Y_FLOAT, INIT_DIRECTION);
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getX()}.
     */
    public void testGetXInts() {
        assertEquals((float)INIT_X_INT, robot1.getX());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getX()}.
     */
    public void testGetXFloats() {
        assertEquals(INIT_X_FLOAT, robot2.getX());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getY()}.
     */
    public void testGetYInts() {
        assertEquals((float)INIT_Y_INT, robot1.getY());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getY()}.
     */
    public void testGetYFloats() {
        assertEquals(INIT_Y_FLOAT, robot2.getY());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getDirection()}.
     */
    public void testGetDirection() {
        assertEquals(INIT_DIRECTION, robot1.getDirection());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getX()}.
     */
    public void testSetXInts() {
        final int new_x = 100;
        robot1.setX(new_x);
        assertEquals((float)new_x, robot1.getX());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getX()}.
     */
    public void testSetXFloats() {
        final float new_x = 102.0f;
        robot2.setX(new_x);
        assertEquals(new_x, robot2.getX());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getY()}.
     */
    public void testSetYInts() {
        final int new_y = 106;
        robot1.setY(new_y);
        assertEquals((float)new_y, robot1.getY());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getY()}.
     */
    public void testSetYFloats() {
        final float new_y = 112.5f;
        robot2.setY(new_y);
        assertEquals(new_y, robot2.getY());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getDirection()}.
     */
    public void testSetPosition() {
        final int new_x = 42;
        final float new_y = 112.5f;
        robot2.set(new_x, new_y);
        assertEquals((float)new_x, robot2.getX());
        assertEquals(new_y, robot2.getY());
    }

    /**
     * Mocking a sensor object that can be added
     */
    class MockSensor extends Sensor {
        public boolean triggered() {
            return true;
        }
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getSensors()}.
     */
    public void testGetSensors() {
        MockSensor sensor = new MockSensor();
        robot1.addSensor(sensor);
        assertEquals(sensor, robot1.getSensors().get(0));
    }
}
