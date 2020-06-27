package org.doraemon.framework.exception;

import org.doraemon.framework.base.BaseCode;
import org.doraemon.framework.base.IEnumProvider;
import org.doraemon.framework.util.StringUtils;

/**
 * @Package : com.jfteam.exception
 * @Description :
 * @Author : yuanDong.lin
 * @Date : 2019-08-27 14:32
 */
public class ExceptionCode implements IExceptionCodeProvider {

    private String errorCode;

    private String errorMessage;

    public ExceptionCode(String errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = null;
    }

    public ExceptionCode(IEnumProvider<Integer> enumProvider) {
        this.errorCode = enumProvider.getCode().toString();
        this.errorMessage = enumProvider.getName();
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessage() {
        if (StringUtils.isBlank(this.errorMessage)) {
            return ExceptionMessageManager.getMessage(this.errorCode);
        }
        return this.errorMessage;
    }
}
