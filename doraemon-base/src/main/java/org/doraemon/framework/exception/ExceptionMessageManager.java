package org.doraemon.framework.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * Description: 修改获取异常国际化消息
 * User: fengwenping
 * Date: 2018-02-08
 * Time: 上午8:57
 */
public final class ExceptionMessageManager {

    public static final String ACCEPT_LANGUAGE = "Accept-Language";

    private static MessageSource messageSource;

    public static MessageSource getMessageSource() {
        if (messageSource == null) {
            synchronized (ExceptionMessageManager.class) {
                if (messageSource == null) {
//                    messageSource = AppContextHolder.getContext().getBean("messageSource", MessageSource.class);
                }
            }
        }
        return messageSource;
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
        return ExceptionMessageManager.getMessageSource().getMessage(code, args, defaultMessage, locale);
    }
}
