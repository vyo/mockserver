package org.mockserver.matchers;

/**
 * @author jamesdbloom
 */
public class PropertiesMatched {

    private static ThreadLocal<Integer> propertiesMatched = new ThreadLocal<Integer>();

    public static void reset() {
        propertiesMatched.set(0);
    }

    public static void increment() {
        Integer integer = propertiesMatched.get();
        if (integer == null) {
            propertiesMatched.set(1);
        } else {
            propertiesMatched.set(integer + 1);
        }
    }

    public static int count() {
        return propertiesMatched.get();
    }
}
