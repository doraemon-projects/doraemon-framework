package org.doraemon.framework.core.enums;

/**
 * Created with IntelliJ IDEA.
 * Description: 枚举类型,所有枚举类型都要实现当前接口
 * Author:      fengwenping
 * Date:        2019/12/15 20:04
 */
public interface IEnumProvider<K> {

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

    default String info() {
        return String.format("{\"className:\":\"%s\",\"code\":\"%s\",\"name\":\"%s\"}",
                this.getClass().getName(),
                this.getCode(),
                this.getName());
    }
}
