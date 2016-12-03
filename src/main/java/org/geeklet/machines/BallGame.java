/*
 * A Simple game that pulls in a Field, a Robot and some balls for the
 * Robot to find and deliver to the end zone. This can be an example
 * of more complex games and obstacle courses.
 */

package org.geeklet.machines;

import org.geeklet.machines.support.Coordinate;
import org.geeklet.machines.support.Field;
import org.geeklet.machines.support.FootballField;
import org.geeklet.machines.support.Robot;

/**
 * A game that add the {@link StatefulRobot} to the
 * <i>center</i> of the {@link FootballField} that contains
 * a single {@link Ball} for the bot to find.
 */
public class BallGame {
    /**
     * The interface to the <b>Run...</b> menu item in Eclipse that
     * does the work of connecting the robot to the field, and then
     * calls the {@link Field#runGame()} method to start the fun.
     * @param args These are ignored
     */
    public static void main(String[] args) {
        Field field = new FootballField("Play Ball!");
        Coordinate center = field.centerField();
        Robot robot = new StatefulRobot(center.x, center.y, 0);

        field.addRobot(robot);
        field.runGame();
    }
}
