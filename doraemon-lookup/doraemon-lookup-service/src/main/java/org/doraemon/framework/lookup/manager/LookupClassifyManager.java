package org.doraemon.framework.lookup.manager;

import org.doraemon.framework.lookup.mapper.LookupClassifyMapper;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-30 18:10
 */
@Named("lookupClassifyManager")
public class LookupClassifyManager {

    @Inject
    private LookupClassifyMapper lookupClassifyMapper;
}
