package org.doraemon.framework.dao.mybatis.mapper;

import java.io.Serializable;

/**
 * @description: 基本的增删改查操作
 * @author: fengwenping
 * @date: 2019-07-12 21:25
 */
public interface BaseMapper<T, ID extends Serializable> extends BaseCreateMapper, BaseRetrieveMapper, BaseUpdateMapper, BaseDeleteMapper {
}
