package org.doraemon.framework.dao.mybatis.mapper;


import java.io.Serializable;

/**
 * @description: 基本的数据库检索操作
 * @author: fengwenping
 * @date: 2021-07-24 15:51
 */
public interface BaseRetrieveMapper<T, ID extends Serializable> extends
        BaseRetrieveSingleMapper,
        BaseRetrieveCountMapper,
        BaseRetrieveMultiMapper,
        BaseRetrievePageMapper {

}
