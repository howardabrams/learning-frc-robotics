/**
 * 
 */
package org.geeklet.machines.support.sensors;

import org.geeklet.machines.support.DrawableRobot;
import org.geeklet.machines.support.Field;
import org.geeklet.machines.support.IRobot;

/**
 * @author howard.abrams
 *
 */
public class UltraSonic extends DirectionalSensor {
	int RANGE = 20;
	Field playingField;
	IRobot attachedTo;
	
	/**
	 * 
	 */
	public UltraSonic(int direction) {
		this.direction = direction;
	}

	/* (non-Javadoc)
	 * @see org.geeklet.machines.support.sensors.iSensor#triggered()
	 */
	@Override
	public boolean triggered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addField(Field field) {
		playingField = field;
	}

	@Override
	public void addRobot(IRobot robot) {
		attachedTo = robot;
	}

}
