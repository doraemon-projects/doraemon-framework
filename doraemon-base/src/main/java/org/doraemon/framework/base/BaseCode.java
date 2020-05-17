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

    public static final IEnumProvider<Integer> SUCCESS = new BaseCode(10001, "请求成功");
    public static final IEnumProvider<Integer> CHECK_ERROR = new BaseCode(10002, "参数验证失败");
    public static final IEnumProvider<Integer> TIMEOUT = new BaseCode(10003, "服务超时");
    public static final IEnumProvider<Integer> FAILURE = new BaseCode(10004, "系统未知错误");
    public static final IEnumProvider<Integer> NOT_LOGIN = new BaseCode(10005, "用户未登录");
    public static final IEnumProvider<Integer> AUTH_CHECK_ERROR = new BaseCode(10006, "权限认证失败");
    public static final IEnumProvider<Integer> LOGIN_SUCCESS = new BaseCode(10007, "用户已登录");
    public static final IEnumProvider<Integer> CUSTOM_ERROR = new BaseCode(10008, "用户自定义错误");
    public static final IEnumProvider<Integer> TOKEN_EXPIRED = new BaseCode(10009, "权限过期");
}
