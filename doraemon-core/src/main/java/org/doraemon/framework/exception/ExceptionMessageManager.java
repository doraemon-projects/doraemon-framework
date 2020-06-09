package org.doraemon.framework.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * Description: 修改获取异常国际化消息
 * User: fengwenping
 * Date: 2018-02-08
 * Time: 上午8:57
 */
public final class ExceptionMessageManager {

    private ExceptionMessageManager(){}

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMessageManager.class);

    public static final String ACCEPT_LANGUAGE = "Accept-Language";

    public static final Map<String, Properties> LANG_PROPERTIES = new ConcurrentHashMap<>();

    public static final String EXCEPTION_PROPERTIES_FILE_NAME = "exception";

    static {
        Enumeration<URL> resources = null;
        try {
            resources = ExceptionMessageManager.class.getClassLoader().getResources(EXCEPTION_PROPERTIES_FILE_NAME);
        } catch (IOException e) {
            LOGGER.error("init exception resources failure", e);
        }
        if (Objects.nonNull(resources) && resources.hasMoreElements()) {
            while (resources.hasMoreElements()) {
                final URL url = resources.nextElement();
                ResourceBundle resourceBundle = ResourceBundle.getBundle(url.getPath());
                Properties properties = new Properties();
                final Enumeration<String> keys = resourceBundle.getKeys();
                while (keys.hasMoreElements()) {
                    final String key = keys.nextElement();
                    final String value = resourceBundle.getString(key);
                    properties.put(key, value);
                }
                final String displayLanguage = resourceBundle.getLocale().getDisplayLanguage();
                LANG_PROPERTIES.put(displayLanguage, properties);
            }
        }
    }

    private static Properties getMessageSource(Locale locale) {
        if (LANG_PROPERTIES.containsKey(locale.getDisplayLanguage())) {
            return LANG_PROPERTIES.get(locale.getDisplayLanguage());
        }
        throw new IllegalArgumentException("this language " + locale.getDisplayLanguage() + " don't support.");
    }

    /**
     * @param code：对应文本配置的key.
     * @return 对应地区的语言消息字符串
     */
    public static String getMessage(String code) {
        return ExceptionMessageManager.getMessage(code, new Object[]{});
    }

    public static String getMessage(String code, String defaultMessage) {
        return ExceptionMessageManager.getMessage(code, null, defaultMessage);
    }

    public static String getMessage(String code, String defaultMessage, Locale locale) {
        return ExceptionMessageManager.getMessage(code, null, defaultMessage, locale);
    }

    public static String getMessage(String code, Locale locale) {
        return ExceptionMessageManager.getMessage(code, null, code, locale);
    }

    public static String getMessage(String code, Object[] args) {
        return ExceptionMessageManager.getMessage(code, args, code);
    }

    public static String getMessage(String code, Object[] args, Locale locale) {
        return ExceptionMessageManager.getMessage(code, args, code, locale);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = Locale.getDefault();
        return ExceptionMessageManager.getMessage(code, args, defaultMessage, locale);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        final Properties messageSource = ExceptionMessageManager.getMessageSource(locale);
        final String property = messageSource.getProperty(code, defaultMessage);
        return MessageFormat.format(property, args);
    }
}
