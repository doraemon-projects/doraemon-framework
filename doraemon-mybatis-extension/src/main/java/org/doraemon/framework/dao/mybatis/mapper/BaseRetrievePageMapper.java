package org.doraemon.framework.dao.mybatis.mapper;

import org.jfteam.common.domain.Page;
import org.jfteam.common.domain.Pageable;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-07-24 18:20
 */
public interface BaseRetrievePageMapper<T> {

    /**
     * 查询多条数据(按照分页信息)
     *
     * @param pageable
     * @return
     */
    Page<T> findPage(Pageable pageable);
}
