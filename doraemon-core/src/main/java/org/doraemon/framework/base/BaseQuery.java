package org.doraemon.framework.base;

import java.util.List;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 17:20
 */
public class BaseQuery<ID> extends BaseObject {
    protected List<ID> ids;

    public List<ID> getIds() {
        return ids;
    }

    public void setIds(List<ID> ids) {
        this.ids = ids;
    }
}
