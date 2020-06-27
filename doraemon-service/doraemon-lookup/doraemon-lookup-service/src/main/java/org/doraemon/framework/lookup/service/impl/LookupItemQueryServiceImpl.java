package org.doraemon.framework.lookup.service.impl;

import org.doraemon.framework.base.dao.BaseMapper;
import org.doraemon.framework.base.service.impl.BaseServiceImpl;
import org.doraemon.framework.lookup.bean.LookupItem;
import org.doraemon.framework.lookup.dao.LookupItemDao;
import org.doraemon.framework.lookup.service.LookupItemQueryService;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 18:12
 */
@Named(value = "lookupItemQueryServiceImpl")
public class LookupItemQueryServiceImpl extends BaseServiceImpl<LookupItem, Integer> implements LookupItemQueryService {

    @Inject
    private LookupItemDao lookupItemDao;

    @Override
    public BaseMapper<LookupItem, Integer> getDao() {
        return this.lookupItemDao;
    }
}
