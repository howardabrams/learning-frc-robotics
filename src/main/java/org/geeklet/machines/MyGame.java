/**
 * 
 */
package org.geeklet.machines;

import org.geeklet.machines.support.Field;
import org.geeklet.machines.support.FootballField;
import org.geeklet.machines.support.Robot;

/**
 *
 */
public class MyGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Robot myKit = new SimpleRobot(42, 100, 0);
		Field field = new FootballField("My Fun Game");
		
		field.addRobot(myKit);
		field.runGame();
	}

}
