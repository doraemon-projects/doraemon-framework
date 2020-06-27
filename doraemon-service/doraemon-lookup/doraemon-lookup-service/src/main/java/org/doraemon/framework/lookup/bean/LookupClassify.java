package org.doraemon.framework.lookup.bean;

import org.doraemon.framework.base.BaseObject;

/**
 * lookup_classify
 *
 * @author
 */
public class LookupClassify extends BaseObject<String> {

    private String classifyCode;

    private String classifyDesc;

    private String classifyParentCode;

    private String name;

    private Integer status;

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getClassifyDesc() {
        return classifyDesc;
    }

    public void setClassifyDesc(String classifyDesc) {
        this.classifyDesc = classifyDesc;
    }

    public String getClassifyParentCode() {
        return classifyParentCode;
    }

    public void setClassifyParentCode(String classifyParentCode) {
        this.classifyParentCode = classifyParentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}