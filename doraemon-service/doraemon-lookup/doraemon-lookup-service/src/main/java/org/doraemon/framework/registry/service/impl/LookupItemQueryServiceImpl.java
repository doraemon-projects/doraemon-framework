package org.doraemon.framework.registry.service.impl;

import org.doraemon.framework.base.dao.BaseDao;
import org.doraemon.framework.base.service.impl.BaseServiceImpl;
import org.doraemon.framework.exception.ApplicationException;
import org.doraemon.framework.registry.bean.LookupItem;
import org.doraemon.framework.registry.dao.LookupItemDao;
import org.doraemon.framework.registry.service.LookupItemQueryService;
import org.doraemon.framework.registry.vo.LookupItemVO;
import org.doraemon.framework.util.BeanUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 18:12
 */
@Named(value = "lookupItemQueryServiceImpl")
public class LookupItemQueryServiceImpl extends BaseServiceImpl<LookupItem, String> implements LookupItemQueryService {

    @Inject
    private LookupItemDao lookupItemDao;

    @Override
    public BaseDao<LookupItem, String> getDao() {
        return this.lookupItemDao;
    }

    @Override
    public List<LookupItemVO> queryLookupItems(LookupItemVO lookupItemVO) throws ApplicationException {
        final List<LookupItem> lookupItems = this.getDao().findMultiByEntity(BeanUtils.copyProperties(lookupItemVO, LookupItem.class));
        return BeanUtils.copyPropertiesOfList(lookupItems, LookupItemVO.class);
    }
}
