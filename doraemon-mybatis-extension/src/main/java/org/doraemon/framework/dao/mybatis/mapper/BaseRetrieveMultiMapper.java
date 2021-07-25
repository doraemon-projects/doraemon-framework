package org.doraemon.framework.dao.mybatis.mapper;

import org.doraemon.framework.core.base.page.Sort;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @description: 基本的数据库检索多条记录操作
 * @author: fengwenping
 * @date: 2021-07-24 16:33
 */
public interface BaseRetrieveMultiMapper<T, ID extends Serializable> {

    /**
     * 查询多条数据(所有)
     *
     * @return
     */
    List<T> findAll();

    /**
     * 查询多条数据(按照实体查询)
     *
     * @param entity
     * @return
     */
    List<T> findMultiByEntity(T entity);

    /**
     * 查询多条数据(根据多个ID查询)
     *
     * @param ids
     * @return
     */
    List<T> findMultiByIds(Collection<ID> ids);

    /**
     * 查询多条数据(根据map)
     *
     * @param param
     * @return
     */
    List<T> findMultiByMap(Map param);

    /**
     * 查询多条数据(按照排序返回)
     *
     * @param sort
     * @return
     */
    List<T> findMultiBySort(Sort sort);
}
