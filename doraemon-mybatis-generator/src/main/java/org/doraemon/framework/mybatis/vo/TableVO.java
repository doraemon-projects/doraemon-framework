package org.doraemon.framework.mybatis.vo;

import org.doraemon.framework.util.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * @author: fengwenping
 * Description:
 * Date: 2018-10-26
 * Time: 下午8:43
 */
public class TableVO implements Serializable {

    private String tableSchema;
    private String tableName;
    private String tableComment;
    private List<ColumnVO> columns;

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

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<ColumnVO> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnVO> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
