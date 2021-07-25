package org.doraemon.framework.core.util;

import org.doraemon.framework.core.Constants;
import org.doraemon.framework.core.util.io.IOUtils;
import org.doraemon.framework.core.util.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * created with IntelliJ IDEA.
 * description: properties工具类
 * author:      fengwenping
 * date:        2019/6/25 21:16
 */
public final class PropertyUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtils.class);

    private static final String PROPERTIES_FILE_EXT_NAME = ".properties";

    private static final String APPLICATION_PROPERTIES_FILE_NAME = "application";

    private PropertyUtils() {
    }

    public static Properties getProperties(String fileName) {
        Properties properties = null;
        if (StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException("argument fileName can not be empty");
        }
        final InputStream inputStream = PropertyUtils.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new RuntimeException(String.format("需要加载的properties文件: %s 不存在,请检查文件是否存在.", fileName));
        }
        try {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("需要加载的properties文件: {} 不存在,请检查文件是否存在.", fileName, e);
        } finally {
            IOUtils.close(inputStream);
        }
        return properties;
    }

    /**
     * 获取系统配置
     *
     * @return
     */
    public static Properties getApplicationConfig() {
        return PropertyUtils.getProperties(APPLICATION_PROPERTIES_FILE_NAME + PROPERTIES_FILE_EXT_NAME);
    }

    /**
     * 获取系统配置(根据环境变化的配置)
     *
     * @param env
     * @return
     */
    public static Properties getApplicationEnvConfig(String env) {
        if (StringUtils.isNotBlank(env)) {
            return PropertyUtils.getProperties(new StringBuilder(APPLICATION_PROPERTIES_FILE_NAME)
                    .append("-")
                    .append(env)
                    .append(PROPERTIES_FILE_EXT_NAME).toString()
            );
        } else {
            return PropertyUtils.getProperties(new StringBuilder(APPLICATION_PROPERTIES_FILE_NAME)
                    .append(Constants.Environment.DEFAULT_SPRING_PROFILES_ACTIVE)
                    .append(PROPERTIES_FILE_EXT_NAME).toString());
        }
    }

    private static String getProperty(final Properties properties, final String key, final String defaultValue) {
        if (properties != null) {
            return properties.getProperty(key, defaultValue);
        }
        return defaultValue;
    }

    public static String getString(final Properties properties, final String key, final String defaultValue) {
        return PropertyUtils.getProperty(properties, key, defaultValue);
    }

    public static String getString(final Properties properties, final String key) {
        return PropertyUtils.getProperty(properties, key, "");
    }

    public static boolean getBoolean(final Properties properties, String key, boolean defaultValue) {
        final String property = getString(properties, key, String.valueOf(defaultValue));
        return "true".equalsIgnoreCase(property) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static boolean getBoolean(final Properties properties, String key) {
        final String property = getString(properties, key, String.valueOf(Boolean.FALSE));
        return "true".equalsIgnoreCase(property) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static int getInt(final Properties properties, final String key, final int defaultValue) {
        final String property = getString(properties, key, String.valueOf(defaultValue));
        return Integer.parseInt(property);
    }

    public static int getInt(final Properties properties, final String key) {
        final String property = getString(properties, key, String.valueOf(1));
        return Integer.parseInt(property);
    }

    public static double getDouble(final Properties properties, final String key, final double defaultValue) {
        final String property = getString(properties, key, String.valueOf(defaultValue));
        return Double.parseDouble(property);
    }

    public static double getDouble(final Properties properties, final String key) {
        final String property = getString(properties, key, String.valueOf(0));
        return Double.parseDouble(property);
    }
}
