package org.doraemon.framework.base.dao;

import org.apache.ibatis.annotations.Param;
import org.doraemon.framework.domain.Page;
import org.doraemon.framework.domain.Pageable;
import org.doraemon.framework.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2019-07-12 21:25
 */
public interface BaseMapper<T, ID extends Serializable> {

    /**
     * 插入数据(根据实体)
     *
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int insert(S entity);

    /**
     * 插入数据(选择性插入)
     *
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int insertSelective(S entity);


    /**
     * 插入数据(批量插入)
     *
     * @param entities
     * @param <S>
     * @return
     */
    <S extends T> int insertInBatch(Iterable<S> entities);

    /**
     * 插入数据(选择性批量插入)
     *
     * @param entities
     * @param <S>
     * @return
     */
    <S extends T> int insertSelectiveInBatch(Iterable<S> entities);

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
    int deleteInIds(Iterable<ID> ids);

    /**
     * 删除数据(根据实体删)
     *
     * @param entity
     * @return
     */
    int deleteByEntity(T entity);

    /**
     * 删除数据(根据多个实体)
     *
     * @param entities
     * @return
     */
    int deleteInBatch(Iterable<? extends T> entities);

    /**
     * 删除数据(所有)
     *
     * @return
     */
    int deleteAll();

    /**
     * 更新数据(根据ID更新为目标实体)
     *
     * @param id
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int updateById(@Param("id") ID id, @Param("entity") S entity);

    /**
     * 选择性的更新数据(根据ID更新为目标实体)
     *
     * @param id
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int updateSelectiveById(@Param("id") ID id, @Param("entity") S entity);

    /**
     * 更新数据(根据多个更新为目标实体)
     *
     * @param ids
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int updateInIds(@Param("ids") Iterable<ID> ids, @Param("entity") S entity);

    /**
     * 选择性的更新数据(根据多个更新为目标实体)
     *
     * @param ids
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int updateSelectiveInIds(@Param("ids") Iterable<ID> ids, @Param("entity") S entity);

    /**
     * 更新数据(按照原实体更新为目标实体)
     *
     * @param source
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int updateByEntity(@Param("source") S source, @Param("entity") S entity);

    /**
     * 更新数据(选择性的更新数据,按照原实体更新为目标实体)
     *
     * @param source
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int updateSelectiveByEntity(@Param("source") S source, @Param("entity") S entity);

    /**
     * 更新数据(更新所有的记录)
     *
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int updateAll(@Param("entity") S entity);

    /**
     * 查询单条数据(根据ID查找)
     *
     * @param id
     * @return
     */
    <S extends T> S findOneById(ID id);

    /**
     * 查询单条数据(根据实体)
     *
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> T findOneByEntity(S entity);

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
     * @param <S>
     * @return
     */
    <S extends T> List<T> findMultiByEntity(S entity);

    /**
     * 查询多条数据(根据多个ID查询)
     *
     * @param ids
     * @return
     */
    List<T> findMultiByIds(Iterable<ID> ids);

    /**
     * 查询多条数据(按照排序返回)
     *
     * @param sort
     * @return
     */
    List<T> findMultiBySort(Sort sort);

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
     * @param <S>
     * @return
     */
    <S extends T> int findCountByEntity(S entity);

    /**
     * 查询多条数据(按照分页信息)
     *
     * @param pageable
     * @return
     */
    Page<T> findPage(Pageable pageable);

    /**
     * 统计数量(按照分页信息)
     *
     * @param pageable
     * @return
     */
    int findPageCount(Pageable pageable);
}
