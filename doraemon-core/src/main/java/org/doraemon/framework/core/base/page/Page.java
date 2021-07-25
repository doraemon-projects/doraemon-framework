package org.doraemon.framework.core.base.page;

import java.util.List;

/**
 * created with IntelliJ IDEA.
 * description: 分页接口
 * author:      fengwenping
 * date:        2019/7/14 19:03
 */
public interface Page<T> {

    /**
     * 当前页码
     *
     * @return
     */
    int getNumber();

    /**
     * 每页行数
     *
     * @return
     */
    int getSize();

    /**
     * 当前页内容
     *
     * @return
     */
    List<T> getContent();

    /**
     * 排序条件
     *
     * @return
     */
    Sort getSort();

    /**
     * 总页数
     *
     * @return
     */
    int getTotalPages();

    /**
     * 总记录数
     *
     * @return
     */
    long getTotalElements();

    /**
     * 当前页记录数
     *
     * @return
     */
    int getPageElements();
}
