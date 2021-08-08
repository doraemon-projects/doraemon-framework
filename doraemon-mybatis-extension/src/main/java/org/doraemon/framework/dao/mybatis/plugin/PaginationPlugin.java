package org.doraemon.framework.dao.mybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.doraemon.framework.core.base.page.PageParam;
import org.doraemon.framework.core.base.page.PageResult;
import org.doraemon.framework.core.util.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-06-06 15:46
 */
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "query",
                args = {
                        MappedStatement.class,
                        Object.class,
                        ResultHandler.class,
                        RowBounds.class
                }
        )
})
public class PaginationPlugin implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaginationPlugin.class);

    private static final String COUNT_SQL_POST_FIX = "Count";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object target = invocation.getTarget();
        LOGGER.info("mybatis invocation target: [{}]", target.getClass().getName());
        final Method method = invocation.getMethod();
        LOGGER.info("mybatis invocation method: [{}]", method.getName());
        final Object[] args = invocation.getArgs();
        final MappedStatement mappedStatement = (MappedStatement) args[0];
        //获取SQL
        final String sqlId = mappedStatement.getId();
        LOGGER.info("sqlId: [{}]", sqlId);
        Object parameter = args[1];
        PageParam pageParam = checkInvocation(sqlId, parameter);
        if (Objects.isNull(pageParam)) {
            return invocation.proceed();
        }
        Executor executor = (Executor) invocation.getTarget();
        int totalCount = 0;
        if (pageParam.isEnabledPaging()) {
            totalCount = queryCount(args, mappedStatement, sqlId, pageParam, executor);
        }
        List<Object> resultList = new ArrayList<>();
        if ((pageParam.isEnabledPaging() && totalCount > 0) || !pageParam.isEnabledPaging()) {
            resultList = queryResultList(executor, mappedStatement, args);
        }
        PageResult<Object> pageResult = new PageResult<>(resultList, pageParam, totalCount);
        return pageResult;
    }

    private List<Object> queryResultList(Executor executor, MappedStatement mappedStatement, Object[] queryArgs) throws SQLException {
        Object parameterObject = queryArgs[1];
        RowBounds rowBounds = (RowBounds) queryArgs[2];
        ResultHandler resultHandler = (ResultHandler) queryArgs[3];
        Configuration configuration = mappedStatement.getConfiguration();
        BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
        StatementHandler statementHandler = configuration.newStatementHandler(executor, mappedStatement, parameterObject, rowBounds, resultHandler, boundSql);
        Statement statement = null;
        List<Object> resultList = new ArrayList<>();
        try {
            statement = preparedStatement(executor, statementHandler);
            resultList = statementHandler.query(statement, resultHandler);
            return resultList;
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeStatement(statement);
        }
    }

    private Statement preparedStatement(Executor executor, StatementHandler statementHandler) throws SQLException {
        Statement statement = null;
        Connection connection = executor.getTransaction().getConnection();
        try {
            statement = statementHandler.prepare(connection, null);
            statementHandler.parameterize(statement);
            return statement;
        } catch (SQLException ex) {
            closeStatement(statement);
            throw ex;
        }
    }

    private void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private int queryCount(Object[] queryArgs, MappedStatement mappedStatement, String sqlId, PageParam pageParam, Executor executor) throws SQLException {
        String queryCountSqlId = sqlId + COUNT_SQL_POST_FIX;
        Configuration configuration = mappedStatement.getConfiguration();
        MappedStatement queryCountMS = configuration.getMappedStatement(queryCountSqlId);
        MappedStatement newMappedStatement = buildMappedStatement(queryCountMS);
        List<Object> queryCountList = queryResultList(executor, newMappedStatement, queryArgs);
        int totalRows = Integer.parseInt(queryCountList.get(0).toString());
        return totalRows;
    }

    private MappedStatement buildMappedStatement(MappedStatement mappedStatement) {
        MappedStatement.Builder builder = new MappedStatement.Builder(mappedStatement.getConfiguration(), mappedStatement.getId(), mappedStatement.getSqlSource(), mappedStatement.getSqlCommandType());
        builder.resource(mappedStatement.getResource());
        builder.parameterMap(mappedStatement.getParameterMap());
        builder.fetchSize(mappedStatement.getFetchSize());
        builder.timeout(mappedStatement.getTimeout());
        builder.statementType(mappedStatement.getStatementType());
        builder.resultSetType(mappedStatement.getResultSetType());
        builder.cache(mappedStatement.getCache());
        builder.flushCacheRequired(mappedStatement.isFlushCacheRequired());
        builder.useCache(mappedStatement.isUseCache());
        builder.keyGenerator(mappedStatement.getKeyGenerator());
        builder.keyProperty(StringUtils.join(mappedStatement.getKeyProperties(), ","));
        return builder.build();
    }

    private PageParam checkInvocation(String sqlId, Object parameter) {
        if (null == parameter) {
            return null;
        }
        if (null != sqlId && !sqlId.endsWith(COUNT_SQL_POST_FIX)) {
            return findPageParam(parameter);
        }
        return null;
    }

    private PageParam findPageParam(Object parameter) {
        if (null == parameter) {
            return null;
        }
        if (parameter instanceof Map) {
            Map<String, Object> parameterMap = (Map<String, Object>) parameter;
            for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                if (entry.getValue() instanceof PageParam) {
                    return (PageParam) entry.getValue();
                }
            }
        }
        if (parameter instanceof PageParam) {
            return (PageParam) parameter;
        }
        return null;
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
