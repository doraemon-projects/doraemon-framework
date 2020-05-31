package org.doraemon.framework.lookup.web.controller;

import org.doraemon.framework.lookup.service.LookupClassifyService;
import org.doraemon.framework.lookup.service.LookupItemService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-31 18:11
 */
@RestController
public class LookupItemController {

    private final LookupItemService lookupItemService;

    private final LookupClassifyService lookupClassifyService;

    public LookupItemController(LookupItemService lookupItemService,
                                LookupClassifyService lookupClassifyService) {
        this.lookupItemService = lookupItemService;
        this.lookupClassifyService = lookupClassifyService;
    }
}
