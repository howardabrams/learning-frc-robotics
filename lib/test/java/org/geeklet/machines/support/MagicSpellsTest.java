/*
 * Test the static functions in {@link MagicSpells}.
 */
package org.geeklet.machines.support;

import junit.framework.TestCase;

/**
 * Junit Test Suite for the {@link MagicSpells}.  <b>Note:</b> The
 * {@link MagicSpells#getMagicInteger(Object, String)} that is the
 * basis for the {@link Robot#getX()} method, is tested in the {@link
 * RobotTest} test suite.
 */
public class MagicSpellsTest extends TestCase {
    private static final int LOWEST_LIMIT = 0;
    private static final int LOWER_LIMIT = 5;
    private static final int UPPER_LIMIT = 10;

    /**
     * Test method for {@link org.geeklet.machines.support.MagicSpells#randomInt(int)}.
     */
    public void testRandomInt() {
        int lowest = UPPER_LIMIT / 2;
        int highest = UPPER_LIMIT / 2;
        for (int t = 0; t < 1000; t++) {
            int num = MagicSpells.randomInt(UPPER_LIMIT);
            if (num < lowest)
                lowest = num;
            if (num > highest)
                highest = num;
            assertTrue(num >= LOWEST_LIMIT && num <= UPPER_LIMIT);
        }
        assertEquals(LOWEST_LIMIT, lowest);
        assertEquals(UPPER_LIMIT, highest);
    }

    /**
     * Test method for {@link org.geeklet.machines.support.MagicSpells#randomInt(int, int)}.
     */
    public void testRandomIntBothLimits() {
        // Set up values around 7.5 to make sure we get something lower
        int lowest = (UPPER_LIMIT - LOWER_LIMIT) / 2 + LOWER_LIMIT;
        int highest = lowest;

        for (int t = 0; t < 1000; t++) {
            int num = MagicSpells.randomInt(LOWER_LIMIT, UPPER_LIMIT);
            if (num < lowest)
                lowest = num;
            if (num > highest)
                highest = num;
            assertTrue(num >= LOWER_LIMIT && num <= UPPER_LIMIT);
        }
        assertEquals(LOWER_LIMIT, lowest);
        assertEquals(UPPER_LIMIT, highest);
    }
}
