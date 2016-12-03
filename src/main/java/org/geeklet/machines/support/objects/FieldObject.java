package org.geeklet.machines.support.objects;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;

/**
 * Each object on the field needs to occupy an area on the field
 * defined by one or more rectangles.
 */
public abstract class FieldObject implements IFieldObject {
    /**
     * An object is defined by one or more rectangles represented
     * as a list of rectangles. The constructor will guarantee
     * that we have at least one rectangle in this list.
     */
    final Area area;

    /**
     * Constructor defines a single area represented by a width
     * and height at a specific location on a field given by the
     * x,y coordinates.
     * @param x      The X coordinate on the field, where 0,0 is upper left
     * @param y      The Y coordinate on the field
     * @param width  The width of the object in pixels
     * @param height The height of the object
     */
    public FieldObject(int x, int y, int width, int height) {
        area = new Area(new Rectangle(x, y, width, height));
    }

    /**
     * Constructor defines entire object shape as a collection of
     * {@link Rectangle}
     * @param r one or more Rectangle objects.
     * @see #addArea(Rectangle)
     */
    public FieldObject(Rectangle... rects) {
        area = new Area();
        for (Rectangle r : rects) {
            addArea(r);
        }
    }

    /**
     * Constructor defines entire object shape as a collection of
     * {@link Area} areas.
     * @param areas one or more Areas objects.
     * @see #addArea(Area)
     */
    public FieldObject(Area... areas) {
        area = new Area();
        for (Area a : areas) {
            addArea(a);
        }
    }

    /**
     * Increases the size of object by adding another (potentially
     * overlapping) rectangle to the list of rectangles that define
     * its area.
     * @param r
     */
    public void addArea(Rectangle r) {
        area.add(new Area(r));
    }

    /**
     * Increases the size of object by adding another (potentially
     * overlapping) area to the list of areal sections that define its
     * complete area.
     * @param a An area that either either wrapped with a {@link
     * Rectangle}, {@link Ellipse} or {@link Polygon}.
     */
    public void addArea(Area a) {
        area.add(a);
    }

    /**
     * Decreases the size of the field object by removing the given area.
     * @param a
     */
    public void subtractArea(Area a) {
        area.subtract(a);
    }

    /**
     * Increases the size of object by adding another (potentially
     * overlapping) area to the list of rectangular shapes that define
     * its area.
     *
     * @param x      The X coordinate on the field, where 0,0 is upper left
     * @param y      The Y coordinate on the field
     * @param width  The width of the object in pixels
     * @param height The height of the object
     */
    public void addArea(int x, int y, int width, int height) {
        addArea(new Rectangle(x, y, width, height));
    }

    /**
     * Predicate function that determines if a point is
     * <i>contained</i> in this field object's area
     * @param p a point on the field
     * @return <code>true</code> if the point is contained in one of
     * the rectangular areas.
     */
    public boolean inside(Point p) {
        return area.contains(p);
    }

    /**
     * Predicate function that determines if a point is
     * <i>contained</i> in this field object's area
     * @param x      The X coordinate on the field, where 0,0 is upper left
     * @param y      The Y coordinate on the field
     * @return <code>true</code> if the point is contained in one of
     * the rectangular areas.
     */
    public boolean inside(int x, int y) {
        return area.contains(x, y);
    }

    /**
     * @return <code>false</code> as all field objects, by default,
     * are immovable.
     */
    @Override
    public boolean movable() {
        return false;
    }
}
