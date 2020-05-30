package org.doraemon.framework.lookup.manager;

import org.doraemon.framework.lookup.mapper.LookupClassifyMapper;

import javax.inject.Named;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-30 18:12
 */
@Named("lookupItemManager")
public class LookupItemManager {

    private final LookupItemMapper lookupItemMapper;

    private final LookupClassifyMapper lookupClassifyMapper;

    public LookupItemManager(LookupItemMapper lookupItemMapper,
                             LookupClassifyMapper lookupClassifyMapper) {
        this.lookupItemMapper = lookupItemMapper;
        this.lookupClassifyMapper = lookupClassifyMapper;
    }
}
