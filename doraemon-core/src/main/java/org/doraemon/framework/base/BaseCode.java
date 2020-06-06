package org.doraemon.framework.base;

/**
 * @description: 基本Code
 * @author: fengwenping
 * @date: 2020-05-16 18:09
 */
public class BaseCode implements IEnumProvider<Integer> {

    protected Integer code;
    protected String name;

    public BaseCode(Integer code) {
        this.code = code;
        this.name = code.toString();
    }

    public BaseCode(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

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
