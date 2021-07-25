package org.doraemon.framework.core.util;

import org.doraemon.framework.dao.mybatis.BaseDO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2020/1/6 1:13
 */
public class JacksonUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parse() {
    }

    @Test
    public void toJSON() {
    }

    @Test
    public void parseObject() {
    }

    @Test
    public void parseArray() {
    }

    @Test
    public void toJSONString() {
        BaseDO baseDO = new BaseDO();
        baseDO.setId(UUID.randomUUID().toString());
        baseDO.setCreatedDate(new Date());
        baseDO.setLastModifiedDate(new Date());
        System.out.println(JacksonUtils.toJSONString(baseDO));
    }

    @Test
    public void testToJSONString() {
        BaseDO baseDO = new BaseDO();
        baseDO.setId(UUID.randomUUID().toString());
        baseDO.setCreatedDate(new Date());
        baseDO.setLastModifiedDate(new Date());
        System.out.println(JacksonUtils.toJSONString(baseDO, false));
        System.out.println(JacksonUtils.toJSONString(baseDO, true));
    }
}