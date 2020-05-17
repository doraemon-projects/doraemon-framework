package org.doraemon.framework.lookup.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * lookup_classify
 * @author 
 */
public class LookupClassify implements Serializable {
    private Long classifyId;

    private Long createdBy;

    private Date createdTime;

    private Long lastUpdatedBy;

    private Date lastUpdatedTime;

    private String classifyCode;

    private String classifyDesc;

    private String classifyParentCode;

    private String name;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

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