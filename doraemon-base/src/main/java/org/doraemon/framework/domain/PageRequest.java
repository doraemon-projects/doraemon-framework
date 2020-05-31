package org.doraemon.framework.domain;

import java.io.Serializable;

/**
 * created with IntelliJ IDEA.
 * description: 分页请求参数
 * author:      fengwenping
 * date:        2019/7/14 19:34
 */
public class PageRequest implements Pageable, Serializable {

    private final int pageNumber;
    private final int pageSize;
    private Sort sort;

    public PageRequest() {
        this.pageNumber = 0;
        this.pageSize = 20;
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

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public Sort getSort() {
        return sort;
    }
}
