package org.doraemon.framework.registry.service.impl;

import org.doraemon.framework.registry.service.LookupItemQueryService;
import org.doraemon.framework.registry.service.RegistryQueryService;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 18:12
 */
@Named(value = "registryQueryServiceImpl")
public class RegistryQueryServiceImpl implements RegistryQueryService {

    @Inject
    private LookupItemQueryService lookupItemQueryService;
}
