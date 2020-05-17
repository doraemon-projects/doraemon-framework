package org.doraemon.framework.webmvc.advice;

import org.doraemon.framework.base.BaseCode;
import org.doraemon.framework.exception.ApplicationException;
import org.doraemon.framework.exception.BusinessException;
import org.doraemon.framework.webmvc.response.Result;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-17 11:53
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> MethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String message = null;
        final List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(allErrors)) {
            final ObjectError objectError = allErrors.get(0);
            message = objectError.getDefaultMessage();
        }
        return new Result.Builder<String>().code(BaseCode.CHECK_ERROR.getCode()).message(message).build();
    }

    @ExceptionHandler(BusinessException.class)
    public Result<String> businessException(BusinessException exception) {
        return new Result.Builder<String>()
                .code(Integer.getInteger(exception.getErrorCode()))
                .message(exception.getMessage()).build();
    }

    @ExceptionHandler(ApplicationException.class)
    public Result<String> applicationException(ApplicationException exception) {
        return new Result.Builder<String>()
                .code(Integer.getInteger(exception.getErrorCode()))
                .message(exception.getMessage()).build();
    }
}
