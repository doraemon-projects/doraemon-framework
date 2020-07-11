package org.doraemon.framework.util;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 断言判断, 相当于Spring中的Assert
 * @author: fengwenping
 * @date: 2020-07-04 15:49
 */
public abstract class AssertUtils {

    private AssertUtils() {
    }

    private static void failure(String message) {
        if (message == null) {
            ExceptionUtils.throwNewException();
        }
        ExceptionUtils.throwNewException(message);
    }

    public static void assertTrue(boolean actual, String message) {
        if (!actual) {
            failure(message);
        }
    }

    public static void assertTrue(boolean actual) {
        assertTrue(actual, null);
    }

    public static void assertFalse(boolean actual, String message) {
        assertTrue(!actual, message);
    }

    public static void assertFalse(boolean actual) {
        assertFalse(actual, null);
    }

    public static void assertEquals(Object expected, Object actual, String message) {
        assertTrue(Objects.equals(expected, actual), message);
    }

    public static void assertEquals(Object expected, Object actual) {
        assertEquals(expected, actual, "expected and actual must be equals");
    }

    public static void assertNotNull(Object actual, String message) {
        assertFalse(Objects.equals(actual, null), message);
    }

    public static void assertNotNull(Object actual) {
        assertNotNull(actual, "param must be not null");
    }

    public static void assertNull(Object actual, String message) {
        assertTrue(Objects.equals(actual, null), message);
    }

    public static void assertNull(Object actual) {
        assertNull(actual, "param must be null");
    }

    public static void assertNotEmpty(Collection actual) {
        assertNotEmpty(actual, "param must be not null or empty");
    }

    public static void assertNotEmpty(Collection actual, String message) {
        if (actual == null || actual.isEmpty()) {
            failure(message);
        }
    }

    public static void assertNotEmpty(Map actual) {
        assertNotEmpty(actual, null);
    }

    public static void assertNotEmpty(Map actual, String message) {
        if (actual == null || actual.isEmpty()) {
            failure(message);
        }
    }

    public static void assertNotEmpty(Object[] actual) {
        assertNotEmpty(actual, "param must be not null or size is not zero");
    }

    public static void assertNotEmpty(Object[] actual, String message) {
        if (actual == null || actual.length == 0) {
            failure(message);
        }
    }

    public static void assertInstanceOf(Object actual, Class clazz) {
        assertInstanceOf(actual, clazz, "actual must instants of clazz");
    }

    public static void assertInstanceOf(Object actual, Class clazz, String message) {
        if (!clazz.isInstance(actual)) {
            failure(message);
        }
    }

    public static void assertAssignableFrom(Class superType, Class subType) {
        assertAssignableFrom(superType, subType, "superType must be subType's interface or parent class");
    }

    public static void assertAssignableFrom(Class superType, Class subType, String message) {
        if (!superType.isAssignableFrom(subType)) {
            failure(message);
        }
    }

    public static void assertNotZero(int actual) {
        assertFalse(actual == 0, "param must be not zero");
    }

    public static void assertNotZero(long actual) {
        assertFalse(actual == 0, "param must be not zero");
    }
}
