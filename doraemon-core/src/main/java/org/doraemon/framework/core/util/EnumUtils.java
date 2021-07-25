package org.doraemon.framework.core.util;

import org.doraemon.framework.core.enums.IEnumProvider;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 枚举类型工具类
 * Author:      fengwenping
 * Date:        2019/12/15 23:10
 */
public abstract class EnumUtils {

    public static <T extends IEnumProvider<K>, K> T fromValue(Class<T> enumType, K value) {
        final T[] enumConstants = enumType.getEnumConstants();
        for (T t : enumConstants) {
            if (Objects.equals(t.getCode(), value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("no enum value " + value + " of " + enumType.getCanonicalName());
    }

    public static <E extends Enum<E>> Map<String, E> getEnumMap(final Class<E> enumClass) {
        final Map<String, E> map = new LinkedHashMap<>();
        for (final E e : enumClass.getEnumConstants()) {
            map.put(e.name(), e);
        }
        return map;
    }

    public static <E extends Enum<E>> List<E> getEnumList(final Class<E> enumClass) {
        return new ArrayList<>(Arrays.asList(enumClass.getEnumConstants()));
    }

    public static <E extends Enum<E>> E getEnum(final Class<E> enumClass, final String enumName) {
        if (enumName == null) {
            return null;
        }
        try {
            return Enum.valueOf(enumClass, enumName);
        } catch (final IllegalArgumentException ex) {
            return null;
        }
    }
}
