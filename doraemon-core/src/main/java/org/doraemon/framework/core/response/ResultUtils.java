package org.doraemon.framework.core.response;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-07-25 13:29
 */
public abstract class ResultUtils {

    public static Result success() {
        return ResultUtils.success(null);
    }

    public static Result success(Object data) {
        Result result = new Result.Builder<>()
                .code(ResultCode.SUCCESS.getCode())
                .message(ResultCode.SUCCESS.getName())
                .success(Boolean.TRUE)
                .data(data)
                .build();
        return result;
    }

    public static Result failure() {
        return ResultUtils.failure(null);
    }

    public static Result failure(Object data) {
        Result result = new Result.Builder<>()
                .code(ResultCode.FAILURE.getCode())
                .message(ResultCode.FAILURE.getName())
                .success(Boolean.FALSE)
                .data(data)
                .build();
        return result;
    }
}
