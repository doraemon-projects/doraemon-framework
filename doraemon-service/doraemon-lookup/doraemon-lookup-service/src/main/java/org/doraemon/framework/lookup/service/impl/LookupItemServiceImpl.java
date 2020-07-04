package org.doraemon.framework.lookup.service.impl;

import org.doraemon.framework.exception.ApplicationException;
import org.doraemon.framework.exception.BusinessException;
import org.doraemon.framework.lookup.bean.LookupItem;
import org.doraemon.framework.lookup.service.LookupItemService;
import org.doraemon.framework.lookup.vo.LookupItemVO;
import org.doraemon.framework.util.BeanUtils;

import javax.inject.Named;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 18:11
 */
@Named(value = "lookupItemServiceImpl")
public class LookupItemServiceImpl extends LookupItemQueryServiceImpl implements LookupItemService {

    @Override
    public LookupItemVO create(LookupItemVO lookupItemVO) throws ApplicationException {
        final LookupItem lookupItem = BeanUtils.copyProperties(lookupItemVO, LookupItem.class);
        final LookupItem item = this.insert(lookupItem);
        return BeanUtils.copyProperties(item, LookupItemVO.class);
    }

    @Override
    public void modify(LookupItemVO lookupItemVO) throws ApplicationException {
        final LookupItem lookupItem = BeanUtils.copyProperties(lookupItemVO, LookupItem.class);
        BusinessException.assertEquals(this.updateSelectiveById(lookupItemVO.getId(), lookupItem), 1);
    }

    @Override
    public void delete(String id) throws ApplicationException {
        BusinessException.assertEquals(this.deleteById(id), 1);
    }
}
