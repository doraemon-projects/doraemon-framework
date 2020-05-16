package org.doraemon.framework;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-16 23:32
 */
public abstract class Constants {

    private Constants() {
    }

    public static class WebConstants {
        public static final String X_DORAEMON_SESSION = "x-doraemon-session";
        public static final String X_DORAEMON_TOKEN = "x-doraemon-token";
    }
}
