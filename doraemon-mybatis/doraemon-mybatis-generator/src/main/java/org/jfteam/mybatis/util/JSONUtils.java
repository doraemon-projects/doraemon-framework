package org.jfteam.mybatis.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * created with IntelliJ IDEA.
 * description: json工具类,json和对象之间互转
 * author:      fengwenping
 * date:        2019/7/7 23:53
 */
public final class JSONUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtils.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JSONUtils() {
    }

    /**
     * 把对象转为json字符串
     *
     * @param value 需要转为json字符串的对象
     * @return
     */
    public static String toJSONString(Object value) {
        String valueAsString = null;
        try {
            valueAsString = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            LOGGER.error("转换json字符串失败.", e);
        }
        return valueAsString;
    }

    /**
     * 把json字符串转为bean对象
     *
     * @param content json字符串
     * @param clazz   指定的bean对象类型
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String content, Class<T> clazz) {
        T t = null;
        try {
            t = objectMapper.readValue(content, clazz);
        } catch (IOException e) {
            LOGGER.error("把json字符串转为对象失败.", e);
        }
        return t;
    }

    /**
     * 把inputStream转为对象
     *
     * @param inputStream
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(InputStream inputStream, Class<T> clazz) {
        T t = null;
        try {
            t = objectMapper.readValue(inputStream, clazz);
        } catch (IOException e) {
            LOGGER.error("把IO转换为对象失败.", e);
        }
        return t;
    }

    /**
     * 把json字符串转为list对象
     *
     * @param content json字符串
     * @param clazz   list泛型的指定类型
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String content, Class<T> clazz) {
        List<T> list = Collections.EMPTY_LIST;
        final CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        try {
            list = objectMapper.readValue(content, collectionType);
        } catch (IOException e) {
            LOGGER.error("把json字符串转为ArrayList对象失败.", e);
        }
        return list;
    }

    /**
     * 把json字符串转为map对象
     *
     * @param content json字符串
     * @param kClass  map的Key的数据类型
     * @param vClass  map的Value的数据类型
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> parseMap(String content, Class<K> kClass, Class<V> vClass) {
        Map<K, V> map = Collections.EMPTY_MAP;
        final MapType mapType = objectMapper.getTypeFactory().constructMapType(HashMap.class, kClass, vClass);
        try {
            map = objectMapper.readValue(content, mapType);
        } catch (IOException e) {
            LOGGER.error("把json字符串转为HashMap对象失败.", e);
        }
        return map;
    }
}
