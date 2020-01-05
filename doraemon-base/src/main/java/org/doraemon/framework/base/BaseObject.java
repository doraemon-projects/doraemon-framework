package org.doraemon.framework.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 基础数据对象
 * Author:      fengwenping
 * Date:        2019/12/15 19:00
 */
public class BaseObject implements Serializable {

    private String id;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
