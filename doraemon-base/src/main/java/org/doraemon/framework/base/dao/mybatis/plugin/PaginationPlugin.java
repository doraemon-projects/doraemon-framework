package org.doraemon.framework.base.dao.mybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Properties;

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
                        Object.class, RowBounds.class,
                        ResultHandler.class
                }
        )
})
public class PaginationPlugin implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaginationPlugin.class);

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
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
