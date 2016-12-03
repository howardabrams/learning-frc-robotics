package org.geeklet.machines;

import org.geeklet.machines.support.DefaultRobot;
import org.geeklet.machines.support.objects.Ball;

import stuff.ExampleFullField;
import stuff.Sensor;

/**
 * A definition for a generic Robot FSM (State Machine)
 */
public class StatefulRobot extends DefaultRobot {
    Sensor eyes;
    Ball holding;
    ExampleFullField field;

    public enum State {
        HUNT, SCAN, MOVE, GRAB, GOAL, RUN, DROP, ERROR
    }

    /**
     * Holds the Robot's current 'state'. Calling the {@link #step()} function
     * should execute the code associated with the current state.
     */
    private State state = State.HUNT;

    public enum Event {
        NONE, // No event current triggered
        COMPLETED, // Action for "state" completed
        LOCATED_GOAL, REACHED_GOAL, // States associated with the goal
        LOCATED_BALL, BALL_DISAPPEARED, NO_BALL_SEEN, MISSED_BALL, DROPPED_BALL, BALL_IN_RANGE, HOLDING_BALL, BALL_AHEAD
    }

    /**
     * A Robot represents a general robot somewhere on the playing field.
     *
     * @param name
     *            The identify label
     * @param x
     *            The 'x' coordinate
     * @param y
     *            The 'y' coordinate
     * @param direction
     *            The angle in degrees, where 0° is towards the right
     */
    public StatefulRobot(int x, int y, int direction) {
        super(x, y, direction);
        this.holding = null;
        this.state = State.HUNT;
        this.direction = direction;
    }



    /**
     * Corrects the direction of the robot if it goes below 0º (This is a
     * temporary fix).
     *
     * @param direction
     *            (direction of the robot).
     */
    void correctDirection(int direction) {
        if (direction < 0) {
            this.direction = 360 + direction;
        }
    }

    /**
     * Repeatedly calling this function executes a single robotic action
     * (associated with the Robot's state). This should be called in a 'tight
     * loop' from the robot's "owner", e.g. the {@link ExampleFullField}.
     */
    public void step() {
        switch (state) {
            case HUNT:
                switch (hunt_state()) {
                    case COMPLETED:
                        state = State.SCAN;
                        break;
                    default:
                        state = State.ERROR;
                }
                break;

            case SCAN:
                switch (scan_state()) {
                    case NO_BALL_SEEN:
                        state = State.HUNT;
                        break;
                    case LOCATED_BALL:
                        state = State.MOVE;
                        break;
                    default:
                        state = State.ERROR;
                }
                break;

            case MOVE:
                switch (move_state()) {
                    case BALL_DISAPPEARED:
                        state = State.SCAN;
                        break;
                    case BALL_IN_RANGE:
                        state = State.GRAB;
                        break;
                    default:
                        state = State.ERROR;
                }
                break;

            case GRAB:
                switch (grab_state()) {
                    case MISSED_BALL:
                        state = State.SCAN;
                        break;
                    case HOLDING_BALL:
                        state = State.GOAL;
                        break;
                    default:
                        state = State.ERROR;
                }
                break;

            case GOAL:
                switch (goal_state()) {
                    case LOCATED_GOAL:
                        state = State.RUN;
                        break;
                    default:
                        state = State.ERROR;
                }
                break;

            case RUN:
                switch (run_state()) {
                    case DROPPED_BALL:
                        state = State.SCAN;
                        break;
                    case REACHED_GOAL:
                        state = State.DROP;
                        break;
                    default:
                        state = State.ERROR;
                }
                break;

            case DROP:
                switch (drop_state()) {
                    default:
                        state = State.HUNT;
                }

            case ERROR:
                message("Got myself into a bad, bad state.");
                break;
        }
    }

    /**
     * The HUNT state will move the robot to a random place 20 paces away.
     *
     * @return {@link Event#COMPLETED} ... always.
     */
    Event hunt_state() {
        turn(Math.random() * 360 - 180); // Math.random returns a number from
        // zero to one
        message("Heading off at %d", direction);

        move(20);
        message("Now at %d,%d", x, y);
        return Event.COMPLETED;
    }

    /**
     * The SCAN state will spin the robot around in a circle scanning for a
     * ball. Stops and returns , and
     *
     * @return {@link Event#LOCATED_BALL} if ball is found,
     *         {@link Event#NO_BALL_SEEN}, otherwise.
     */
    Event scan_state() {
        for (int d = 0; d < 360; d += 10) {
            turn(10);
            int distance = eyes.ballDistance();
            if (distance > 0) {
                message("Found ball at %d° about %d away", direction, distance);
                return Event.LOCATED_BALL;
            }
        }
        return Event.NO_BALL_SEEN;
    }

    /**
     * The MOVE state creeps up on the ball one unit.
     *
     * @return {@link Event#BALL_AHEAD} if we are still moving towards the ball.
     *         <br/>
     *         {@link Event#BALL_IN_RANGE} if we are on top of the ball, and can
     *         pick it up. <br/>
     *         {@link Event#BALL_DISAPPEARED}, otherwise.
     */
    Event move_state() {
        move(1);
        message("Moved to %d,%d", x, y);

        int distance = eyes.ballDistance();
        if (distance == 0)
            return Event.BALL_IN_RANGE;
        else if (distance > 0)
            return Event.BALL_AHEAD;
        else
            return Event.BALL_DISAPPEARED;
    }

    /**
     * The GRAB state acquires any ball in the same position as the robot.
     *
     * @return {@link Event#MISSED_BALL} if no ball is in the same position (but
     *         Robot is on the field) {@link Event#HOLDING_BALL} if we acquired
     *         a ball in the {@link #holding} variable.
     */
    Event grab_state() {
        if (field != null) {
            holding = field.ballAt(this);
            message("Grabbing ball: " + holding);

            if (holding == null)
                return Event.MISSED_BALL;
            else
                return Event.HOLDING_BALL;
        } else
            return Event.NO_BALL_SEEN;
    }

    /**
     * Orients robot toward the opposing goal
     */
    Event goal_state() {
        return Event.NONE;
    }

    /**
     * Moves robot towards goal
     */
    Event run_state() {
        return Event.NONE;
    }

    /**
     * The DROP state drops the ball where the robot is at ... hopefully, in the
     * end zone.
     *
     * @return {@link Event#COMPLETED} ... whether it had a ball or not.
     */
    Event drop_state() {
        // 7. *drop*: leave the ball in end zone
        if (holding != null) {
            holding.held = false;
            holding = null;
            message("Dropped ball at %d,%d", x, y);
        } else
            message("Dropping nothing at %d,%d", x, y);
        return Event.COMPLETED;
    }

    /**
     * Displays the Robot's name, position and direction.
     */
    public String toString() {
        return String.format("Robot at (%d,%d) direction=%d", x, y, direction);
    }

    /**
     * Wrapper around the console output that displays the current state as well
     * as formats any of the arguments.
     *
     * @param format
     *            The format string, see
     *            {@link String#format(String, Object...)} for details.
     * @param arguments
     *            The arguments fed into the format string.
     */
    public void message(String format, Object... arguments) {
        String stateful = String.format("State(%S): ", state);
        String output = String.format(format, arguments);
        System.out.println(stateful + output);
    }
}
