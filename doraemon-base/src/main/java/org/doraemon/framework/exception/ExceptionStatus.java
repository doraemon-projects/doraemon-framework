package org.doraemon.framework.exception;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2019/12/29 16:23
 */
public class ExceptionStatus implements IExceptionCodeProvider {

    private final String code;
    private final String message;

    public ExceptionStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public static final IExceptionCodeProvider SUCCESS = new ExceptionStatus("10001", "请求成功");
    public static final IExceptionCodeProvider CHECK_ERROR = new ExceptionStatus("10002", "参数验证失败");
    public static final IExceptionCodeProvider TIMEOUT = new ExceptionStatus("10003", "服务超时");
    public static final IExceptionCodeProvider ERROR = new ExceptionStatus("10004", "系统未知错误");
    public static final IExceptionCodeProvider NOT_LOGIN = new ExceptionStatus("10005", "用户未登录");
    public static final IExceptionCodeProvider AUTH_CHECK_ERROR = new ExceptionStatus("10006", "权限认证失败");
    public static final IExceptionCodeProvider LOGIN_SUCCESS = new ExceptionStatus("10007", "用户已登录");
    public static final IExceptionCodeProvider CUSTOM_ERROR = new ExceptionStatus("10008", "用户自定义错误");
    public static final IExceptionCodeProvider TOKEN_EXPIRED = new ExceptionStatus("10009", "权限过期");
}
