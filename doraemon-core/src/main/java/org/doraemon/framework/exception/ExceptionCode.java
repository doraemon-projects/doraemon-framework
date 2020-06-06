package org.doraemon.framework.exception;

/**
 * @Package : com.jfteam.exception
 * @Description :
 * @Author : yuanDong.lin
 * @Date : 2019-08-27 14:32
 */
public class ExceptionCode implements IExceptionCodeProvider {

    private String errorCode;

    ExceptionCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessage() {
        return ExceptionMessageManager.getMessage(this.errorCode);
    }
}
