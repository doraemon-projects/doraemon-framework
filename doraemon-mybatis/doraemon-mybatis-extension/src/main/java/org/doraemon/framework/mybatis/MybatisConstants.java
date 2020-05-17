package org.doraemon.framework.mybatis;

import org.doraemon.framework.Constants;
import org.doraemon.framework.base.IEnumProvider;

/**
 * @description: MyBatis常量
 * @author: fengwenping
 * @date: 2020-05-17 18:33
 */
public class MybatisConstants extends Constants {

    /**
     * MyBatis通用操作枚举
     */
    public enum UniversalMethod implements IEnumProvider<String> {
        insert("insert", "插入数据(根据实体)"),
        insertSelective("insertSelective", "插入数据(选择性插入)"),
        insertInBatch("insertInBatch", "插入数据(批量插入)"),
        insertSelectiveInBatch("insertSelectiveInBatch", "插入数据(选择性批量插入)"),
        deleteById("deleteById", "删除数据(根据主键删除)"),
        deleteInIds("deleteInIds", "删除数据(根据多个主键删除)"),
        delete("delete", "删除数据(根据实体删)"),
        deleteInBatch("deleteInBatch", "删除数据(根据多个实体)"),
        deleteAll("deleteAll", "删除数据(所有)"),
        updateById("updateById", "更新数据(根据ID更新为目标实体)"),
        updateSelectiveById("updateSelectiveById", "选择性的更新数据(根据ID更新为目标实体)"),
        updateInIds("updateInIds", "更新数据(根据多个更新为目标实体)"),
        updateSelectiveInIds("updateSelectiveInIds", "选择性的更新数据(根据多个更新为目标实体)"),
        updateByEntity("updateByEntity", "更新数据(按照原实体更新为目标实体)"),
        updateSelectiveByEntity("updateSelectiveByEntity", "选择性的更新数据(按照原实体更新为目标实体)"),
        findOneById("findOneById", "查询单条数据(根据ID查找)"),
        findOneByEntity("findOneByEntity", "查询单条数据(根据实体)"),
        findAll("findAll", "查询多条数据(所有)"),
        findMultiByEntity("findMultiByEntity", "查询多条数据(按照实体查询)"),
        findMultiByIds("findMultiByIds", "查询多条数据(根据多个ID查询)"),
        findMultiBySort("findMultiBySort", "查询多条数据(按照排序返回)"),
        findCount("findCount", "统计数量(所有)"),
        findCountByEntity("findCountByEntity", "统计数量(按实体)"),
        findPage("findPage", "查询多条数据(按照分页信息)"),
        findPageCount("findPageCount", "统计数量(按照分页信息)");


        private String code;
        private String name;

        UniversalMethod(String code, String name) {
            this.code = code;
            this.name = name;
        }

        @Override
        public String getCode() {
            return this.code;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
