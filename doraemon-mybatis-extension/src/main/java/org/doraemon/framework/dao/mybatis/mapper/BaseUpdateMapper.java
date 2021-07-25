package org.doraemon.framework.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;

/**
 * @description: 基本的数据库更新操作
 * @author: fengwenping
 * @date: 2021-07-24 15:55
 */
public interface BaseUpdateMapper<T, ID extends Serializable> {

    /**
     * 更新数据(根据ID更新为目标实体)
     *
     * @param id
     * @param entity
     * @return
     */
    int updateById(@Param("id") ID id, @Param("entity") T entity);

    /**
     * 更新数据(根据ID更新为目标实体)
     *
     * @param id
     * @param entity
     * @return
     */
    int updateSelectiveById(@Param("id") ID id, @Param("entity") T entity);

    /**
     * 更新数据(批量根据ID更新为目标实体)
     *
     * @param entities
     * @return
     */
    int updateBatchById(Collection<T> entities);

    /**
     * 更新数据(批量根据ID更新为目标实体)
     *
     * @param entities
     * @return
     */
    int updateSelectiveBatchById(Collection<T> entities);

    /**
     * 更新数据(把多个id更新为目标实体)
     *
     * @param ids
     * @param entity
     * @return
     */
    int updateByIds(@Param("ids") Collection<ID> ids, @Param("entity") T entity);

    /**
     * 更新数据(把多个id更新为目标实体)
     *
     * @param ids
     * @param entity
     * @return
     */
    int updateSelectiveByIds(@Param("ids") Collection<ID> ids, @Param("entity") T entity);

    /**
     * 更新数据(按照原实体更新为目标实体)
     *
     * @param source
     * @param entity
     * @return
     */
    int updateByEntity(@Param("source") T source, @Param("entity") T entity);

    /**
     * 更新数据(按照原实体更新为目标实体)
     *
     * @param source
     * @param entity
     * @return
     */
    int updateSelectiveByEntity(@Param("source") T source, @Param("entity") T entity);
}
