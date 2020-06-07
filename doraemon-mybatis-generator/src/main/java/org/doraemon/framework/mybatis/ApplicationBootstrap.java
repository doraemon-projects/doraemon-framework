package org.doraemon.framework.mybatis;

import org.doraemon.framework.mybatis.component.GeneratorComponent;

/**
 * Hello world!
 */
public class ApplicationBootstrap {

    public static void main(String[] args) {
        GeneratorComponent.getInstance().execute();
    }
}
