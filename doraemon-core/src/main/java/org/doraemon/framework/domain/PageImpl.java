package org.doraemon.framework.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * created with IntelliJ IDEA.
 * description: 分页实体
 * author:      fengwenping
 * date:        2019/7/14 19:05
 */
public class PageImpl<T> implements Page<T> {

    private final List<T> content = new ArrayList<>();
    private PageRequest pageRequest;
    private long total = 0L;

    public PageImpl(List<T> content, PageRequest pageRequest, long total) {
        this(content, pageRequest);
        this.total = total;
    }

    public PageImpl(List<T> content, PageRequest pageRequest) {
        if (content == null) {
            throw new IllegalArgumentException("Content must not be null!");
        }
        this.content.addAll(content);
        this.pageRequest = pageRequest;
    }

    public PageImpl(List<T> content) {
        this(content, null, null == content ? 0 : content.size());
    }

    @Override
    public int getNumber() {
        return pageRequest == null ? 0 : pageRequest.getPageNumber();
    }

    @Override
    public int getSize() {
        return pageRequest == null ? 0 : pageRequest.getPageSize();
    }

    @Override
    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    @Override
    public Sort getSort() {
        return pageRequest == null ? null : pageRequest.getSort();
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
