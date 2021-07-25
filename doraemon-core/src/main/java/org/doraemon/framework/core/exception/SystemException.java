package org.doraemon.framework.core.exception;

import org.doraemon.framework.core.response.ResultCode;

/**
 * Created with IntelliJ IDEA.
 * Description: 非运行时异常，在项目运行之前必须处理掉。一般由程序员try catch 掉
 * Author:      fengwenping
 * Date:        2019/12/15 19:33
 */
public class SystemException extends Exception {

    private String errorCode = ResultCode.CUSTOM_ERROR.getCode();
    private String message = ResultCode.CUSTOM_ERROR.getName();

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
        this.message = message;
    }

    public SystemException(Throwable throwable) {
        super(throwable);
    }

    public SystemException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public SystemException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    public SystemException(String errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
        this.message = message;
    }

    /**
     * 尽量不要调用此方法,错误信息采用ExceptionMessageManager构建
     *
     * @return
     */
    @Override
    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
