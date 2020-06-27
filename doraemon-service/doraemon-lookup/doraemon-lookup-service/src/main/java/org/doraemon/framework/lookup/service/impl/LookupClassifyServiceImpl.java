package org.doraemon.framework.lookup.service.impl;

import org.doraemon.framework.exception.ApplicationException;
import org.doraemon.framework.lookup.bean.LookupClassify;
import org.doraemon.framework.lookup.service.LookupClassifyService;

import javax.inject.Named;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 17:17
 */
@Named(value = "lookupClassifyServiceImpl")
public class LookupClassifyServiceImpl extends LookupClassifyQueryServiceImpl implements LookupClassifyService {
    @Override
    public LookupClassify create(LookupClassify lookupClassify) throws ApplicationException {
        return super.insert(lookupClassify);
    }
}
