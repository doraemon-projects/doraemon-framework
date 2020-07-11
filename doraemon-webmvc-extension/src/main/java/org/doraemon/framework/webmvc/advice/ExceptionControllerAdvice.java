package org.doraemon.framework.webmvc.advice;

import org.doraemon.framework.exception.ApplicationException;
import org.doraemon.framework.exception.ApplicationRuntimeException;
import org.doraemon.framework.response.Result;
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
        return Result.failure(data);
    }

    @ExceptionHandler(ApplicationRuntimeException.class)
    public Result<String> businessException(ApplicationRuntimeException exception) {
        return new Result.Builder<String>()
                .code(exception.getErrorCode())
                .message(exception.getMessage()).build();
    }

    @ExceptionHandler(ApplicationException.class)
    public Result<String> applicationException(ApplicationException exception) {
        return new Result.Builder<String>()
                .code(exception.getErrorCode())
                .message(exception.getMessage()).build();
    }
}
