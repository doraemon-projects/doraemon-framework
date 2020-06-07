package org.doraemon.framework.mybatis.enums;

import java.util.Arrays;

/**
 * created with IntelliJ IDEA.
 * description: mysql数据类型与java数据类型映射
 * author:      fengwenping
 * date:        2019/6/30 23:55
 */
public enum MysqlDataType implements EnumProvider {
    BIT("BIT", "java.lang.Boolean"),
    VARCHAR("VARCHAR", "java.lang.String"),
    CHAR("CHAR", "java.lang.String"),
    TEXT("TEXT", "java.lang.String"),
    BLOB("BLOB", "java.lang.byte[]"),
    INTEGER("INTEGER", "java.lang.Long"),
    TINYINT("TINYINT", "java.lang.Integer"),
    INT("INT", "java.lang.Integer"),
    BOOLEAN("BOOLEAN", "java.lang.Integer"),
    SMALLINT("SMALLINT", "java.lang.Integer"),
    MEDIUMINT("MEDIUMINT", "java.lang.Integer"),
    BIGINT("BIGINT", "java.math.BigInteger"),
    FLOAT("FLOAT", "java.lang.Float"),
    DOUBLE("DOUBLE", "java.lang.Double"),
    DECIMAL("DECIMAL", "java.math.BigDecimal"),
    DATE("DATE", "java.lang.Date"),
    TIME("TIME", "java.lang.Date"),
    DATETIME("DATETIME", "java.lang.Date"),
    TIMESTAMP("TIMESTAMP", "java.lang.Date"),
    YEAR("YEAR", "java.lang.Date");


    String jdbcType;
    String javaType;

    MysqlDataType(String jdbcType, String javaType) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    public static MysqlDataType fromString(String jdbcType) {
        return Arrays.stream(MysqlDataType.values()).filter(dataType -> dataType.jdbcType.equalsIgnoreCase(jdbcType)).findFirst().get();
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
