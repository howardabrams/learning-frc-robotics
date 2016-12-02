package org.geeklet.machines.support.objects;

public abstract class FieldObject implements IFieldObject {
	public int x;
	public int y;
	public int width;
	public int height;

	public FieldObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean movable() {
		return false;
	}

}
