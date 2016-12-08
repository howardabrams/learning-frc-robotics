package org.geeklet.machines.support;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.geeklet.machines.support.sensors.ISensor;

/**
 * Contains static helper functions that should not be viewed.
 */
public class MagicSpells {
    /**
     * Prints out an nice error message when attempting to access an numeric field.
     * @param name The name of the field to be accessed
     * @return 0 ... always.
     */
    private static int magicNumberFieldError(String name) {
        System.err.println(String.format("You need to create a field called '%s' as a type of 'int' or 'float'.", name));
        return 0;
    }

    /**
     * Since the robot library can only access fields <i>through</i> a
     * method, we use Java Reflection to search for a field of a given
     * name on a robot object. Yeah, we really need to catch lots of
     * exceptions in order to support both the library as well test
     * cases.
     * <p>
     * Speaking of which, see {@link RobotTest} for test cases that
     * use this function.
     *
     * @param robot a class, probably of type {@link Robot}, but
     * technically, doesn't have to be.
     * @param fieldname  a field name, e.g. "x" or "direction"
     * @return The value of the field that is in the class, as an integer.
     */
    public static int getMagicInteger(Object robot, String fieldname) {
        Class<? extends Object> r = robot.getClass();
        Field f;
        try {
            f = r.getField(fieldname);
        } catch (NoSuchFieldException e) {
            try {
                f = r.getDeclaredField(fieldname);
            } catch (Exception e1) {
                return magicNumberFieldError(fieldname);
            }
        } catch (Exception e) {
            return magicNumberFieldError(fieldname);
        }

        f.setAccessible(true);
        try {
            return f.getInt(robot);
        } catch (Exception e) {
            return magicNumberFieldError(fieldname);
        }
    }

    /**
     * Since the robot library can only access fields <i>through</i> a
     * method, we use Java Reflection to search for a field of a given
     * name on a robot object. If we can't find a float with the field
     * name, we then call {@link #getMagicInteger(Object, String)} to
     * see if the field name is an integer, and return that.
     *
     * @param robot a class, probably of type {@link Robot}, but
     * technically, doesn't have to be.
     * @param field  a field name, e.g. "x" or "direction"
     * @return The value of the field that is in the class, as a float.
     * @see RobotTest
     */
    public static float getMagicFloat(Object robot, String fieldname) {
        Class<? extends Object> r = robot.getClass();
        Field f;
        try {
            f = r.getField(fieldname);
        } catch (NoSuchFieldException e) {
            try {
                f = r.getDeclaredField(fieldname);
            } catch (Exception e1) {
                return getMagicInteger(robot, fieldname);
            }
        } catch (Exception e) {
            return magicNumberFieldError(fieldname);
        }

        f.setAccessible(true);

        try {
            return f.getFloat(robot);
        } catch (Exception e) {
            return magicNumberFieldError(fieldname);
        }
    }

    /**
     * @param clazz
     *            A sensor class to search, e.g. <code>UltraSonic.class</code>
     * @return a list of all sensors of a particular type (class).
     */
    public List<ISensor> getSensors(Class clazz, List<ISensor> sensors) {
        List<ISensor> result = new ArrayList<ISensor>();
        for (ISensor s : sensors) {
            if (s.getClass().isAssignableFrom(clazz))
                result.add(s);
        }
        return result;
    }

    /**
     * @param clazz
     *            A sensor class to search, e.g. <code>UltraSonic.class</code>
     * @return the first sensor found of a particular type (class). This is
     *         really only useful when a single type of sensor has been
     *         attached.
     */
    public ISensor getSensor(Class clazz, List<ISensor> sensors) {
        List<ISensor> allOfType = getSensors(clazz, sensors);
        if (allOfType.isEmpty())
            return null;
        else
            return allOfType.get(0);
    }

    /**
     * Simplified random number generator with a limit.
     *
     * @param limit Maximum value
     * @return a random number from 0 to limit
     */
    static int randomInt(int limit) {
        return (int) Math.round(Math.random() * limit);
    }

    /**
     * Simplified random number generator with a limit.
     *
     * @param lowerLimit Lowest possible value, inclusive. In other
     * words, if you set this to 5, you might get 5 (but nothing
     * less).
     *
     * @param upperLimit Maximum value, inclusive. In other words, if
     * this is set to 10, you might get 10.
     *
     * @return a random number from 0 to limit
     */
    static int randomInt(int lowerlimit, int upperlimit) {
        return (int) Math.round(Math.random() * (upperlimit-lowerlimit)) + lowerlimit;
    }
}
