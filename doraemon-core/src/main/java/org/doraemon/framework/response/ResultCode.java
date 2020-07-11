package org.doraemon.framework.response;

import org.doraemon.framework.base.BaseCode;
import org.doraemon.framework.base.IBaseCodeProvider;

/**
 * @description: 通用常量
 * @author: fengwenping
 * @date: 2020-07-11 15:12
 */
public abstract class ResultCode {
    public static final IBaseCodeProvider SUCCESS = new BaseCode("20000", "请求成功");
    public static final IBaseCodeProvider CHECK_ERROR = new BaseCode("20004", "参数验证失败");
    public static final IBaseCodeProvider LOGIN_SUCCESS = new BaseCode("20007", "用户已登录");
    public static final IBaseCodeProvider CUSTOM_ERROR = new BaseCode("20008", "用户自定义错误");
    //业务自定义编码
    public static final IBaseCodeProvider RECORD_EXIST = new BaseCode("20100", "记录已存在或已创建");
    public static final IBaseCodeProvider RECORD_NOT_EXIST = new BaseCode("20101", "记录不存在");
    public static final IBaseCodeProvider RECORD_INVALID = new BaseCode("20102", "记录不存在或失效");
    public static final IBaseCodeProvider STRING_IS_BLANK = new BaseCode("20103", "字符串为空");
    public static final IBaseCodeProvider RESULT_IS_FALSE = new BaseCode("20104", "结果为假");
    public static final IBaseCodeProvider RESULT_MODIFY_FAILURE = new BaseCode("20105", "更新记录失败");
    public static final IBaseCodeProvider FAILURE = new BaseCode("40000", "请求未知错误");
    public static final IBaseCodeProvider NOT_LOGIN = new BaseCode("40100", "用户未登录");
    public static final IBaseCodeProvider AUTH_CHECK_ERROR = new BaseCode("40300", "权限认证失败");
    public static final IBaseCodeProvider TOKEN_EXPIRED = new BaseCode("40301", "权限过期");
    public static final IBaseCodeProvider NOT_FOUND = new BaseCode("40400", "数据不存在");
    public static final IBaseCodeProvider EXCEPTION = new BaseCode("50000", "系统未知异常");
    public static final IBaseCodeProvider EXPECTED = new BaseCode("50001", "参数未达到预期值");
    public static final IBaseCodeProvider TIMEOUT = new BaseCode("50400", "服务超时");

}
