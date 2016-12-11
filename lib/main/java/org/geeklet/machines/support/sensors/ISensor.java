/**
 * 
 */
package org.geeklet.machines.support.sensors;

import org.geeklet.machines.support.IRobot;
import org.geeklet.machines.support.Field;

/**
 *
 */
public interface ISensor {
	boolean triggered ();

	void addField(Field field);

	void addRobot(IRobot robot);
}
