package org.doraemon.framework.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @description: 响应结果
 * @author: fengwenping
 * @date: 2020-05-16 22:30
 */
public class Result<T> {
    @JsonIgnore
    private int httpStatus = 200;
    private String code;
    private String message;
    private T data;

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static Result success() {
        Result result = new Result.Builder<>()
                .code(ResultCode.SUCCESS.getCode())
                .message(ResultCode.SUCCESS.getName())
                .build();
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result.Builder<>()
                .code(ResultCode.SUCCESS.getCode())
                .message(ResultCode.SUCCESS.getName())
                .data(data).build();
        return result;
    }

    public static Result failure(Object data) {
        Result result = new Builder<>()
                .code(ResultCode.FAILURE.getCode())
                .message(ResultCode.FAILURE.getName())
                .build();
        return result;
    }

    public static final class Builder<T> {
        private int httpStatus;
        private String code;
        private String message;
        private T data;

        public Builder() {
        }

        public Builder<T> httpStatus(int httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder<T> code(String code) {
            this.code = code;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Result<T> build() {
            Result<T> result = new Result<T>();
            result.httpStatus = this.httpStatus;
            result.data = this.data;
            result.code = this.code;
            result.message = this.message;
            return result;
        }
    }
}
