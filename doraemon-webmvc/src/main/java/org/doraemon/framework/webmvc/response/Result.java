package org.doraemon.framework.webmvc.response;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-16 22:30
 */
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static Result SUCCESS = new Result.Builder<>()
            .code(ResultCode.SUCCESS.getCode())
            .message(ResultCode.SUCCESS.getName())
            .build();

    public static Result FAILURE = new Builder<>()
            .code(ResultCode.FAILURE.getCode())
            .message(ResultCode.FAILURE.getName())
            .build();

    public static final class Builder<T> {
        private Integer code;
        private String message;
        private T data;

        public Builder() {
        }

        public Builder code(Integer code) {
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
