package org.doraemon.framework.mybatis.util;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 *
 * @author fengwenping
 * @date 2018-10-28 下午4:01
 */
public class FreemarkerUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FreemarkerUtilsTest.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test_01() {
        LOGGER.info("freemarker base path: {}", FreemarkerUtils.class.getResource("/").getPath() + "template");
    }
}