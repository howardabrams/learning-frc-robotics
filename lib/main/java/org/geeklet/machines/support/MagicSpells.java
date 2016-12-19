package org.geeklet.machines.support;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
     * Set a private field in a {@link DrawableRobot} to a particular value
     * @param robot     instance of a class that has a field of the given
     *                  name, probably {@link Robot}
     * @param fieldname a field name, e.g. "x", "y" or "direction" of
     *                  either type <code>int</code> or <code>float</code>.
     * @param value     a numeric number
     */
    public static void setMagicNumber(Object robot, String fieldname, float value) {
        Class<? extends Object> r = robot.getClass();
        Field f;
        try {
            f = r.getField(fieldname);
        } catch (NoSuchFieldException e) {
            try {
                f = r.getDeclaredField(fieldname);
            } catch (Exception e1) {
                magicNumberFieldError(fieldname);
                return;
            }
        } catch (Exception e) {
            magicNumberFieldError(fieldname);
            return;
        }
        f.setAccessible(true);

        try {
            f.setFloat(robot, value);
        } catch (Exception e) {
            try {
                f.setInt(robot, (int) value);
            } catch (IllegalArgumentException | IllegalAccessException e1) {
                magicNumberFieldError(fieldname);
            }
        }
    }

    /**
     * Given an <i>robot-like</i> object this create a {@link Robot}
     * that connects to the given object's field variables ... in other
     * words, the returned instance is a wrapper around it.
     *
     * @param robot Instance of any class that implements three variable fields,
     * <code>x</code>, <code>y</code> and <code>direction</code>
     * @return a <i>Frankenstein-like</i> Robot that
     * <i>wraps</i> the given object, <var>robot</var>
     */
    public static Robot createFrankieRobot(Object robot) {
        // Let's just make sure that the object given to us
        // can be a 'good enough' robot, but verifying they
        // have all the correct field variables defined:
        final List<String> names =
            Arrays.asList(new String[] {"x", "y", "direction"});
        for (String fieldname : names) {

            final Stream<Field> f1 =
                Arrays.stream(robot.getClass().getFields());
            final Stream<Field> f2 =
                Arrays.stream(robot.getClass().getDeclaredFields());
            final Stream<Field> f = Stream.concat(f1, f2);

            if (!f.anyMatch(s -> s.getName() == fieldname)) {
                String msg = String.format("Your robot must have a variable field, '%s', in order to be shown on a field.", fieldname);
                throw new RuntimeException(msg);
            }
        }

        // We assume the robot is "good enough", so we create a
        // Wrapper Robot that can implement the Java interfaces.
        final Robot robotWrap = new Robot() {
                public float getX() {
                    return MagicSpells.getMagicFloat(robot, "x");
                }
                public float getY() {
                    return MagicSpells.getMagicFloat(robot, "y");
                }
                public int getDirection() {
                    return MagicSpells.getMagicInteger(robot, "direction");
                }
                public void step() {    }
            };
        return robotWrap;
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
