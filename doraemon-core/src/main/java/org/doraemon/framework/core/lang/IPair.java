package org.doraemon.framework.core.lang;

import java.io.Serializable;

/**
 * @description: 键值对数据结构定义
 * @author: fengwenping
 * @date: 2021-08-08 15:41
 */
public interface IPair<K, V> extends Serializable {

    /**
     * 键属性
     *
     * @return
     */
    K getKey();

    /**
     * 值属性
     *
     * @return
     */
    V getValue();
}
