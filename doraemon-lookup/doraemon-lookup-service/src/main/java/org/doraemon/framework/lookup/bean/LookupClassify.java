package org.doraemon.framework.lookup.bean;

import lombok.Data;
import org.doraemon.framework.base.BaseObject;

/**
 * lookup_classify
 *
 * @author
 */
@Data
public class LookupClassify extends BaseObject {

    private String classifyCode;

    private String classifyDesc;

    private String classifyParentCode;

    private String name;

    private Integer status;
}