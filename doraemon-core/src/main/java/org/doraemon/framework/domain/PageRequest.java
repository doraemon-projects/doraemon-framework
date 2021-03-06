package org.doraemon.framework.domain;

import java.io.Serializable;

/**
 * created with IntelliJ IDEA.
 * description: 分页请求参数
 * author:      fengwenping
 * date:        2019/7/14 19:34
 */
public class PageRequest implements Serializable {
    public static final int DEFAULT_PAGE_SIZE = 20;
    private final int pageNumber;
    private final int pageSize;
    private Sort sort;

    public PageRequest() {
        this.pageNumber = 0;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public PageRequest(int pageNumber, int pageSize) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }
        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public PageRequest(int pageNumber, int pageSize, Sort.Direction direction, String... properties) {
        this(pageNumber, pageSize, new Sort(direction, properties));
    }

    public PageRequest(int pageNumber, int pageSize, Sort sort) {
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
