package org.doraemon.framework.lookup.web.controller;

import org.doraemon.framework.domain.Page;
import org.doraemon.framework.lookup.bean.LookupClassify;
import org.doraemon.framework.lookup.service.LookupClassifyService;
import org.doraemon.framework.lookup.web.model.request.ClassifyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-31 18:10
 */
@RestController
@RequestMapping("/lookup/classifies")
public class LookupClassifyController {

    private final LookupClassifyService lookupClassifyService;

    public LookupClassifyController(LookupClassifyService lookupClassifyService) {
        this.lookupClassifyService = lookupClassifyService;
    }

    @GetMapping("/page/")
    public Page<LookupClassify> queryPage(@RequestBody ClassifyRequest request) {
        this.lookupClassifyService
    }
}
