package org.doraemon.framework.dao.mybatis.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * @description: 基本的数据库删除操作
 * @author: fengwenping
 * @date: 2021-07-24 15:53
 */
public interface BaseDeleteMapper<T, ID extends Serializable> {

    /**
     * 删除数据(根据主键删除)
     *
     * @param id
     */
    int deleteById(ID id);

    /**
     * 删除数据(根据多个主键删除)
     *
     * @param ids
     * @return
     */
    int deleteByIds(Collection<ID> ids);

    /**
     * 删除数据(根据实体删)
     *
     * @param entity
     * @return
     */
    int deleteByEntity(T entity);

    /**
     * 删除数据(根据map参数)
     *
     * @param param
     * @return
     */
    int deleteByMap(Map param);

    /**
     * 删除数据(根据多个实体)
     *
     * @param entities
     * @return
     */
    int deleteByEntities(Collection<T> entities);

    /**
     * 删除数据(所有)
     *
     * @return
     */
    int deleteAll();
}
