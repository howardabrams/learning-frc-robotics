/*
 * Example `game` that accompanies the lesson chapter:
 *     http://geeklet.org/java///020-java-basics-1/
 */

import org.geeklet.machines.support.Field;
import org.geeklet.machines.support.ArenaField;

/**
 * The simplest game that adds the {@link MyBot} robot to the
 * {@link ArenaField} field.
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
        Field field = new ArenaField("My Fun Game");

        // REPLACE THIS LINE TO ADD YOUR ROBOT HERE!

        field.runGame();
    }
}
