package org.jfteam.mybatis.util;

import org.junit.Test;

/**
 * created with IntelliJ IDEA.
 * description: StringHelperTest
 * author:      fengwenping
 * date:        2019/6/25 21:26
 */
public class StringHelperTest {

    @Test
    public void underLine2Camel() {
        String str = "under_line_camel_utils";
        System.out.println("StringHelperTest: " + StringHelper.underLine2Camel(str));
    }

    @Test
    public void camel2UnderLine() {
        String str = "UnderLineCamelUtils";
        System.out.println("StringHelperTest: " + StringHelper.camel2UnderLine(str));
    }
}