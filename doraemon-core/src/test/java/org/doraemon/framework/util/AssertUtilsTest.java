package org.doraemon.framework.util;

import org.doraemon.framework.base.BaseObject;
import org.doraemon.framework.base.BaseQuery;
import org.doraemon.framework.exception.ApplicationRuntimeException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-07-11 18:24
 */
@RunWith(JUnit4.class)
public class AssertUtilsTest {

    @Test(expected = ApplicationRuntimeException.class)
    public void assertNotEmpty() {
        AssertUtils.assertNotEmpty(Collections.emptyList());
    }

    @Test
    public void testAssertNotEmpty() {
        final List<Integer> collect = Stream.of(1, 2, 3).collect(Collectors.toList());
        AssertUtils.assertNotEmpty(collect);
    }

    @Test(expected = ApplicationRuntimeException.class)
    public void assertInstanceOf() {
        BaseQuery<String> baseQuery = new BaseQuery<>();
        AssertUtils.assertInstanceOf(baseQuery, Assert.class);
    }

    @Test
    public void testAssertInstanceOf() {
        BaseQuery<String> baseQuery = new BaseQuery<>();
        AssertUtils.assertInstanceOf(baseQuery, BaseObject.class);
    }

    @Test
    public void assertAssignableFrom() {
        AssertUtils.assertAssignableFrom(BaseObject.class, BaseQuery.class);
    }

    @Test(expected = ApplicationRuntimeException.class)
    public void testAssertAssignableFrom() {
        AssertUtils.assertAssignableFrom(Assert.class, BaseQuery.class);
    }

    @Test(expected = ApplicationRuntimeException.class)
    public void assertNotZero() {
        AssertUtils.assertNotZero(0L);
    }

    @Test(expected = ApplicationRuntimeException.class)
    public void testAssertNotZero() {
        AssertUtils.assertNotZero(0);
    }

    @Test
    public void testAssertNotZero1() {
        AssertUtils.assertNotZero(1);
    }
}
