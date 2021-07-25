package org.doraemon.framework.webmvc.advice;

import org.doraemon.framework.core.exception.SystemException;
import org.doraemon.framework.core.exception.BusinessException;
import org.doraemon.framework.core.response.Result;
import org.doraemon.framework.core.response.ResultUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-17 11:53
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        final List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        final List<String> data = Optional.ofNullable(allErrors).orElse(Collections.emptyList())
                .stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        return ResultUtils.failure(data);
    }

    @ExceptionHandler(BusinessException.class)
    public Result<String> businessException(BusinessException exception) {
        return new Result.Builder<String>()
                .code(exception.getErrorCode())
                .message(exception.getMessage()).build();
    }

    @ExceptionHandler(SystemException.class)
    public Result<String> systemException(SystemException exception) {
        return new Result.Builder<String>()
                .code(exception.getErrorCode())
                .message(exception.getMessage()).build();
    }
}
