package org.doraemon.framework.base;

import org.doraemon.framework.domain.Pageable;

import java.util.List;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 17:20
 */
public class BaseQuery<ID> {
    private ID id;
    private List<ID> ids;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public List<ID> getIds() {
        return ids;
    }

    public void setIds(List<ID> ids) {
        this.ids = ids;
    }
}
