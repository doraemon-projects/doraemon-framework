package org.doraemon.framework.lookup.service.impl;

import org.doraemon.framework.Constants;
import org.doraemon.framework.base.dao.BaseDao;
import org.doraemon.framework.base.service.impl.BaseServiceImpl;
import org.doraemon.framework.exception.ApplicationException;
import org.doraemon.framework.exception.ApplicationRuntimeException;
import org.doraemon.framework.lookup.bean.LookupClassify;
import org.doraemon.framework.lookup.bean.LookupItem;
import org.doraemon.framework.lookup.dao.LookupClassifyDao;
import org.doraemon.framework.lookup.dao.LookupItemDao;
import org.doraemon.framework.lookup.service.LookupClassifyQueryService;
import org.doraemon.framework.lookup.vo.LookupClassifyVO;
import org.doraemon.framework.util.BeanUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 16:18
 */
@Named(value = "lookupClassifyQueryServiceImpl")
public class LookupClassifyQueryServiceImpl extends BaseServiceImpl<LookupClassify, String> implements LookupClassifyQueryService {

    @Inject
    private LookupClassifyDao lookupClassifyDao;

    @Inject
    private LookupItemDao lookupItemDao;

    @Override
    public BaseDao<LookupClassify, String> getDao() {
        return this.lookupClassifyDao;
    }

    @Override
    public LookupClassifyVO findById(String id) throws ApplicationException {
        return BeanUtils.copyProperties(this.findOneById(id), LookupClassifyVO.class);
    }

    @Override
    public LookupClassifyVO findByCode(String classifyCode) throws ApplicationException {
        LookupClassify lookupClassify = new LookupClassify();
        lookupClassify.setClassifyCode(classifyCode);
        final LookupClassify classify = this.findOneByEntity(lookupClassify);
        if (classify == null || Objects.equals(classify.getStatus(), 0)) {
            throw new ApplicationRuntimeException(Constants.ResultCode.EXP_RECORD_INVALID);
        }
        LookupItem lookupItem = new LookupItem();
        lookupItem.setClassifyCode(classifyCode);
        final List<LookupItem> multiByEntity = this.lookupItemDao.findMultiByEntity(lookupItem);
        LookupClassifyVO lookupClassifyVO = new LookupClassifyVO();
        return null;
    }
}
