package org.doraemon.framework;

import org.doraemon.framework.base.BaseCode;
import org.doraemon.framework.base.IEnumProvider;

import java.nio.charset.Charset;

/**
 * @description: 系统常量, 后续可以继承
 * @author: fengwenping
 * @date: 2020-05-16 23:32
 */
public abstract class Constants {

    public static class Language {

    }

    public static class CharsetConfig {
        public static Charset defaultCharset() {
            return utf8Charset();
        }

        public static Charset big5Charset() {
            return Charset.forName("Big5");
        }

        public static Charset utf8Charset() {
            return Charset.forName("UTF-8");
        }
    }

    public static class ContentType {
        public static final String ALL_VALUE = "*/*";
        public static final String APPLICATION_JSON_UTF_8 = "application/json; charset=UTF-8";
        public static final String APPLICATION_XML = "application/xml";
        public static final String TEXT_HTML = "text/html";
        public static final String TEXT_PLAIN = "text/plain";

    }

    public static class ResultCode {
        public static final IEnumProvider<Integer> SUCCESS = new BaseCode(20000, "请求成功");
        public static final IEnumProvider<Integer> CHECK_ERROR = new BaseCode(20004, "参数验证失败");
        public static final IEnumProvider<Integer> LOGIN_SUCCESS = new BaseCode(20007, "用户已登录");
        public static final IEnumProvider<Integer> CUSTOM_ERROR = new BaseCode(20008, "用户自定义错误");
        public static final IEnumProvider<Integer> EXCEPTION = new BaseCode(40000, "系统异常");
        public static final IEnumProvider<Integer> NOT_LOGIN = new BaseCode(40100, "用户未登录");
        public static final IEnumProvider<Integer> AUTH_CHECK_ERROR = new BaseCode(40300, "权限认证失败");
        public static final IEnumProvider<Integer> TOKEN_EXPIRED = new BaseCode(40301, "权限过期");
        public static final IEnumProvider<Integer> NOT_FOUND = new BaseCode(40400, "数据不存在");
        public static final IEnumProvider<Integer> FAILURE = new BaseCode(50000, "系统未知异常");
        public static final IEnumProvider<Integer> TIMEOUT = new BaseCode(50400, "服务超时");
    }
}
