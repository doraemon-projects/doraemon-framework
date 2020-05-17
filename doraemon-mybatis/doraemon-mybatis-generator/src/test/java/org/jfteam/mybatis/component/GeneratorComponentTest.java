package org.jfteam.mybatis.component;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2019/8/4 0:22
 */
public class GeneratorComponentTest {
    String outputDirectory = "D:\\Program Files\\demo";
    String packageName = ".orf.jfteam.baidu/daren/...//////.../.";

    @Test
    public void getOutputDirectory() {
        final String s = GeneratorComponent.getInstance().getOutputDirectory(outputDirectory, packageName);
        System.out.println(s);
    }

    @Test
    public void createFile() throws Exception {
        String fileName = "LookupClassifyMapper.xml";
        GeneratorComponent.getInstance().createFile(GeneratorComponent.getInstance().getOutputDirectory(outputDirectory, packageName), fileName, "abc");
    }
}