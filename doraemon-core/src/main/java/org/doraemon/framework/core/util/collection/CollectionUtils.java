package org.doraemon.framework.core.util.collection;

import java.util.Collection;
import java.util.Map;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-07-25 14:57
 */
public abstract class CollectionUtils {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
