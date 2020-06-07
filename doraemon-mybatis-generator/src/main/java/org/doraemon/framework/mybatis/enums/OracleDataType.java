package org.doraemon.framework.mybatis.enums;

import java.util.Arrays;

/**
 * created with IntelliJ IDEA.
 * description: oracle数据类型与java数据类型映射
 * author:      fengwenping
 * date:        2019/7/1 0:15
 */
public enum OracleDataType implements EnumProvider {

    VARCHAR2("VARCHAR2", "java.lang.String"),
    CHAR("CHAR", "java.lang.String"),
    DATE("DATE", "java.lang.Date"),
    NUMBER("INTEGER", "java.lang.Integer");

    String jdbcType;
    String javaType;

    OracleDataType(String jdbcType, String javaType) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    public static OracleDataType fromString(String jdbcType) {
        return Arrays.stream(OracleDataType.values()).filter(dataType -> dataType.jdbcType.equalsIgnoreCase(jdbcType)).findFirst().get();
    }

    @Override
    public String getCode() {
        return this.jdbcType;
    }

    @Override
    public String getDesc() {
        return this.javaType;
    }
}
