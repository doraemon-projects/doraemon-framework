package org.doraemon.framework.dao.mybatis.plugin;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.Properties;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-08-08 15:15
 */
public class ResultCountLimitPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
