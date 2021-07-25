package org.doraemon.framework.core.util;

import org.doraemon.framework.core.base.BaseDO;
import org.doraemon.framework.core.base.BaseParam;
import org.doraemon.framework.core.exception.BusinessException;
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

    @Test(expected = BusinessException.class)
    public void assertNotEmpty() {
        AssertUtils.assertNotEmpty(Collections.emptyList());
    }

    @Test
    public void testAssertNotEmpty() {
        final List<Integer> collect = Stream.of(1, 2, 3).collect(Collectors.toList());
        AssertUtils.assertNotEmpty(collect);
    }

    @Test(expected = BusinessException.class)
    public void assertInstanceOf() {
        BaseParam<String> baseParam = new BaseParam<>();
        AssertUtils.assertInstanceOf(baseParam, Assert.class);
    }

    @Test
    public void testAssertInstanceOf() {
        BaseParam<String> baseParam = new BaseParam<>();
        AssertUtils.assertInstanceOf(baseParam, BaseDO.class);
    }

    @Test
    public void assertAssignableFrom() {
        AssertUtils.assertAssignableFrom(BaseDO.class, BaseParam.class);
    }

    @Test(expected = BusinessException.class)
    public void testAssertAssignableFrom() {
        AssertUtils.assertAssignableFrom(Assert.class, BaseParam.class);
    }

    @Test(expected = BusinessException.class)
    public void assertNotZero() {
        AssertUtils.assertNotZero(0L);
    }

    @Test(expected = BusinessException.class)
    public void testAssertNotZero() {
        AssertUtils.assertNotZero(0);
    }

    @Test
    public void testAssertNotZero1() {
        AssertUtils.assertNotZero(1);
    }
}
