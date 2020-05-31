package org.doraemon.framework.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 基础数据对象
 * Author:      fengwenping
 * Date:        2019/12/15 19:00
 */
@Data
public class BaseObject<T> implements Serializable {

    private T id;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
}
