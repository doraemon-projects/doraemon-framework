package org.doraemon.framework.exception;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @description: 描述
 * @author: fengwenping
 * @date: 2020-07-05 22:29
 */
public class BusinessExceptionTest {

    @Test
    public void assertTrue() {
        BusinessException.assertTrue(false);
    }

    @Test
    public void testAssertTrue() {
        BusinessException.assertTrue(true, "未达到预期值");
    }

    @Test
    public void assertFalse() {
        BusinessException.assertFalse(false);
    }

    @Test
    public void testAssertFalse() {
        BusinessException.assertFalse(false, "未达到预期值");
    }

    @Test
    public void assertEquals() {
        BusinessException.assertEquals(1, 0);
    }

    @Test
    public void testAssertEquals() {
        BusinessException.assertEquals(1, 1, "未达到预期值");
    }

    @Test
    public void assertNonNull() {
        BusinessException.assertNotNull(1);
    }

    @Test
    public void testAssertNonNull() {
        BusinessException.assertNotNull(null, "未达到预期值");
    }

    @Test
    public void assertNull() {
        BusinessException.assertNull(1);
    }

    @Test
    public void testAssertNull() {
        BusinessException.assertNull(null, "未达到预期值");
    }

    @Test
    public void newException() {
    }

    @Test
    public void testNewException() {
    }

    @Test
    public void testAssertTrue1() {
    }

    @Test
    public void testAssertTrue2() {
    }

    @Test
    public void testAssertFalse1() {
    }

    @Test
    public void testAssertFalse2() {
    }

    @Test
    public void testAssertEquals1() {
    }

    @Test
    public void testAssertEquals2() {
    }

    @Test
    public void assertNotNull() {
    }

    @Test
    public void testAssertNotNull() {
    }

    @Test
    public void testAssertNull1() {
    }

    @Test
    public void testAssertNull2() {
    }

    @Test
    public void assertNotEmpty() {
    }

    @Test
    public void testAssertNotEmpty() {
    }

    @Test
    public void testAssertNotEmpty1() {
    }

    @Test
    public void testAssertNotEmpty2() {
    }

    @Test
    public void testAssertNotEmpty3() {
    }

    @Test
    public void testAssertNotEmpty4() {
    }

    @Test
    public void assertInstanceOf() {
    }

    @Test
    public void testAssertInstanceOf() {
    }

    @Test
    public void assertIsAssignable() {
    }

    @Test
    public void testAssertIsAssignable() {
    }

    @Test
    public void throwNewException() {
    }

    @Test
    public void testThrowNewException() {
    }
}