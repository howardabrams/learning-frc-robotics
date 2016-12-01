package org.geeklet.machines;

import java.awt.Graphics;

/**
 * The behavior (or interface) that all robots who want to play on the field must adhere.
 */
public interface IRobot {
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);
    int getDirection();
    void setDirection(int d);
    
    void go();
    
    void paint(Graphics g);
}
