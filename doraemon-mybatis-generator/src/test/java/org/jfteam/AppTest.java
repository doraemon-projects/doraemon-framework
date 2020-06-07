package org.jfteam;

import org.apache.ibatis.session.SqlSession;
import org.doraemon.framework.util.JSON;
import org.doraemon.framework.util.StringUtils;
import org.doraemon.framework.mybatis.dao.TableMapper;
import org.doraemon.framework.mybatis.util.FreemarkerUtils;
import org.doraemon.framework.mybatis.util.MyBatisUtils;
import org.doraemon.framework.util.PropertyUtils;
import org.doraemon.framework.mybatis.vo.TableVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * created with IntelliJ IDEA.
 * description: 单元测试
 * author:      fengwenping
 * date:        2019/6/25 20:54
 */
public class AppTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

    private String tableSchema = null;

    private List<String> tableNames = new ArrayList<>(10);

    @Before
    public void before() {
        final Properties applicationConfig = PropertyUtils.getApplicationConfig();
        this.tableSchema = PropertyUtils.getString(applicationConfig, "jdbc.schema");
        final String tables = PropertyUtils.getString(applicationConfig, "jdbc.tables");
        if (StringUtils.isNotBlank(tables)) {
            this.tableNames = Arrays.asList(tables.split(","));
        }
    }

    @Test
    public void test_01() {
        final SqlSession sqlSession = MyBatisUtils.openSqlSession();
        try {
            final TableMapper mapper = MyBatisUtils.getMapper(sqlSession, TableMapper.class);
            final List<TableVO> tables = mapper.findTables(this.tableSchema, this.tableNames);
            tables.forEach(tableVO -> {
                Map<String, Object> map = new HashMap<>();
                map.put("table", tableVO);
                final String html = FreemarkerUtils.renderHtml("XmlMapper.ftl", map);
                LOGGER.info(html);
            });
            LOGGER.info(JSON.toJSONString(tables));
            Assert.assertEquals("查询数据库中表结构数量不匹配", tableNames.size(), tables.size());
        } catch (Exception e) {
            LOGGER.error("查询数据库表结构失败.", e);
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
    }
}
