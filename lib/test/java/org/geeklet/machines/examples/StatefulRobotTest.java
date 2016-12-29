package org.geeklet.machines.examples;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.geeklet.machines.examples.StatefulRobot.State;
import org.geeklet.machines.support.sensors.BallTracker;
import org.geeklet.machines.support.sensors.PhotoSensor;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 * Unit test for the {@link StatefulRobot} class.
 */
public class StatefulRobotTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName
     *            name of the test case
     */
    public StatefulRobotTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(StatefulRobotTest.class);
    }

    public void testScan_State_Broken() {
        // Create a "fake" version of our Sensor ... we call these Mocks
        BallTracker broken = mock(BallTracker.class);
        when(broken.locate()).thenReturn(PhotoSensor.Location.NOT_FOUND);

        StatefulRobot wizzie = new StatefulRobot(0, 0, 45);
        wizzie.eyes = broken;
        
        // Testing a function without any special knowledge of the
        // function's behavior (just its inputs and returned result)
        // is called 'black box' testing:
        assertEquals(wizzie.scan_left_state(), State.SCAN_LEFT);
        assertEquals(wizzie.scan_right_state(), State.SCAN_RIGHT);
        
        // If you can manipulate a class's internal values, like the
        // 'state' variable, you understand (and assume) much about
        // the function's behavior. Testing it this way is called
        // 'white box' testing:
        wizzie.state = State.SCAN_LEFT;
        wizzie.step();
        assertEquals(wizzie.state, State.SCAN_LEFT);   
        
        wizzie.state = State.SCAN_RIGHT;
        wizzie.step();
        assertEquals(wizzie.state, State.SCAN_RIGHT); 
    }

    /** 
     * If we see the ball to the left, we should always go into the 
     * {@link State#SCAN_LEFT} state.
     */
    public void testScan_State_Left() {
        // Create a "fake" version of our Sensor ... we call these Mocks
        BallTracker leftie = mock(BallTracker.class);
        when(leftie.locate()).thenReturn(PhotoSensor.Location.TO_LEFT);

        StatefulRobot wizzie = new StatefulRobot(0, 0, 45);
        wizzie.eyes = leftie;
        
        wizzie.state = State.SCAN_LEFT;
        wizzie.step();
        assertEquals(wizzie.state, State.SCAN_LEFT);
        
        wizzie.state = State.SCAN_RIGHT;
        wizzie.step();
        assertEquals(wizzie.state, State.SCAN_LEFT);
    }
    
    /** 
     * If we see the ball to the right, we should always go into the 
     * {@link State#SCAN_RIGHT} state.
     */
    public void testScan_State_Right() {
        BallTracker rightie = mock(BallTracker.class);
        when(rightie.locate()).thenReturn(PhotoSensor.Location.TO_RIGHT);

        StatefulRobot wizzie = new StatefulRobot(0, 0, 45);
        wizzie.eyes = rightie;
        
        wizzie.state = State.SCAN_RIGHT;
        wizzie.step();
        assertEquals(wizzie.state, State.SCAN_RIGHT);
        
        wizzie.state = State.SCAN_LEFT;
        wizzie.step();
        assertEquals(wizzie.state, State.SCAN_RIGHT);
    }
    
    /** 
     * If we see the ball is to the front of us, we should always proceed
     * forward.
     */
    public void testScan_State_Move() {
        BallTracker tracker = mock(BallTracker.class);
        when(tracker.locate()).thenReturn(PhotoSensor.Location.IN_FRONT);

        StatefulRobot wizzie = new StatefulRobot(0, 0, 45);
        wizzie.eyes = tracker;
        
        wizzie.state = State.SCAN_RIGHT;
        wizzie.step();
        assertEquals(wizzie.state, State.MOVE);
        
        wizzie.state = State.SCAN_LEFT;
        wizzie.step();
        assertEquals(wizzie.state, State.MOVE);
    }
}
