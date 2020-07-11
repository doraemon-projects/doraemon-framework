package org.doraemon.framework.webmvc.advice;

import org.doraemon.framework.response.Result;
import org.doraemon.framework.util.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-17 12:07
 */
@RestControllerAdvice(basePackages = "org.doraemon.*.controller")
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        return !Objects.equals(Result.class, returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (Objects.equals(String.class, returnType.getGenericParameterType())) {
            return JSON.toJSONString(Result.success(data));
        }
        return Result.success(data);
    }
}
