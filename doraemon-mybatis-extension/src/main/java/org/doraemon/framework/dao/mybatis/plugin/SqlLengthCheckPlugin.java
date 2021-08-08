package org.doraemon.framework.dao.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.doraemon.framework.core.exception.SystemException;
import org.doraemon.framework.core.response.ResultCode;
import org.doraemon.framework.core.util.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-08-08 15:17
 */
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {
                        Connection.class,
                        Integer.class
                }
        )
})
public class SqlLengthCheckPlugin implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqlLengthCheckPlugin.class);

    public static final int DEFAULT_MAX_SQL_LENGTH_COUNT = 50000;

    private static int maxSqlLengthCount = DEFAULT_MAX_SQL_LENGTH_COUNT;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            final BoundSql boundSql = statementHandler.getBoundSql();
            final String sql = boundSql.getSql();
            if (sql.length() > maxSqlLengthCount) {
                LOGGER.error("current sql length: {}, max sql length: {}, please limit sql.", sql.length(), maxSqlLengthCount);
                throw new SystemException(ResultCode.EXCEPTION.getCode(), ResultCode.EXCEPTION.getValue());
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler || target instanceof ResultHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        final String maxSqlLengthLimitCount = properties.getProperty("maxSqlLengthLimitCount");
        if (StringUtils.isNotBlank(maxSqlLengthLimitCount) && StringUtils.isNumeric(maxSqlLengthLimitCount)) {
            maxSqlLengthCount = Integer.valueOf(maxSqlLengthLimitCount);
        }
    }
}
