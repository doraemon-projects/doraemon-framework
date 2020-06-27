package org.doraemon.framework.message;

import org.doraemon.framework.base.IEnumProvider;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2020/1/12 1:51
 */
public enum MessageStatus implements IEnumProvider<Integer> {
    SUCCESS(10, "成功"),
    LATER(20, "延迟"),
    FAILURE(30, "失败");

    MessageStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.info();
    }
}
