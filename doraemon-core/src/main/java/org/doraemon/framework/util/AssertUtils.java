package org.doraemon.framework.util;

import org.doraemon.framework.Constants;
import org.doraemon.framework.exception.ApplicationException;

/**
 * @author yuandong.lin
 */
public class AssertUtils {

    public static void notNull(Object obj) {
        notNull(obj, Constants.ExceptionCode.RECORD_NOT_EXIST.getCode().toString(), Constants.ExceptionCode.RECORD_NOT_EXIST.getName());
    }

    public static void notNull(Object obj, String code,String message) {
        if (obj == null) {
            throw new ApplicationException(code, message);
        }
    }

    public static void isNull(Object obj) {
        isNull(obj, Constants.ExceptionCode.RECORD_EXIST.getCode().toString(), Constants.ExceptionCode.RECORD_EXIST.getName());
    }

    public static void isNull(Object obj, String code, String message) {
        if (obj != null) {
            throw new ApplicationException(code, message);
        }
    }

    public static void isBlank(String expression) {
        isBlank(expression,Constants.ExceptionCode.STRING_IS_BLANK.getCode().toString(), Constants.ExceptionCode.STRING_IS_BLANK.getName());
    }

    public static void isBlank(String expression, String code, String message) {
        if (expression == null || "".equalsIgnoreCase(expression.trim())) {
            throw new ApplicationException(code, message);
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression,Constants.ExceptionCode.RESULT_IS_FALSE.getCode().toString(), Constants.ExceptionCode.RESULT_IS_FALSE.getName());
    }

    public static void isTrue(boolean expression, String code, String message) {
        if (!expression) {
            throw new ApplicationException(code, message);
        }
    }
}
