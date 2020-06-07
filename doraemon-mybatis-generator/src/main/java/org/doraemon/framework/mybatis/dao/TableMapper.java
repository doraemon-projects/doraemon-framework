package org.doraemon.framework.mybatis.dao;

import org.apache.ibatis.annotations.Param;
import org.doraemon.framework.mybatis.vo.ColumnVO;
import org.doraemon.framework.mybatis.vo.TableVO;

import java.util.List;

/**
 * created with IntelliJ IDEA.
 * description: 通过sql获取表结构
 * author:      fengwenping
 * date:        2019/7/7 23:38
 */
public interface TableMapper {

    /**
     * 根据tableSchema和tableNames集合查询所有的表
     *
     * @param tableSchema
     * @param tableNames
     * @return
     */
    List<TableVO> findTables(@Param("tableSchema") String tableSchema, @Param("tableNames") List<String> tableNames);

    /**
     * 根据tableSchema和tableName查询所有的列集合
     *
     * @param tableSchema
     * @param tableName
     * @return
     */
    List<ColumnVO> findColumns(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);
}
