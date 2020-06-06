package org.doraemon.framework.util;

import org.doraemon.framework.base.BaseObject;
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
public class JSONTest {

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
        BaseObject baseObject = new BaseObject();
        baseObject.setId(UUID.randomUUID().toString());
        baseObject.setCreatedDate(new Date());
        baseObject.setUpdatedDate(new Date());
        System.out.println(JSON.toJSONString(baseObject));
    }

    @Test
    public void testToJSONString() {
        BaseObject baseObject = new BaseObject();
        baseObject.setId(UUID.randomUUID().toString());
        baseObject.setCreatedDate(new Date());
        baseObject.setUpdatedDate(new Date());
        System.out.println(JSON.toJSONString(baseObject, false));
        System.out.println(JSON.toJSONString(baseObject, true));
    }
}