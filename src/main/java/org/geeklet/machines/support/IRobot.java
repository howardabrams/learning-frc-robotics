package org.geeklet.machines.support;

/**
 * The behavior (or interface) that all robots who want to play on the field must adhere.
 */
public interface IRobot {
    int getX();
    int getY();
    int getDirection();
    
    void step();
}
