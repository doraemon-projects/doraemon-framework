package org.doraemon.framework.exception;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Description: 非运行时异常，在项目运行之前必须处理掉。一般由程序员try catch 掉
 * Author:      fengwenping
 * Date:        2019/12/15 19:33
 */
public class ApplicationException extends Exception {

    private final String errorCode;
    private String message;
    private Object[] args;

    public ApplicationException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ApplicationException(String errorCode, Object... args) {
        super();
        this.errorCode = errorCode;
        this.args = args;
    }

    public ApplicationException(String errorCode, Throwable throwable) {
        super(throwable);
        this.errorCode = errorCode;
    }

    public ApplicationException(String errorCode, Throwable throwable, Object... args) {
        super(throwable);
        this.errorCode = errorCode;
        this.args = args;
    }

    public ApplicationException(IExceptionCodeProvider exceptionCodeProvider) {
        this.errorCode = exceptionCodeProvider.getErrorCode();
        this.message = exceptionCodeProvider.getMessage();
    }

    public ApplicationException(IExceptionCodeProvider exceptionCodeProvider, Object... args) {
        this.errorCode = exceptionCodeProvider.getErrorCode();
        this.message = exceptionCodeProvider.getMessage();
        this.args = args;
    }

    /**
     * 尽量不要调用此方法,错误信息采用ExceptionMessageManager构建
     *
     * @return 异常信息
     */
    @Override
    public String getMessage() {
        if (Objects.nonNull(this.message) && this.message.trim().length() > 0) {
            return this.message;
        }
        return ExceptionMessageManager.getMessage(this.errorCode, this.args);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }
}
