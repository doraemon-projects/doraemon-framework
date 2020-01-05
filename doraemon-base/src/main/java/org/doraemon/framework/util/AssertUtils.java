package org.doraemon.framework.util;

import org.doraemon.framework.exception.BusinessException;
import org.doraemon.framework.exception.ExceptionCode;

/**
 * @Package : com.jfteam.exception
 * @Description :
 * @Author : yuanDong.lin
 * @Date : 2019-08-27 14:38
 */
public class AssertUtils {

    public static void notNull(Object obj) {
        notNull(obj, ExceptionCode.RECORDS_NOT_EXIST.getMsg());
    }

    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new BusinessException(ExceptionCode.RECORDS_NOT_EXIST.getCode(), message);
        }
    }

    public static void isNull(Object obj) {
        isNull(obj, ExceptionCode.RECORDS_EXIST.getMsg());
    }

    public static void isNull(Object obj, String message) {
        if (obj != null) {
            throw new BusinessException(ExceptionCode.RECORDS_EXIST.getCode(), message);
        }
    }

}
