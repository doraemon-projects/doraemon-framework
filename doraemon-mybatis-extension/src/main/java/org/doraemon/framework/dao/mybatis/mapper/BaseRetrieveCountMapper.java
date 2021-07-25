package org.doraemon.framework.dao.mybatis.mapper;

import java.util.Map;

/**
 * @description: 基本的数据库检索数量操作
 * @author: fengwenping
 * @date: 2021-07-24 16:40
 */
public interface BaseRetrieveCountMapper<T> {

    /**
     * 统计数量(所有)
     *
     * @return
     */
    int findCount();

    /**
     * 统计数量(按实体)
     *
     * @param entity
     * @return
     */
    int findCountByEntity(T entity);

    /**
     * 统计数量(按map)
     *
     * @param param
     * @return
     */
    int findCountByMap(Map param);
}
