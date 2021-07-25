package org.doraemon.framework.core;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @description: 系统常量, 后续可以继承
 * @author: fengwenping
 * @date: 2020-05-16 23:32
 */
public interface Constants {

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
