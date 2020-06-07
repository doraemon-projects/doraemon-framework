package org.doraemon.framework.mybatis.util;

import org.doraemon.framework.mybatis.enums.DatabaseType;
import org.doraemon.framework.mybatis.enums.MysqlDataType;
import org.doraemon.framework.mybatis.enums.OracleDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: fengwenping
 * Description:
 * Date: 2018-10-26
 * Time: 下午8:28
 */
public final class SqlHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqlHelper.class);

    public static String getJavaType(DatabaseType databaseType, String jdbcType) {
        LOGGER.debug("databaseType: {}, jdbcType: {}", databaseType.getCode(), jdbcType);
        switch (databaseType) {
            case ORACLE:
                return OracleDataType.fromString(jdbcType).getDesc();
            default:
                return MysqlDataType.fromString(jdbcType).getDesc();
        }
    }

}
