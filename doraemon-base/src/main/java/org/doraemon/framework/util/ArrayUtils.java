package org.doraemon.framework.util;

import java.lang.reflect.Array;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2019/12/15 23:08
 */
public abstract class ArrayUtils {

    public static int getLength(final Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }

    public static boolean isEmpty(final Object[] array) {
        return getLength(array) == 0;
    }
}
