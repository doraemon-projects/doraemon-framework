package org.doraemon.framework.registry.service.impl;

import org.doraemon.framework.exception.ApplicationException;
import org.doraemon.framework.registry.service.LookupItemQueryService;
import org.doraemon.framework.registry.service.RegistryQueryService;
import org.doraemon.framework.registry.vo.LookupItemVO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-04-12 18:12
 */
@Named(value = "registryQueryServiceImpl")
public class RegistryQueryServiceImpl implements RegistryQueryService {

    @Inject
    @Named("lookupItemQueryServiceImpl")
    private LookupItemQueryService lookupItemQueryService;

    @Override
    public Object queryConfig(String appId, String configCode) throws ApplicationException {
        LookupItemVO lookupItemVO = new LookupItemVO();
        lookupItemVO.setClassifyCode(appId);
        final List<LookupItemVO> lookupItemVOS = lookupItemQueryService.queryLookupItems(lookupItemVO);
        final LookupItemVO itemVO = Optional.ofNullable(lookupItemVOS).orElse(Collections.emptyList()).stream()
                .filter(lookupItemVO1 -> configCode.equalsIgnoreCase(lookupItemVO1.getItemCode()))
                .findFirst().orElse(new LookupItemVO());
        return itemVO;
    }
}
