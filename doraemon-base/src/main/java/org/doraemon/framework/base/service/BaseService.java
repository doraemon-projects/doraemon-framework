package org.doraemon.framework.base.service;

import org.doraemon.framework.domain.Page;
import org.doraemon.framework.domain.PageRequest;
import org.doraemon.framework.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 通用基础服务接口
 * @author: fengwenping
 * @date: 2020-05-31 18:42
 */
public interface BaseService<T, ID extends Serializable> {
    /**
     * 插入数据(根据实体)
     *
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> S insert(S entity);

    /**
     * 插入数据(选择性插入)
     *
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> S insertSelective(S entity);


    /**
     * 插入数据(批量插入)
     *
     * @param entities
     * @param <S>
     * @return
     */
    <S extends T> int insertInBatch(List<S> entities);

    /**
     * 插入数据(选择性批量插入)
     *
     * @param entities
     * @param <S>
     * @return
     */
    <S extends T> int insertSelectiveInBatch(List<S> entities);

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
    int deleteInIds(List<ID> ids);

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
    int deleteInBatch(List<? extends T> entities);

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
    <S extends T> int updateById(ID id, S entity);

    /**
     * 选择性的更新数据(根据ID更新为目标实体)
     *
     * @param id
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int updateSelectiveById(ID id, S entity);

    /**
     * 更新数据(根据多个更新为目标实体)
     *
     * @param ids
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int updateInIds(List<ID> ids, S entity);

    /**
     * 选择性的更新数据(根据多个更新为目标实体)
     *
     * @param ids
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int updateSelectiveInIds(List<ID> ids, S entity);

    /**
     * 更新数据(按照原实体更新为目标实体)
     *
     * @param source
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int updateByEntity(S source, S entity);

    /**
     * 更新数据(选择性的更新数据,按照原实体更新为目标实体)
     *
     * @param source
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int updateSelectiveByEntity(S source, S entity);

    /**
     * 更新数据(更新所有的记录)
     *
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> int updateAll(S entity);

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
    List<T> findMultiByIds(List<ID> ids);

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
     * @param pageRequest
     * @return
     */
    Page<T> findPage(PageRequest pageRequest);

    /**
     * 统计数量(按照分页信息)
     *
     * @param pageRequest
     * @return
     */
    int findPageCount(PageRequest pageRequest);


    /**
     * 判断对象是否存在
     *
     * @param id
     * @return
     */
    boolean existsById(ID id);
}
