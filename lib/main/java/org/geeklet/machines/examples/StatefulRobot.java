/*
 * Full-blown robot that can seek and find balls on a field.
 * Accompanies the lesson chapter: docs/100-fsm-2.md
 */

package org.geeklet.machines.examples;

import java.awt.Color;

import org.geeklet.machines.support.DefaultRobot;
import org.geeklet.machines.support.objects.Ball;
import org.geeklet.machines.support.sensors.BallTracker;
import org.geeklet.machines.support.sensors.PhotoSensor;
import org.geeklet.machines.support.sensors.UltraSonic;

/**
 * Robot that uses a photosensor to locate objects in its field
 * environment.
 */
public class StatefulRobot extends DefaultRobot {
    /**
     * Our {@link UltraSonic} sensor that detects objects at close
     * range.
     */
    UltraSonic sonic;

    /**
     * A type of {@link PhotoSensor} that detects {@link Ball} objects
     * at a distance.
     */
    BallTracker eyes;

    public enum State {
        SCAN_LEFT, SCAN_RIGHT, MOVE, GRAB, GOAL, ERROR
    }

    /**
     * Holds the Robot's current 'state'. Calling the {@link #step()}
     * function should execute the code associated with the current
     * state.
     */
    State state = State.SCAN_LEFT;

    /**
     * A Robot represents a general robot somewhere on the playing
     * field.
     *
     * @param x   The 'x' coordinate
     * @param y   The 'y' coordinate
     * @param direction
     *            The angle in degrees, where 0° is towards the right
     */
    public StatefulRobot(int x, int y, int direction) {
        super(x, y, direction);
        this.state = State.SCAN_LEFT;

        sonic = new UltraSonic(0);
        addSensor(sonic);

        eyes = new BallTracker(0);
        addSensor(eyes);
    }

    /**
     * Repeatedly calling this function executes a single robotic
     * action (associated with the Robot's state).
     */
    public void step() {
        switch (state) {
            case SCAN_LEFT:
                state = scan_left_state();
                break;

            case SCAN_RIGHT:
                state = scan_right_state();
                break;

            case MOVE:
                state = move_state();
                break;

            case GRAB:
                state = grab_state();
                break;

            case GOAL:
                state = goal_state();
                break;

            case ERROR:
                message("Got myself into a bad, bad state.");
                break;
        }
    }

    /**
     * The <code>SCAN_LEFT</code> state will spin the robot around in towards the left,
     * looking for a ball.
     *
     * @return
     * <ul>
     * <li> {@link State#SCAN_LEFT} if a ball is located towards the left side of the sensor view
     * <li> {@link State#SCAN_RIGHT} if a ball is located towards the right side of the sensor view
     * <li> {@link State#MOVE} if a ball is located directly in front of the robot
     * <li> {@link State#SCAN_LEFT} if no ball is seen
     */
    State scan_left_state() {
        turnLeft();
        switch(eyes.locate()) {
            case TO_LEFT:
                return State.SCAN_LEFT;
            case TO_RIGHT:
                return State.SCAN_RIGHT;
            case IN_FRONT:
                return State.MOVE;
            default:
                return State.SCAN_LEFT;
        }
    }

    /**
     * The SCAN state will spin the robot around in towards the right,
     * looking for a ball.
     *
     * @return
     * <ul>
     * <li> {@link State#SCAN_LEFT} if a ball is located towards the left side of the sensor view
     * <li> {@link State#SCAN_RIGHT} if a ball is located towards the right side of the sensor view
     * <li> {@link State#MOVE} if a ball is located directly in front of the robot
     * <li> {@link State#SCAN_RIGHT} if no ball is seen
     */
    State scan_right_state() {
        turnRight();

        switch(eyes.locate()) {
            case TO_LEFT:
                return State.SCAN_LEFT;
            case TO_RIGHT:
                return State.SCAN_RIGHT;
            case IN_FRONT:
                return State.MOVE;
            default:
                return State.SCAN_RIGHT;
        }
    }

    /**
     * The MOVE state creeps up on the ball one unit.
     * @return
     * <ul>
     * <li> {@link State#MOVE} if the ball is still in front
     * <li> {@link State#GRAB} if the ball is still in front <i>and</i>
     * if the {@link UltraSonic} sensor, {@link #sonic}, triggers something
     * (which means that the ball is within range).
     * </ul>
     */
    State move_state() {
        forward();

        switch(eyes.locate()) {
            case TO_LEFT:
                return State.SCAN_LEFT;
            case TO_RIGHT:
                return State.SCAN_RIGHT;
            case IN_FRONT:
                if (sonic.triggered())
                    return State.GRAB;
                else
                    return State.MOVE;

            default:
                return State.SCAN_RIGHT;
        }
    }

    /**
     * The GRAB state acquires any ball in the same position as the robot.
     *
     * @return {@link Event#MISSED_BALL} if no ball is in the same position (but
     *         Robot is on the field) {@link Event#HOLDING_BALL} if we acquired
     *         a ball in the {@link #holding} variable.
     */
    State grab_state() {
        defaultColor = new Color(0, 0, 128);
        return State.GOAL;
    }

    /**
     * Orients robot toward the opposing goal
     */
    State goal_state() {
        return State.GOAL;
    }

    /**
     * Wrapper around the console output that displays the current
     * state as well as formats any of the arguments.
     *
     * @param format The format string, see {@link
     *            String#format(String, Object...)} for details.
     * @param arguments
     *            The arguments fed into the format string.
     */
    public void message(String format, Object... arguments) {
        super.message(String.format("State(%s): %s", state, format), arguments);
    }
}
