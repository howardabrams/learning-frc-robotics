/*
 * Example `game` that accompanies the lesson chapter:
 *     docs/20-learning-java.md
 */
package org.geeklet.machines;

import org.geeklet.machines.support.Field;
import org.geeklet.machines.support.FootballField;
import org.geeklet.machines.support.Robot;

/**
 * The simplest game that adds the {@link MyBot} robot to the
 * {@link FootballField} field.
 */
public class MyGame {
    /**
     * The interface to the <b>Run...</b> menu item in Eclipse that
     * does the work of connecting the robot to the field, and then
     * calls the {@link Field#runGame()} method to start the fun.
     * @param args These are ignored
     */
    public static void main(String[] args) {
        // The field takes a string that is used for the Window title.
        Field field = new FootballField("My Fun Game");

        // Create and place the robot on the "left" side of the field
        Robot myKit = new MyBot(10, 200, 0);

        field.addRobot(myKit);

        field.runGame();
    }
}
