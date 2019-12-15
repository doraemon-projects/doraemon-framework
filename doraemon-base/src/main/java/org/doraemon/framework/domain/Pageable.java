package org.doraemon.framework.domain;

/**
 * created with IntelliJ IDEA.
 * description: 分页参数接口
 * author:      fengwenping
 * date:        2019/7/14 19:01
 */
public interface Pageable {

    int getPageNumber();

    int getPageSize();

    Sort getSort();
}
