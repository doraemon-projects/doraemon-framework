package org.doraemon.framework.util;

import org.doraemon.framework.exception.ApplicationRuntimeException;
import org.doraemon.framework.response.ResultCode;

/**
 * @description: 异常工具类
 * @author: fengwenping
 * @date: 2020-07-11 17:52
 */
public abstract class ExceptionUtils {

    public static void throwNewException(Throwable throwable) {
        throwNewException(throwable.getMessage());
    }

    public static void throwNewException() {
        throwNewException(ResultCode.CUSTOM_ERROR.getName());
    }

    public static void throwNewException(String message) {
        throw new ApplicationRuntimeException(ResultCode.CUSTOM_ERROR.getCode(), message);
    }
}
