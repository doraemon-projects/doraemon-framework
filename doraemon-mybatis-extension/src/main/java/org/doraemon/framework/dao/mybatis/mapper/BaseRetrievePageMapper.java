package org.doraemon.framework.dao.mybatis.mapper;

import org.doraemon.framework.core.base.page.PageParam;
import org.doraemon.framework.core.base.page.PageResult;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-07-24 18:20
 */
public interface BaseRetrievePageMapper<T> {

    /**
     * 查询多条数据(按照分页信息)
     *
     * @param pageParam
     * @return
     */
    PageResult<T> findPage(PageParam pageParam);

    /**
     * 统计数量(按照分页信息)
     *
     * @param pageParam
     * @return
     */
    int findPageCount(PageParam pageParam);
}
