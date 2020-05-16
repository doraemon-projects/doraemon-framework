package org.doraemon.framework.exception;

import org.doraemon.framework.base.BaseCode;

/**
 * @Package : com.jfteam.exception
 * @Description :
 * @Author : yuanDong.lin
 * @Date : 2019-08-27 14:32
 */
public class ExceptionCode extends BaseCode<Integer> implements IExceptionCodeProvider {

    private Integer errorCode;

    @Override
    public String getErrorCode() {
        return this.errorCode.toString();
    }

    @Override
    public String getMessage() {
        return ExceptionMessageManager.getMessage(this.getErrorCode());
    }

    @Override
    public Integer getCode() {
        return this.errorCode;
    }

    @Override
    public String getName() {
        return this.getMessage();
    }

    @Override
    public String getDesc() {
        return this.getMessage();
    }
}
