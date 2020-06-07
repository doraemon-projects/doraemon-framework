package org.doraemon.framework.mybatis.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.doraemon.framework.util.PropertyUtils;
import org.doraemon.framework.util.StringUtils;

import java.io.InputStream;
import java.util.Properties;

/**
 * created with IntelliJ IDEA.
 * description: MyBatis工具类
 * author:      fengwenping
 * date:        2019/7/7 23:53
 */
public final class MyBatisUtils {

    /**
     * mybatis配置文件名字
     */
    private static final String MYBATIS_CONFIG_FILE = "mybatis-config.xml";

    /**
     * mybatis环境配置key名字
     */
    private static final String ENV_CONFIG_KEY = "jdbc.environment";
    /**
     * mybatis默认环境
     */
    private static final String DEFAULT_ENV = "dev";

    private static SqlSessionFactory sqlSessionFactory;

    private static SqlSessionFactory getSqlSessionFactory() {
        final Properties applicationConfig = new Properties();
        return MyBatisUtils.getSqlSessionFactory(applicationConfig);
    }

    private static SqlSessionFactory getSqlSessionFactory(Properties properties) {
        if (sqlSessionFactory == null) {
            synchronized (MyBatisUtils.class) {
                if (sqlSessionFactory == null) {
                    final InputStream inputStream = MyBatisUtils.class.getClassLoader().getResourceAsStream(MYBATIS_CONFIG_FILE);
                    if (properties.containsKey(ENV_CONFIG_KEY)) {
                        String env = PropertyUtils.getString(properties, ENV_CONFIG_KEY, DEFAULT_ENV);
                        if (StringUtils.isNoneBlank(env)) {
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
     * 打开SqlSession
     *
     * @return 返回一个SqlSession
     */
    public static SqlSession openSqlSession() {
        return MyBatisUtils.openSqlSession(false);
    }

    /**
     * sqlSession开启事务
     *
     * @param autoCommit 是否自动提交(开启事务)
     * @return 返回创建的sqlSession
     */
    public static SqlSession openSqlSession(boolean autoCommit) {
        return MyBatisUtils.getSqlSessionFactory().openSession(autoCommit);
    }

    /**
     * 提交事务
     *
     * @param sqlSession 通过sqlSession进行事务提交
     */
    public static void commit(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.commit();
        }
    }

    /**
     * 关闭SqlSession
     *
     * @param sqlSession 需要关闭的SqlSession
     */
    public static void closeSqlSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
        }
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
        if (sqlSession == null) {
            throw new RuntimeException("参数sqlSession不能为空");
        }
        return sqlSession.getMapper(tClass);
    }
}
