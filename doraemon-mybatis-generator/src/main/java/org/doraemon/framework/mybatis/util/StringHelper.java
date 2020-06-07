package org.doraemon.framework.mybatis.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 *
 * @author fengwenping
 * @date 2018-10-28 下午5:17
 */
public final class StringHelper {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     */
    public static String underLine2Camel(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 驼峰转下划线,效率比上面高
     */
    public static String camel2UnderLine(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String subPrefix(String s) {
        if (s != null && s.length() > 0 && s.contains(".")) {
            return s.substring(s.lastIndexOf(".")+1);
        }
        return "";
    }

    public static void main(String[] args) {
        String s = "java.String";
        System.out.println(subPrefix(s));
    }
}
