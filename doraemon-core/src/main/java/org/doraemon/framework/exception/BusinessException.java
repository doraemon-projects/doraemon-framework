package org.doraemon.framework.exception;

import org.doraemon.framework.Constants;
import org.doraemon.framework.base.BaseCode;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 断言判断, 相当于Spring中的Assert
 * @author: fengwenping
 * @date: 2020-07-04 15:49
 */
public abstract class BusinessException {

    private BusinessException() {
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
        throw new ApplicationRuntimeException(Constants.ResultCode.EXP_CUSTOM_ERROR);
    }

    public static void throwNewException(String message) {
        throw new ApplicationRuntimeException(new ExceptionCode(new BaseCode(Constants.ResultCode.CUSTOM_ERROR.getCode(), message)));
    }

    public static void isTrue(boolean actual, String message) {
        if (!actual) {
            failure(message);
        }
    }

    public static void isTrue(boolean actual) {
        isTrue(actual, null);
    }

    public static void isFalse(boolean actual, String message) {
        isTrue(!actual, message);
    }

    public static void isFalse(boolean actual) {
        isFalse(actual, null);
    }

    public static void isEquals(Object expected, Object actual) {
        isTrue(Objects.equals(expected, actual));
    }

    public static void isEquals(Object expected, Object actual, String message) {
        isTrue(Objects.equals(expected, actual), message);
    }

    public static void isNotNull(Object actual, String message) {
        isTrue(Objects.equals(actual, null), message);
    }

    public static void isNotNull(Object actual) {
        isTrue(Objects.equals(actual, null));
    }

    public static void isNull(Object actual, String message) {
        isFalse(Objects.equals(actual, null), message);
    }

    public static void isNull(Object actual) {
        isFalse(Objects.equals(actual, null));
    }

    public static void isNotEmpty(Collection actual) {
        isNotEmpty(actual, null);
    }

    public static void isNotEmpty(Collection actual, String message) {
        if (actual == null || actual.isEmpty()) {
            failure(message);
        }
    }

    public static void isNotEmpty(Map actual) {
        isNotEmpty(actual, null);
    }

    public static void isNotEmpty(Map actual, String message) {
        if (actual == null || actual.isEmpty()) {
            failure(message);
        }
    }

    public static void isNotEmpty(Object[] actual) {
        isNotEmpty(actual, null);
    }

    public static void isNotEmpty(Object[] actual, String message) {
        if (actual == null || actual.length == 0) {
            failure(message);
        }
    }

    public static void isInstanceOf(Class clazz, Object actual) {
        isInstanceOf(clazz, actual);
    }

    public static void isInstanceOf(Object actual, Class clazz, String message) {
        if (clazz.isInstance(actual)) {
            failure(message);
        }
    }

    public static void isAssignableFrom(Class subType, Class superType) {
        isAssignableFrom(subType, superType, null);
    }

    public static void isAssignableFrom(Class subType, Class superType, String message) {
        if (subType.isAssignableFrom(superType)) {
            failure(message);
        }
    }
}
