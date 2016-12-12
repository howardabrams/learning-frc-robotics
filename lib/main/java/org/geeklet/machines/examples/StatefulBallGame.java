/*
 * Accompanies the second lesson on finite state machines.
 */
package org.geeklet.machines.examples;

import org.geeklet.machines.support.Field;
import org.geeklet.machines.support.FootballField;
import org.geeklet.machines.support.Robot;
import org.geeklet.machines.support.objects.Ball;

/**
 * A game that adds the {@link StatefulRobot} robot to <i>center</i>
 * of the {@link FootballField} field that contains walls, and a
 * randomly place {@link Ball}.  It calls the {@link Field#runGame()}
 * method to start the fun.
 */
public class StatefulBallGame {
    /**
     * The interface to the <b>Run...</b> menu item in Eclipse that
     * does the work of connecting the robot to the field, and then
     * calls the {@link Field#runGame()} method to start the fun.
     * @param args These are ignored
     */
    public static void main(String[] args) {
        Field field = new FootballField("Play Ball!", 500, 500);
        Robot robbie = new StatefulRobot(field.center().x, field.center().y, 0);

        field.addRobot(robbie);
        field.runGame();
    }
}
