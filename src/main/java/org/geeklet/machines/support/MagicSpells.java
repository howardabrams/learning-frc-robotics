package org.geeklet.machines.support;

import java.lang.reflect.Field;

/**
 * Contains static helper functions that should not be viewed.
 */
public class MagicSpells {

    public static int getMagicInteger(Robot robot, String name) {
        try {
            Class<? extends Robot> r = robot.getClass();
            Field f = r.getDeclaredField(name);
            f.setAccessible(true);
            return f.getInt(robot);
        } catch (NoSuchFieldException e) {
            System.err.println(String.format("You need to create a field called {0} as a type of 'int'", name));
            return 0;
        } catch (SecurityException e) {
            System.err.println(String.format("You need to create a field called {0} as a type of 'int'", name));
            return 0;
        } catch (IllegalAccessException e) {
            System.err.println(String.format("You need to create a field called {0} as a type of 'int'", name));
            return 0;
        }
    }

    public static float getMagicFloat(Robot robot, String name) {
        try {
            Class<? extends Robot> r = robot.getClass();
            Field f = r.getDeclaredField(name);
            f.setAccessible(true);
            return f.getFloat(robot);
        } catch (NoSuchFieldException e) {
            System.err.println(String.format("You need to create a field called {0} as a type of 'float'", name));
            // e.printStackTrace();
            return 0;
        } catch (SecurityException e) {
            System.err.println(String.format("You need to create a field called {0} as a type of 'float'", name));
            return 0;
        } catch (IllegalArgumentException e) {
            return getMagicInteger(robot, name);
        } catch (IllegalAccessException e) {
            System.err.println(String.format("You need to create a field called {0} as a type of 'float'", name));
            return 0;
        }
    }

    /**
     * Simplified random number generator with a limit.
     * 
     * @param limit
     *            Maximum value
     * @return a random number from 0 to limit
     */
    static int randomInt(int limit) {
        return (int) Math.round(Math.random() * limit);
    }
}
