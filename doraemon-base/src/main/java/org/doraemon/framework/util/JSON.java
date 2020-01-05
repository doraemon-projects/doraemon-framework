package org.doraemon.framework.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 采用jackson作为工具类,废弃fastjson
 * Author:      fengwenping
 * Date:        2019/12/29 16:53
 */
public abstract class JSON {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {

        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    /**
     * 把JSON文本parse为JSONObject或者JSONArray
     *
     * @param text JSON字符串
     * @return JSONObject或者JSONArray对象
     */
    public static JsonNode parse(String text) {
        try {
            return OBJECT_MAPPER.readTree(text);
        } catch (JsonProcessingException e) {
            throw new JsonCastException("convert json string to json object failure", e);
        }
    }

    /**
     * 将JavaBean转换为JSONObject或者JSONArray
     *
     * @param javaObject JavaBean对象
     * @return JSONObject或者JSONArray对象
     */
    public static JsonNode toJSON(Object javaObject) {
        return OBJECT_MAPPER.convertValue(javaObject, JsonNode.class);
    }

    /**
     * 把JSON文本parse为JavaBean
     *
     * @param text  JSON字符串
     * @param clazz 对象类型
     * @param <T>   泛型
     * @return JavaBean对象
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(text, clazz);
        } catch (JsonProcessingException e) {
            throw new JsonCastException("convert json string to clazz instance failure", e);
        }
    }


    /**
     * 把JSON文本parse成JavaBean集合
     *
     * @param text  JSON字符串
     * @param clazz 对象类型
     * @param <T>   泛型
     * @return JavaBean结合
     */
    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        final TypeFactory typeFactory = OBJECT_MAPPER.getTypeFactory();
        final JavaType javaType = typeFactory.constructParametricType(List.class, clazz);
        try {
            return OBJECT_MAPPER.readValue(text, javaType);
        } catch (JsonProcessingException e) {
            throw new JsonCastException("convert json string to clazz collection failure", e);
        }
    }

    /**
     * 将JavaBean序列化为JSON文本
     *
     * @param object JavaBean对象
     * @return JSON字符串
     */
    public static String toJSONString(Object object) {
        return toJSONString(object, false);
    }

    /**
     * 将JavaBean序列化为带格式的JSON文本
     *
     * @param object       JavaBean对象
     * @param prettyFormat 是否格式化
     * @return JSON字符串
     */
    public static String toJSONString(Object object, boolean prettyFormat) {
        try {
            if (prettyFormat) {
                return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            }
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonCastException("convert object to json string failure", e);
        }
    }

    /**
     * JSON转换异常
     */
    public static class JsonCastException extends RuntimeException {

        public JsonCastException(String message, Throwable throwable) {
            super(message, throwable);
        }
    }
}