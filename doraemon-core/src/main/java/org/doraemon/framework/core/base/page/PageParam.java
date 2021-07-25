package org.doraemon.framework.core.base.page;

import java.io.Serializable;

/**
 * created with IntelliJ IDEA.
 * description: 分页请求参数
 * author:      fengwenping
 * date:        2019/7/14 19:34
 */
public class PageParam implements Serializable {
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;
    private final int pageNumber;
    private final int pageSize;
    private Sort sort;

    public PageParam() {
        this.pageNumber = DEFAULT_PAGE_NUMBER;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public PageParam(int pageNumber, int pageSize) {
        if (pageNumber < DEFAULT_PAGE_NUMBER) {
            throw new IllegalArgumentException("Page index must not be less than one!");
        }
        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public PageParam(int pageNumber, int pageSize, Sort.Direction direction, String... properties) {
        this(pageNumber, pageSize, new Sort(direction, properties));
    }

    public PageParam(int pageNumber, int pageSize, Sort sort) {
        this(pageNumber, pageSize);
        this.sort = sort;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public Sort getSort() {
        return sort;
    }
}
