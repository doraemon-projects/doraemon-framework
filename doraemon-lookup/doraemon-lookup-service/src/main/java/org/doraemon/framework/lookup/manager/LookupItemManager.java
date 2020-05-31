package org.doraemon.framework.lookup.manager;

import org.doraemon.framework.lookup.bean.LookupItem;
import org.doraemon.framework.lookup.mapper.LookupItemMapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-30 18:12
 */
@Named("lookupItemManager")
public class LookupItemManager {

    @Inject
    private LookupItemMapper lookupItemMapper;

    public LookupItemManager() {
    }

    public LookupItem create(LookupItem lookupItem) {
        this.lookupItemMapper.insert(lookupItem);
        return lookupItem;
    }

    public boolean modify(LookupItem lookupItem) {
        final int i = this.lookupItemMapper.updateById(lookupItem.getId(), lookupItem);
        return i > 0;
    }

    public boolean delete(String id) {
        final int i = this.lookupItemMapper.deleteById(id);
        return i > 0;
    }

    public LookupItem queryById(String id) {
        return this.lookupItemMapper.findOneById(id);
    }

    public List<LookupItem> queryByClassifyCode(String classifyCode) {
        LookupItem lookupItem = new LookupItem();
        lookupItem.setClassifyCode(classifyCode);
        final List<LookupItem> lookupItems = this.lookupItemMapper.findMultiByEntity(lookupItem);
        return lookupItems;
    }

    /**
     * 根据父类Code和父Code查询列表
     *
     * @param classifyParentCode 父类Code
     * @param parentCode         父Code
     * @return
     */
    public List<LookupItem> queryByClassifyAndParentCode(String classifyParentCode, String parentCode) {
        LookupItem lookupItem = new LookupItem();
        lookupItem.setClassifyParentCode(classifyParentCode);
        lookupItem.setParentCode(parentCode);
        final List<LookupItem> lookupItems = this.lookupItemMapper.findMultiByEntity(lookupItem);
        return lookupItems;
    }
}
