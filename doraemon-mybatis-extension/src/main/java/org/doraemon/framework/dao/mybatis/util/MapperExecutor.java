package org.doraemon.framework.dao.mybatis.util;

import org.doraemon.framework.dao.mybatis.mapper.BaseMapper;

import java.io.Serializable;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-07-25 19:08
 */
@FunctionalInterface
public interface MapperExecutor<T, R, ID extends Serializable> {

    R execute(BaseMapper<T, ID> baseMapper);
}
