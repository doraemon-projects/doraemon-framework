package org.doraemon.framework.core.base.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * created with IntelliJ IDEA.
 * description: 分页实体
 * author:      fengwenping
 * date:        2019/7/14 19:05
 */
public class PageResult<T> implements Page<T> {

    private final List<T> content = new ArrayList<>();
    private PageParam pageParam;
    private long total = 0L;

    public PageResult(List<T> content, PageParam pageParam, long total) {
        this(content, pageParam);
        this.total = total;
    }

    public PageResult(List<T> content, PageParam pageParam) {
        if (content == null) {
            throw new IllegalArgumentException("Content must not be null!");
        }
        this.content.addAll(content);
        this.pageParam = pageParam;
    }

    public PageResult(List<T> content) {
        this(content, null, null == content ? 0 : content.size());
    }

    @Override
    public int getNumber() {
        return pageParam == null ? 0 : pageParam.getPageNumber();
    }

    @Override
    public int getSize() {
        return pageParam == null ? 0 : pageParam.getPageSize();
    }

    @Override
    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    @Override
    public Sort getSort() {
        return pageParam == null ? null : pageParam.getSort();
    }

    @Override
    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    @Override
    public long getTotalElements() {
        return total;
    }

    @Override
    public int getPageElements() {
        return content.size();
    }
}
