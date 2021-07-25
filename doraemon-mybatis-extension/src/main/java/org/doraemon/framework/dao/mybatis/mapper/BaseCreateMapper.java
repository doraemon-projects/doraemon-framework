package org.doraemon.framework.dao.mybatis.mapper;

import java.util.Collection;

/**
 * @description: 基本的数据库插入操作
 * @author: fengwenping
 * @date: 2021-07-24 15:52
 */
public interface BaseCreateMapper<T> {
    /**
     * 插入数据(根据实体)
     *
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 插入数据(选择性插入)
     *
     * @param entity
     * @return
     */
    int insertSelective(T entity);


    /**
     * 插入数据(批量插入)
     *
     * @param entities
     * @return
     */
    int insertWithBatch(Collection<T> entities);

    /**
     * 插入数据(选择性批量插入)
     *
     * @param entities
     * @return
     */
    int insertSelectiveWithBatch(Collection<T> entities);
}
