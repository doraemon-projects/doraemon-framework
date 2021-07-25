package org.doraemon.framework.core.base;

/**
 * @description: 基本Code
 * @author: fengwenping
 * @date: 2020-05-16 18:09
 */
public class BaseCode implements IBaseCodeProvider {

    protected String code;
    protected String name;

    public BaseCode(String code) {
        this.code = code;
        this.name = code;
    }

    public BaseCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return info();
    }
}
