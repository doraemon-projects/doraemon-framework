package org.doraemon.framework.exception;

import org.doraemon.framework.Constants;

import java.util.Objects;

/**
 * @description: 断言判断, 相当于Spring中的Assert
 * @author: fengwenping
 * @date: 2020-07-04 15:49
 */
public abstract class BusinessException {

    private BusinessException() {
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            failure(message);
        }
    }

    public static void assertTrue(boolean condition) {
        assertTrue(condition, null);
    }

    public static void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }

    public static void assertFalse(boolean condition) {
        assertFalse(condition, null);
    }

    public static void failure(String message) {
        if (message == null) {
            BusinessException.newException();
        }
        BusinessException.newException(message);
    }

    public static void failure() {
        failure(null);
    }

    public static void assertEquals(Object expected, Object actual) {
        if (!Objects.equals(expected, actual)) {
            BusinessException.newException();
        }
    }

    public static void assertEquals(Object expected, Object actual, String message) {
        if (!Objects.equals(expected, actual)) {
            BusinessException.newException(message);
        }
    }

    public static void newException() {
        throw new ApplicationRuntimeException(Constants.ResultCode.EXP_CUSTOM_ERROR);
    }

    public static void newException(String message) {
        throw new ApplicationRuntimeException(Constants.ResultCode.EXP_CUSTOM_ERROR.getErrorCode(), message);
    }
}
