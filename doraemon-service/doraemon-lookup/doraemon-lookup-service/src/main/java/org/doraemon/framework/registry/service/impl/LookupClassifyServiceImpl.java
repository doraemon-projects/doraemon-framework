package org.doraemon.framework.registry.service.impl;

import org.doraemon.framework.exception.ApplicationException;
import org.doraemon.framework.registry.bean.LookupClassify;
import org.doraemon.framework.registry.service.LookupClassifyService;
import org.doraemon.framework.registry.vo.LookupClassifyVO;
import org.doraemon.framework.util.BeanUtils;

import javax.inject.Named;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 17:17
 */
@Named(value = "lookupClassifyServiceImpl")
public class LookupClassifyServiceImpl extends LookupClassifyQueryServiceImpl implements LookupClassifyService {

    @Override
    public LookupClassifyVO create(LookupClassifyVO lookupClassifyVO) throws ApplicationException {
        final LookupClassify lookupClassify = BeanUtils.copyProperties(lookupClassifyVO, LookupClassify.class);
        super.insert(lookupClassify);
        return lookupClassifyVO;
    }
}
