package org.doraemon.framework.base.dao.mybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-05-17 16:56
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class UniversalOperationPlugin implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniversalOperationPlugin.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object target = invocation.getTarget();
        LOGGER.info("mybatis invocation target: [{}]", target.getClass().getName());
        final Method method = invocation.getMethod();
        LOGGER.info("mybatis invocation method: [{}]", method.getName());
        final Object[] args = invocation.getArgs();
        final MappedStatement mappedStatement = (MappedStatement) args[0];
        //获取SQL
        final String id = mappedStatement.getId();
        LOGGER.info("sql id: [{}]", id);
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        LOGGER.info("sql command type: [{}]", sqlCommandType.name());
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {

        } else if (SqlCommandType.DELETE.equals(sqlCommandType)) {

        } else if (SqlCommandType.UPDATE.equals(sqlCommandType)) {

        } else if (SqlCommandType.SELECT.equals(sqlCommandType)) {
        } else {
        }
        //获取参数
        Object parameter = invocation.getArgs()[1];
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
