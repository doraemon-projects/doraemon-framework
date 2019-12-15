package org.doraemon.framework.exception;

/**
 * Created with IntelliJ IDEA.
 * Description: 运行时异常，在项目运行之后出错则直接中止运行。异常由JVM虚拟机处理
 * Author:      fengwenping
 * Date:        2019/12/15 19:33
 */
public class BusinessException extends RuntimeException {

    private String errorCode;
    private Object[] args;

    public BusinessException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, Object... args) {
        super();
        this.errorCode = errorCode;
        this.args = args;
    }

    public BusinessException(String errorCode, Throwable throwable) {
        super(throwable);
        this.errorCode = errorCode;
    }

    public BusinessException(String errorCode, Throwable throwable, Object... args) {
        super(throwable);
        this.errorCode = errorCode;
        this.args = args;
    }

    /**
     * 尽量不要调用此方法,错误信息采用ExceptionMessageManager构建
     *
     * @return
     */
    @Override
    public String getMessage() {
        return ExceptionMessageManager.getMessage(this.errorCode, this.args);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }
}
