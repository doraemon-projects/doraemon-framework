package org.jfteam.mybatis.util;

import java.nio.charset.Charset;

/**
 * created with IntelliJ IDEA.
 * description: 描述
 * author:      fengwenping
 * date:        2019/7/14 21:59
 */
public class ConfigUtils {

    public static Charset defaultCharset() {
        return Charset.forName("UTF-8");
    }

    public static Charset big5Charset() {
        return Charset.forName("Big5");
    }

    public static Charset utf8Charset() {
        return Charset.forName("UTF-8");
    }
}
