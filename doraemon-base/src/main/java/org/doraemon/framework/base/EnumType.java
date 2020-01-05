package org.doraemon.framework.base;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2019/12/15 20:04
 */
public interface EnumType<K> {

    K getCode();

    String getName();

    String getDesc();

    default String info() {
        return String.format("{\"code\":\"%s\",\"name\":\"%s\",\"desc\":\"%s\"}", this.getCode(), this.getName(), this.getDesc());
    }

    static <T extends EnumType<K>, K> T fromValue(Class<T> enumType, K value) {
        final T[] enumConstants = enumType.getEnumConstants();
        for (T t : enumConstants) {
            if (Objects.equals(t.getCode(), value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("no enum value " + value + " of " + enumType.getCanonicalName());
    }
}
