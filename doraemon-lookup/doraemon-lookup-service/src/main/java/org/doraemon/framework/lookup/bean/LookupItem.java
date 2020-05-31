package org.doraemon.framework.lookup.bean;

import lombok.Data;
import org.doraemon.framework.base.BaseObject;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-31 16:09
 */
@Data
public class LookupItem extends BaseObject<String> {

    private String classifyCode;
    private String classifyParentCode;
    private String classifyParentName;
    private String itemAttr1;
    private String itemAttr2;
    private String itemAttr3;
    private String itemAttr4;
    private String itemAttr5;
    private String itemAttr6;
    private String itemCode;
    private String itemDesc;
    private Integer itemIndex;
    private String itemName;
    private String parentCode;
    private Integer status;
}
