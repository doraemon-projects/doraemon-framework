package org.doraemon.framework.exception;

import org.doraemon.framework.base.IBaseCodeProvider;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Description: 运行时异常，在项目运行之后出错则直接中止运行。异常由JVM虚拟机处理
 * Author:      fengwenping
 * Date:        2019/12/15 19:33
 */
public class ApplicationRuntimeException extends RuntimeException {

    private final String errorCode;
    private String message;
    private Object[] args;

    public ApplicationRuntimeException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ApplicationRuntimeException(String errorCode, Object... args) {
        super();
        this.errorCode = errorCode;
        this.args = args;
    }

    public ApplicationRuntimeException(String errorCode, Throwable throwable) {
        super(throwable);
        this.errorCode = errorCode;
    }

    public ApplicationRuntimeException(String errorCode, Throwable throwable, Object... args) {
        super(throwable);
        this.errorCode = errorCode;
        this.args = args;
    }

    public ApplicationRuntimeException(IBaseCodeProvider baseCodeProvider) {
        this.errorCode = baseCodeProvider.getCode();
        this.message = baseCodeProvider.getName();
    }

    public ApplicationRuntimeException(IBaseCodeProvider baseCodeProvider, Object... args) {
        this.errorCode = baseCodeProvider.getCode();
        this.message = baseCodeProvider.getName();
        this.args = args;
    }

    /**
     * 尽量不要调用此方法,错误信息采用ExceptionMessageManager构建
     *
     * @return
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
