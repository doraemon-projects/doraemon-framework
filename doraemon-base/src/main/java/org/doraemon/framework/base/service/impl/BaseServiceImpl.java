package org.doraemon.framework.base.service.impl;

import org.doraemon.framework.base.dao.BaseDao;
import org.doraemon.framework.base.service.BaseService;
import org.doraemon.framework.domain.Page;
import org.doraemon.framework.domain.PageRequest;
import org.doraemon.framework.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @description: 通用基础服务实现
 * @author: fengwenping
 * @date: 2020-05-31 18:43
 */
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    protected abstract BaseDao<T, ID> getDao();

    /**
     * 插入数据(根据实体)
     *
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> S insert(S entity) {
        this.getDao().insert(entity);
        return entity;
    }

    /**
     * 插入数据(选择性插入)
     *
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> S insertSelective(S entity) {
        this.getDao().insertSelective(entity);
        return entity;
    }


    /**
     * 插入数据(批量插入)
     *
     * @param entities
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> int insertInBatch(List<S> entities) {
        return this.getDao().insertInBatch(entities);
    }

    /**
     * 插入数据(选择性批量插入)
     *
     * @param entities
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> int insertSelectiveInBatch(List<S> entities) {
        return this.getDao().insertSelectiveInBatch(entities);
    }

    /**
     * 删除数据(根据主键删除)
     *
     * @param id
     */
    @Override
    public int deleteById(ID id) {
        return this.getDao().deleteById(id);
    }

    /**
     * 删除数据(根据多个主键删除)
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteInIds(List<ID> ids) {
        return this.getDao().deleteInIds(ids);
    }

    /**
     * 删除数据(根据实体删)
     *
     * @param entity
     * @return
     */
    @Override
    public int deleteByEntity(T entity) {
        return this.getDao().deleteByEntity(entity);
    }

    /**
     * 删除数据(根据多个实体)
     *
     * @param entities
     * @return
     */
    @Override
    public int deleteInBatch(List<? extends T> entities) {
        return this.getDao().deleteInBatch(entities);
    }

    /**
     * 删除数据(所有)
     *
     * @return
     */
    @Override
    public int deleteAll() {
        return this.getDao().deleteAll();
    }

    /**
     * 更新数据(根据ID更新为目标实体)
     *
     * @param id
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> int updateById(ID id, S entity) {
        return this.getDao().updateById(id, entity);
    }

    /**
     * 选择性的更新数据(根据ID更新为目标实体)
     *
     * @param id
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> int updateSelectiveById(ID id, S entity) {
        return this.getDao().updateSelectiveById(id, entity);
    }

    /**
     * 更新数据(根据多个更新为目标实体)
     *
     * @param ids
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> int updateInIds(List<ID> ids, S entity) {
        return this.getDao().updateInIds(ids, entity);
    }

    /**
     * 选择性的更新数据(根据多个更新为目标实体)
     *
     * @param ids
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> int updateSelectiveInIds(List<ID> ids, S entity) {
        return this.getDao().updateSelectiveInIds(ids, entity);
    }

    /**
     * 更新数据(按照原实体更新为目标实体)
     *
     * @param source
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> int updateByEntity(S source, S entity) {
        return this.getDao().updateByEntity(source, entity);
    }

    /**
     * 选择性的更新数据(按照原实体更新为目标实体)
     *
     * @param source
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> int updateSelectiveByEntity(S source, S entity) {
        return this.getDao().updateSelectiveByEntity(source, entity);
    }

    /**
     * 更新数据(更新所有的记录)
     *
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> int updateAll(S entity) {
        return this.getDao().updateAll(entity);
    }

    /**
     * 查询单条数据(根据ID查找)
     *
     * @param id
     * @return
     */
    @Override
    public <S extends T> S findOneById(ID id) {
        return this.getDao().findOneById(id);
    }

    /**
     * 查询单条数据(根据实体)
     *
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> T findOneByEntity(S entity) {
        return this.getDao().findOneByEntity(entity);
    }

    /**
     * 查询多条数据(所有)
     *
     * @return
     */
    @Override
    public List<T> findAll() {
        return this.getDao().findAll();
    }

    /**
     * 查询多条数据(按照实体查询)
     *
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> List<T> findMultiByEntity(S entity) {
        return this.getDao().findMultiByEntity(entity);
    }

    /**
     * 查询多条数据(根据多个ID查询)
     *
     * @param ids
     * @return
     */
    @Override
    public List<T> findMultiByIds(List<ID> ids) {
        return this.getDao().findMultiByIds(ids);
    }

    /**
     * 查询多条数据(按照排序返回)
     *
     * @param sort
     * @return
     */
    @Override
    public List<T> findMultiBySort(Sort sort) {
        return this.getDao().findMultiBySort(sort);
    }

    /**
     * 统计数量(所有)
     *
     * @return
     */
    @Override
    public int findCount() {
        return this.getDao().findCount();
    }

    /**
     * 统计数量(按实体)
     *
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends T> int findCountByEntity(S entity) {
        return this.getDao().findCountByEntity(entity);
    }

    /**
     * 查询多条数据(按照分页信息)
     *
     * @param pageRequest
     * @return
     */
    @Override
    public Page<T> findPage(PageRequest pageRequest) {
        return this.getDao().findPage(pageRequest);
    }

    /**
     * 统计数量(按照分页信息)
     *
     * @param pageRequest
     * @return
     */
    @Override
    public int findPageCount(PageRequest pageRequest) {
        return this.getDao().findPageCount(pageRequest);
    }

    /**
     * 判断对象是否存在
     *
     * @param id
     * @return
     */
    @Override
    public boolean existsById(ID id) {
        return Objects.nonNull(this.findOneById(id));
    }
}
