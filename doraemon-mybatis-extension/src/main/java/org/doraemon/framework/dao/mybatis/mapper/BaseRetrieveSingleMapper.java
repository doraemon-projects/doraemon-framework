package org.doraemon.framework.dao.mybatis.mapper;

import java.io.Serializable;
import java.util.Map;

/**
 * @description: 基本的数据库检索单条记录操作
 * @author: fengwenping
 * @date: 2021-07-24 16:30
 */
public interface BaseRetrieveSingleMapper<T, ID extends Serializable> {

    /**
     * 查询单条数据(根据ID查找)
     *
     * @param id
     * @return
     */
    T findOneById(ID id);

    /**
     * 查询单条数据(根据实体)
     *
     * @param entity
     * @return
     */
    T findOneByEntity(T entity);

    /**
     * 查询单条数据(根据map)
     *
     * @param map
     * @return
     */
    T findOneByMap(Map map);
}
