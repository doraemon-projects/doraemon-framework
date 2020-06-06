package org.doraemon.framework.base.dao.mybatis;

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
        INSERT("insert", "插入数据(根据实体)"),
        INSERT_SELECTIVE("insertSelective", "插入数据(选择性插入)"),
        INSERT_IN_BATCH("insertInBatch", "插入数据(批量插入)"),
        INSERT_SELECTIVE_IN_BATCH("insertSelectiveInBatch", "插入数据(选择性批量插入)"),
        DELETE_BY_ID("deleteById", "删除数据(根据主键删除)"),
        DELETE_IN_IDS("deleteInIds", "删除数据(根据多个主键删除)"),
        DELETE_BY_ENTITY("deleteByEntity", "删除数据(根据实体删)"),
        DELETE_IN_BATCH("deleteInBatch", "删除数据(根据多个实体)"),
        DELETE_ALL("deleteAll", "删除数据(所有)"),
        UPDATE_BY_ID("updateById", "更新数据(根据ID更新为目标实体)"),
        UPDATE_SELECTIVE_BY_ID("updateSelectiveById", "选择性的更新数据(根据ID更新为目标实体)"),
        UPDATE_IN_IDS("updateInIds", "更新数据(根据多个更新为目标实体)"),
        UPDATE_SELECTIVE_IN_IDS("updateSelectiveInIds", "选择性的更新数据(根据多个更新为目标实体)"),
        UPDATE_BY_ENTITY("updateByEntity", "更新数据(按照原实体更新为目标实体)"),
        UPDATE_SELECTIVE_BY_ENTITY("updateSelectiveByEntity", "选择性的更新数据(按照原实体更新为目标实体)"),
        FIND_ONE_BY_ID("findOneById", "查询单条数据(根据ID查找)"),
        FIND_ONE_BY_ENTITY("findOneByEntity", "查询单条数据(根据实体)"),
        FIND_ALL("findAll", "查询多条数据(所有)"),
        FIND_MULTI_BY_ENTITY("findMultiByEntity", "查询多条数据(按照实体查询)"),
        FIND_MULTI_BY_IDS("findMultiByIds", "查询多条数据(根据多个ID查询)"),
        FIND_MULTI_BY_SORT("findMultiBySort", "查询多条数据(按照排序返回)"),
        FIND_COUNT("findCount", "统计数量(所有)"),
        FIND_COUNT_BY_ENTITY("findCountByEntity", "统计数量(按实体)"),
        FIND_PAGE("findPage", "查询多条数据(按照分页信息)"),
        FIND_PAGE_COUNT("findPageCount", "统计数量(按照分页信息)");


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
