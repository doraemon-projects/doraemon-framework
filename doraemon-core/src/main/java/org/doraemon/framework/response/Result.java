package org.doraemon.framework.response;

import org.doraemon.framework.Constants;

import java.util.Objects;

/**
 * @description: 响应结果
 * @author: fengwenping
 * @date: 2020-05-16 22:30
 */
public class Result<T> {
    private String code;
    private String message;
    private T data;

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
                .code(Objects.toString(Constants.ResultCode.SUCCESS.getCode()))
                .message(Constants.ResultCode.SUCCESS.getName())
                .build();
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result.Builder<>()
                .code(Objects.toString(Constants.ResultCode.SUCCESS.getCode()))
                .message(Constants.ResultCode.SUCCESS.getName())
                .data(data).build();
        return result;
    }

    public static Result failure(Object data) {
        Result result = new Builder<>()
                .code(Objects.toString(Constants.ResultCode.FAILURE.getCode()))
                .message(Constants.ResultCode.FAILURE.getName())
                .build();
        return result;
    }

    public static final class Builder<T> {
        private String code;
        private String message;
        private T data;

        public Builder() {
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(T data) {
            this.data = data;
            return this;
        }

        public Result build() {
            Result result = new Result();
            result.data = this.data;
            result.code = this.code;
            result.message = this.message;
            return result;
        }
    }
}
