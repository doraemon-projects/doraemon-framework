package org.doraemon.framework.dao.mybatis.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.doraemon.framework.core.util.PropertyUtils;
import org.doraemon.framework.core.util.lang3.StringUtils;
import org.doraemon.framework.dao.mybatis.MyBatisConstants;
import org.doraemon.framework.dao.mybatis.mapper.BaseMapper;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Function;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2021-07-25 18:34
 */
public class MyBatisTemplate {

    private static MyBatisTemplate instance = null;

    public static MyBatisTemplate getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (MyBatisTemplate.class) {
                if (Objects.isNull(instance)) {
                    instance = new MyBatisTemplate();
                }
            }
        }
        return instance;
    }

    private static SqlSessionFactory sqlSessionFactory;

    private static SqlSessionFactory getSqlSessionFactory() {
        final Properties applicationConfig = new Properties();
        return getSqlSessionFactory(applicationConfig);
    }

    private static SqlSessionFactory getSqlSessionFactory(Properties properties) {
        if (sqlSessionFactory == null) {
            synchronized (MyBatisTemplate.class) {
                if (sqlSessionFactory == null) {
                    final InputStream inputStream = MyBatisTemplate.class.getClassLoader().getResourceAsStream(MyBatisConstants.MYBATIS_CONFIG_FILE);
                    if (properties.containsKey(MyBatisConstants.ENV_CONFIG_KEY)) {
                        String env = PropertyUtils.getString(properties, MyBatisConstants.ENV_CONFIG_KEY, MyBatisConstants.DEFAULT_ENV);
                        if (StringUtils.isNotBlank(env)) {
                            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, env, properties);
                            return sqlSessionFactory;
                        }
                    }
                    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
                }
            }
        }
        return sqlSessionFactory;
    }

    /**
     * sqlSession开启事务
     *
     * @param autoCommit 是否自动提交(开启事务)
     * @return 返回创建的sqlSession
     */
    public static SqlSession openSqlSession(SqlSessionFactory sqlSessionFactory, boolean autoCommit) {
        Objects.requireNonNull(sqlSessionFactory, "参数sqlSessionFactory不能为空");
        return sqlSessionFactory.openSession(autoCommit);
    }

    /**
     * 提交事务
     *
     * @param sqlSession 通过sqlSession进行事务提交
     */
    public static void commit(SqlSession sqlSession) {
        Objects.requireNonNull(sqlSession, "参数sqlSession不能为空");
        sqlSession.commit();
    }

    public static void rollback(SqlSession sqlSession) {
        Objects.requireNonNull(sqlSession, "参数sqlSession不能为空");
        sqlSession.rollback();
    }

    /**
     * 关闭SqlSession
     *
     * @param sqlSession 需要关闭的SqlSession
     */
    public static void closeSqlSession(SqlSession sqlSession) {
        Objects.requireNonNull(sqlSession, "参数sqlSession不能为空");
        sqlSession.close();
    }

    /**
     * 获取Mapper代理
     *
     * @param sqlSession SqlSession对象
     * @param tClass     Mapper类型
     * @param <T>        返回的类型
     * @return 返回结果
     */
    public static <T> T getMapper(SqlSession sqlSession, Class<T> tClass) {
        Objects.requireNonNull(sqlSession, "参数sqlSession不能为空");
        return sqlSession.getMapper(tClass);
    }

    public <T extends BaseMapper, R> R run(Class<T> mapperInterface, Function<T, R> mapperExecutor) {
        final SqlSessionFactory sqlSessionFactory = MyBatisTemplate.getSqlSessionFactory();
        final SqlSession sqlSession = MyBatisTemplate.openSqlSession(sqlSessionFactory, false);
        R result = null;
        try {
            final T mapper = MyBatisTemplate.getMapper(sqlSession, mapperInterface);
            result = mapperExecutor.apply(mapper);
            MyBatisTemplate.commit(sqlSession);
        } catch (Exception e) {
            MyBatisTemplate.rollback(sqlSession);
        } finally {
            MyBatisTemplate.closeSqlSession(sqlSession);
        }
        return result;
    }
}
