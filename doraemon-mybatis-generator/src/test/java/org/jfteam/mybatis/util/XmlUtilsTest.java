package org.jfteam.mybatis.util;

import org.doraemon.framework.util.XmlUtils;
import org.jfteam.mybatis.model.ConfigTable;
import org.junit.Test;

import java.util.List;

/**
 * created with IntelliJ IDEA.
 * description: 描述
 * author:      fengwenping
 * date:        2019/6/30 22:58
 */
public class XmlUtilsTest {

    @Test
    public void getModels() throws Exception {
        final List<ConfigTable> models = XmlUtils.getModels("table-config.xml", ConfigTable.class);
        models.stream().forEach(model -> System.out.println("name: " + model.getName() + ", model: " + model.getModel()));
    }
}