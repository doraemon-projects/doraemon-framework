package org.doraemon.framework.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Package : org.doraemon.framework.util
 * @Description : fastJson工具类
 * @Author : yuanDong.lin
 * @Date : 2019-12-27 14:55
 */
public class JsonUtils {

    private static SerializerFeature[] serializerFeatures = new SerializerFeature[]{
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteDateUseDateFormat
    };

    /**
     * 对象转json字符串
     *
     * @param t 泛型对象实例
     * @return java.lang.String 序列化后字符串
     * @author yuanDong.lin
     * @date 2019/12/27 15:22
     *
     */
    public  static <T> String toJson(T t){
        return JSON.toJSONString(t,serializerFeatures);
    }

    /**
     *  对象转json，按照指定pattern格式化时间，若不传默认为yyyy-MM-dd格式
     *
     * @param t 泛型对象实例
     * @param pattern  格式化时间格式
     * @return java.lang.String 序列化后字符串
     * @author yuanDong.lin
     * @date 2019/12/27 15:27
     *
     */
    public static <T> String toJsonWithDateFormatter(T t, String pattern) {
        JSON.DEFFAULT_DATE_FORMAT = StringUtils.isEmpty(pattern) ? "yyyy-MM-dd" : pattern;
        return JSON.toJSONString(t, serializerFeatures);
    }

    /**
     * 根据自定义filter对象转json
     *
     * @param t 泛型对象实例
     * @param serializeFilters 过滤器
     * @return java.lang.String
     * @author yuanDong.lin
     * @date 2019/12/27 15:40
     *
     */
    public static <T> String toJsonWithFilter(T t, SerializeFilter[] serializeFilters) {
        return JSON.toJSONString(t, serializeFilters, serializerFeatures);
    }

    /**
     * 根据自定义filter对象转json,按照指定pattern格式化时间，若不传默认为yyyy-MM-dd格式
     *
     * @param t 泛型对象实例
     * @param pattern  格式化时间格式
     * @param serializeFilters 过滤器
     * @return java.lang.String
     * @author yuanDong.lin
     * @date 2019/12/27 15:41
     *
     */
    public static <T> String toJsonWithDateFormatterAndFilter(T t, String pattern, SerializeFilter[] serializeFilters) {
        JSON.DEFFAULT_DATE_FORMAT = StringUtils.isEmpty(pattern) ? "yyyy-MM-dd" : pattern;
        return JSON.toJSONString(t, serializeFilters, serializerFeatures);
    }

    /**
     * json字符串转对象
     *
     * @param json 字符串
     * @param clazz  转换对象类型
     * @return T 对象
     * @author yuanDong.lin
     * @date 2019/12/27 15:50
     *
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * json字符串转对象
     *
     * @param json 字符串
     * @param typeReference  转换类型
     * @return T 对象
     * @author yuanDong.lin
     * @date 2019/12/27 15:50
     *
     */
    public static <T> T parseObjectWithTypeReference(String json, TypeReference<T> typeReference) {
        return JSON.parseObject(json, typeReference);
    }

    /**
     * json字符串转对象数组
     *
     * @param json 字符串
     * @param clazz  转换对象类型
     * @return T 对象
     * @author yuanDong.lin
     * @date 2019/12/27 15:50
     *
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    /**
     * json字符串转对象
     *
     * @param json 字符串
     * @param clazz  转换对象类型
     * @param features 转换配置
     * @return T 对象
     * @author yuanDong.lin
     * @date 2019/12/27 15:50
     *
     */
    public static <T> T parseObjectWithFeature(String json, Class<T> clazz,Feature[] features) {
        return JSON.parseObject(json, clazz, features);
    }

    /**
     * json字符串转对象数组
     *
     * @param json 字符串
     * @param clazz  转换对象类型
     * @return T 对象
     * @author yuanDong.lin
     * @date 2019/12/27 15:50
     *
     */
    public static <T> List<T> parseArrayWithFeature(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

}
