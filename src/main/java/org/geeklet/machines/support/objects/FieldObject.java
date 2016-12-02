package org.geeklet.machines.support.objects;

public abstract class FieldObject implements IFieldObject {
	int x;
	int y;
	int width;
	int height;

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
