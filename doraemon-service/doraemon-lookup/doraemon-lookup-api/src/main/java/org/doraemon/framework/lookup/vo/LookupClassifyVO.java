package org.doraemon.framework.lookup.vo;

import org.doraemon.framework.base.BaseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-06-26 17:39
 */
public class LookupClassifyVO extends BaseObject<String> {

    private String classifyCode;

    private String classifyDesc;

    private String classifyParentCode;

    private String name;

    private Integer status;

    private List<LookupItemVO> items = new ArrayList<>();

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

    public List<LookupItemVO> getItems() {
        return items;
    }

    public void setItems(List<LookupItemVO> items) {
        this.items = items;
    }
}
