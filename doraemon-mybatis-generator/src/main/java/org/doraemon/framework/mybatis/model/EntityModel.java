package org.doraemon.framework.mybatis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * created with IntelliJ IDEA.
 * description: 描述
 * author:      fengwenping
 * date:        2019/6/30 23:45
 */
public class EntityModel implements Serializable {

    private String packageName;
    private String name;
    private String tableName;
    private String comment;
    private List<FieldModel> fields = new ArrayList<>();

    public EntityModel(String packageName, String name, String tableName, String comment) {
        this.packageName = packageName;
        this.name = name;
        this.tableName = tableName;
        this.comment = comment;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<FieldModel> getFields() {
        return fields;
    }

    public void setFields(List<FieldModel> fields) {
        this.fields = fields;
    }
}
