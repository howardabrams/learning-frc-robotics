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

        public MockRobotFloats(int i, int j, int k) {
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
        robot1 = new MockRobotInts(1, 2, 3);
        robot2 = new MockRobotFloats(4, 5, 6);
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getX()}.
     */
    public void testGetXInts() {
        assertEquals(1.0f, robot1.getX());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getX()}.
     */
    public void testGetXFloats() {
        assertEquals(4.0f, robot2.getX());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getY()}.
     */
    public void testGetYInts() {
        assertEquals(2.0f, robot1.getY());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getY()}.
     */
    public void testGetYFloats() {
        assertEquals(5.0f, robot2.getY());
    }

    /**
     * Test method for {@link org.geeklet.machines.support.Robot#getDirection()}.
     */
    public void testGetDirection() {
        assertEquals(3, robot1.getDirection());
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
