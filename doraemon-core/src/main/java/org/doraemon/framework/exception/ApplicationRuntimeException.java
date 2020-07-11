package org.doraemon.framework.exception;

import org.doraemon.framework.response.ResultCode;

/**
 * Created with IntelliJ IDEA.
 * Description: 运行时异常，在项目运行之后出错则直接中止运行。异常由JVM虚拟机处理
 * Author:      fengwenping
 * Date:        2019/12/15 19:33
 */
public class ApplicationRuntimeException extends RuntimeException {

    private String errorCode = ResultCode.CUSTOM_ERROR.getCode();
    private String message = ResultCode.CUSTOM_ERROR.getName();

    public ApplicationRuntimeException() {
        super();
    }

    public ApplicationRuntimeException(String message) {
        super(message);
        this.message = message;
    }

    public ApplicationRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public ApplicationRuntimeException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public ApplicationRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

    public ApplicationRuntimeException(String errorCode, String message, Throwable throwable) {
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
