package org.doraemon.framework.base;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Description: 枚举类型,所有枚举类型都要实现当前接口
 * Author:      fengwenping
 * Date:        2019/12/15 20:04
 */
public interface EnumProvider<K> {

    /**
     * 枚举code
     *
     * @return
     */
    K getCode();

    /**
     * 枚举名称
     *
     * @return
     */
    String getName();

    /**
     * 枚举描述
     *
     * @return
     */
    String getDesc();

    default String info() {
        return String.format("{\"className:\":\"%s\",\"code\":\"%s\",\"name\":\"%s\",\"desc\":\"%s\"}",
                this.getClass().getName(),
                this.getCode(),
                this.getName(),
                this.getDesc());
    }

    static <T extends EnumProvider<K>, K> T fromValue(Class<T> enumType, K value) {
        final T[] enumConstants = enumType.getEnumConstants();
        for (T t : enumConstants) {
            if (Objects.equals(t.getCode(), value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("no enum value " + value + " of " + enumType.getCanonicalName());
    }
}
