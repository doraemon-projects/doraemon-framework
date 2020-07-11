package org.doraemon.framework.util;

import org.doraemon.framework.exception.ApplicationRuntimeException;
import org.doraemon.framework.response.ResultCode;

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
            throwNewException();
        }
        throwNewException(message);
    }

    public static void throwNewException(Throwable throwable) {
        throwNewException(throwable.getMessage());
    }

    public static void throwNewException() {
        throw new ApplicationRuntimeException(ResultCode.CUSTOM_ERROR);
    }

    public static void throwNewException(String message) {
        throw new ApplicationRuntimeException(ResultCode.CUSTOM_ERROR, message);
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

    public static void assertEquals(Object expected, Object actual) {
        assertTrue(Objects.equals(expected, actual));
    }

    public static void assertEquals(Object expected, Object actual, String message) {
        assertTrue(Objects.equals(expected, actual), message);
    }

    public static void assertNotNull(Object actual, String message) {
        assertTrue(Objects.equals(actual, null), message);
    }

    public static void assertNotNull(Object actual) {
        assertTrue(Objects.equals(actual, null));
    }

    public static void assertNull(Object actual, String message) {
        assertFalse(Objects.equals(actual, null), message);
    }

    public static void assertNull(Object actual) {
        assertFalse(Objects.equals(actual, null));
    }

    public static void assertNotEmpty(Collection actual) {
        assertNotEmpty(actual, null);
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
        assertNotEmpty(actual, null);
    }

    public static void assertNotEmpty(Object[] actual, String message) {
        if (actual == null || actual.length == 0) {
            failure(message);
        }
    }

    public static void assertInstanceOf(Class clazz, Object actual) {
        assertInstanceOf(clazz, actual);
    }

    public static void assertInstanceOf(Object actual, Class clazz, String message) {
        if (clazz.isInstance(actual)) {
            failure(message);
        }
    }

    public static void assertAssignableFrom(Class subType, Class superType) {
        assertAssignableFrom(subType, superType, null);
    }

    public static void assertAssignableFrom(Class subType, Class superType, String message) {
        if (subType.isAssignableFrom(superType)) {
            failure(message);
        }
    }
}
