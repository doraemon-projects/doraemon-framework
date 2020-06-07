package org.doraemon.framework.mybatis.vo;

import org.doraemon.framework.util.JSON;

import java.io.Serializable;

/**
 * @author: fengwenping
 * Description:
 * Date: 2018-10-26
 * Time: 下午9:53
 */
public class ColumnVO implements Serializable {
    private String tableSchema;
    private String tableName;
    private String columnName;
    private String columnComment;
    private String dataType;
    private Integer ordinalPosition;

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
