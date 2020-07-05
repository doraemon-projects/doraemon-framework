package org.doraemon.framework;

import org.doraemon.framework.base.BaseCode;
import org.doraemon.framework.base.IEnumProvider;
import org.doraemon.framework.exception.ExceptionCode;
import org.doraemon.framework.exception.IExceptionCodeProvider;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @description: 系统常量, 后续可以继承
 * @author: fengwenping
 * @date: 2020-05-16 23:32
 */
public abstract class Constants {

    public static class CharsetConfig {
        public static Charset defaultCharset() {
            return utf8Charset();
        }

        public static Charset big5Charset() {
            return Charset.forName("Big5");
        }

        public static Charset utf8Charset() {
            return StandardCharsets.UTF_8;
        }
    }

    public static class ContentType {
        public static final String ALL_VALUE = "*/*";
        public static final String APPLICATION_JSON_UTF_8 = "application/json; charset=UTF-8";
        public static final String APPLICATION_XML_UTF_8 = "application/xml; charset=UTF-8";
        public static final String TEXT_HTML = "text/html";
        public static final String TEXT_PLAIN = "text/plain";

    }

    public static class ResultCode {
        public static final IEnumProvider<Integer> SUCCESS = new BaseCode(20000, "请求成功");
        public static final IEnumProvider<Integer> CHECK_ERROR = new BaseCode(20004, "参数验证失败");
        public static final IEnumProvider<Integer> LOGIN_SUCCESS = new BaseCode(20007, "用户已登录");
        public static final IEnumProvider<Integer> CUSTOM_ERROR = new BaseCode(20008, "用户自定义错误");
        public static final IEnumProvider<Integer> EXCEPTION = new BaseCode(40000, "系统内部异常");
        public static final IEnumProvider<Integer> NOT_LOGIN = new BaseCode(40100, "用户未登录");
        public static final IEnumProvider<Integer> AUTH_CHECK_ERROR = new BaseCode(40300, "权限认证失败");
        public static final IEnumProvider<Integer> TOKEN_EXPIRED = new BaseCode(40301, "权限过期");
        public static final IEnumProvider<Integer> NOT_FOUND = new BaseCode(40400, "数据不存在");
        public static final IEnumProvider<Integer> FAILURE = new BaseCode(50000, "系统未知异常");
        public static final IEnumProvider<Integer> EXPECTED = new BaseCode(50001, "参数未达到预期值");
        public static final IEnumProvider<Integer> TIMEOUT = new BaseCode(50400, "服务超时");
        //业务自定义编码
        public static final IEnumProvider<Integer> RECORD_EXIST = new BaseCode(20100, "记录已存在");
        public static final IEnumProvider<Integer> RECORD_NOT_EXIST = new BaseCode(20101, "记录不存在");
        public static final IEnumProvider<Integer> RECORD_INVALID = new BaseCode(20102, "记录不存在或失效");
        public static final IEnumProvider<Integer> STRING_IS_BLANK = new BaseCode(20103, "字符串为空");
        public static final IEnumProvider<Integer> RESULT_IS_FALSE = new BaseCode(20104, "结果为假");
        public static final IEnumProvider<Integer> RESULT_MODIFY_FAILURE = new BaseCode(20105, "更新记录失败");

        //自定义异常编码
        public static final IExceptionCodeProvider EXP_FAILURE = new ExceptionCode(FAILURE);
        public static final IExceptionCodeProvider EXP_CUSTOM_ERROR = new ExceptionCode(CUSTOM_ERROR);
        public static final IExceptionCodeProvider EXP_EXPECTED = new ExceptionCode(EXPECTED);
        public static final IExceptionCodeProvider EXP_RECORD_EXIST = new ExceptionCode(RECORD_EXIST);
        public static final IExceptionCodeProvider EXP_RECORD_NOT_EXIST = new ExceptionCode(RECORD_NOT_EXIST);
        public static final IExceptionCodeProvider EXP_RECORD_INVALID = new ExceptionCode(RECORD_INVALID);
        public static final IExceptionCodeProvider EXP_STRING_IS_BLANK = new ExceptionCode(STRING_IS_BLANK);
        public static final IExceptionCodeProvider EXP_RESULT_IS_FALSE = new ExceptionCode(RESULT_IS_FALSE);
        public static final IExceptionCodeProvider EXP_RESULT_MODIFY_FAILURE = new ExceptionCode(RESULT_IS_FALSE);
    }

    /**
     * 环境信息
     */
    public static class Environment {
        public static final String DEV = "dev";
        public static final String SIT = "sit";
        public static final String UAT = "uat";
        public static final String BETA = "beta";
        public static final String PRODUCTION = "production";
        public static final String DEFAULT_SPRING_PROFILES_ACTIVE = DEV;
        public static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
    }
}
