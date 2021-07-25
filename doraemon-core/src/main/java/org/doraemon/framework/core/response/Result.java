package org.doraemon.framework.core.response;

/**
 * @description: 响应结果
 * @author: fengwenping
 * @date: 2020-05-16 22:30
 */
public class Result<T> {
    private int httpStatus = 200;
    private String code;
    private String message;
    private boolean success = Boolean.TRUE;
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

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public static final class Builder<T> {
        private int httpStatus = 200;
        private String code;
        private String message;
        private boolean success = Boolean.TRUE;
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

        public Builder<T> success(boolean success) {
            this.success = success;
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
            result.success = this.success;
            return result;
        }
    }
}
