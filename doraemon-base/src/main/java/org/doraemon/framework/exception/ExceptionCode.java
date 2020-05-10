package org.doraemon.framework.exception;

import org.doraemon.framework.base.EnumProvider;

/**
 * @Package : com.jfteam.exception
 * @Description :
 * @Author : yuanDong.lin
 * @Date : 2019-08-27 14:32
 */
public abstract ExceptionCode implements EnumProvider<Integer> {
    PARAM_REQUIRED(50000, "参数不能为空", "参数不能为空"),

    PARAM_ERROR(50001, "参数异常", "参数异常"),

    RECORDS_NOT_EXIST(60000, "记录不存在", "记录不存在"),

    RECORDS_EXIST(60001, "记录已经存在", "记录已经存在"),

    THIRD_REQUEST_EXCEPTION(70000, "第三方请求异常", "第三方请求异常"),

    SYSTEM_ERROR(80000, "系统异常", "系统异常");

    private Integer code;
    private String name;
    private String desc;

    ExceptionCode(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
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
    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        return this.info();
    }
}
