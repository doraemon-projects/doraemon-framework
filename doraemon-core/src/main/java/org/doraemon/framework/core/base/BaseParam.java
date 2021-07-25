package org.doraemon.framework.core.base;

import java.util.List;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 17:20
 */
public class BaseParam<ID> extends BaseDO {
    protected List<ID> ids;

    public List<ID> getIds() {
        return ids;
    }

    public void setIds(List<ID> ids) {
        this.ids = ids;
    }
}
