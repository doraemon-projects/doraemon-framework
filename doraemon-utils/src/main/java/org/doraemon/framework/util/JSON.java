package org.doraemon.framework.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2019/12/29 16:53
 */
public abstract class JSON {

    /**
     * 把JSON文本parse为JSONObject或者JSONArray
     *
     * @param text
     * @return
     */
    public static final Object parse(String text);

    /**
     * 把JSON文本parse成JSONObject
     *
     * @param text
     * @return
     */
    public static final JSONObject parseObject(String text);

    /**
     * 把JSON文本parse为JavaBean
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static final <T> T parseObject(String text, Class<T> clazz);

    /**
     * 把JSON文本parse成JSONArray
     *
     * @param text
     * @return
     */
    public static final JSONArray parseArray(String text);

    /**
     * 把JSON文本parse成JavaBean集合
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static final <T> List<T> parseArray(String text, Class<T> clazz);

    /**
     * 将JavaBean序列化为JSON文本
     *
     * @param object
     * @return
     */
    public static final String toJSONString(Object object);

    /**
     * 将JavaBean序列化为带格式的JSON文本
     *
     * @param object
     * @param prettyFormat
     * @return
     */
    public static final String toJSONString(Object object, boolean prettyFormat);

    /**
     * 将JavaBean转换为JSONObject或者JSONArray
     *
     * @param javaObject
     * @return
     */
    public static final Object toJSON(Object javaObject);
}
