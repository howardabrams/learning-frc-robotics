/*
 * Example `game` that accompanies the lesson chapter:
 *     docs/25-more-java.md
 */
package org.geeklet.machines;

import org.geeklet.machines.support.Coordinate;
import org.geeklet.machines.support.Field;
import org.geeklet.machines.support.FootballField;
import org.geeklet.machines.support.Robot;

/**
 * The simplest game that adds the {@link SimpleRobot} robot to
 * <i>center</i> of the {@link FootballField} field, and calls the
 * {@link Field#runGame()} method to start the fun.
 */
public class SimpleGame {
    /**
     * The interface to the <b>Run...</b> menu item in Eclipse that
     * does the work of connecting the robot to the field, and then
     * calls the {@link Field#runGame()} method to start the fun.
     * @param args These are ignored
     */
    public static void main(String[] args) {
        Field field = new FootballField("A Simple Game");
        Coordinate center = field.centerField();
        Robot myKit = new SimpleRobot(center.x, center.y, 0);

        field.addRobot(myKit);
        field.runGame();
    }

}
