/**
 * 
 */
package org.geeklet.machines.support.sensors;

import java.awt.Point;
import java.awt.Rectangle;

import org.geeklet.machines.support.DrawableRobot;
import org.geeklet.machines.support.Field;
import org.geeklet.machines.support.IRobot;
import org.geeklet.machines.support.objects.FieldObject;
import org.geeklet.machines.support.objects.IFieldObject;

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
		int x = attachedTo.getX();
		int y = attachedTo.getY();
		int d = attachedTo.getDirection() + direction;
		double rads = Math.toRadians(d);

		for (FieldObject o : playingField.getFieldObjects()) {
			for (int a = 0; a < RANGE; a++) {
				int vx = (int) (x + Math.cos(rads) * a);
				int vy = (int) (y + Math.sin(rads) * a);
				if (isIn(vx, vy, o.x, o.y, o.width, o.height)) {
					return true;
				}
			}
		}
		return false;
	}

	boolean isIn(int vx, int vy, int x, int y, int width, int height) {
		final Rectangle o = new Rectangle(x, y, width, height);
		return o.contains(vx, vy);
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
