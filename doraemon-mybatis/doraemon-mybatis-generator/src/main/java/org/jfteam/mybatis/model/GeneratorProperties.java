package org.jfteam.mybatis.model;

import org.jfteam.mybatis.enums.DatabaseType;

/**
 * Created with IntelliJ IDEA.
 * Description: 生成文件的属性名定义
 * Author:      fengwenping
 * Date:        2019/8/3 22:01
 */
public final class GeneratorProperties {

    /**
     * 生成工具配置文件名
     */
    public static final String CONFIG_FILE_NAME = "generator-config.xml";

    /**
     * 默认数据库类型为MYSQL
     */
    public static final DatabaseType DEFAULT_DB_TYPE = DatabaseType.MYSQL;
    /**
     * 数据库类型属性名
     */
    public static final String DB_TYPE = "dbType";
    /**
     * 数据库schema属性名
     */
    public static final String DB_SCHEMA = "schema";
    /**
     * 生成文件报名属性名
     */
    public static final String PACKAGE_NAME = "package";
    /**
     * 生成的实体前缀属性名
     */
    public static final String MODEL_PREFIX = "modelPrefix";
    /**
     * 生成的实体后缀属性名
     */
    public static final String MODEL_SUFFIX = "modelSuffix";
    /**
     * xmlMapper模板文件名
     */
    public static final String XML_MAPPER_TEMPLATE_NAME = "XmlMapper.ftl";
    /**
     * JavaModel模板文件名
     */
    public static final String JAVA_MODEL_TEMPLATE_NAME = "JavaModel.ftl";
    /**
     * JavaMapper模板文件名
     */
    public static final String JAVA_MAPPER_TEMPLATE_NAME = "JavaMapper.ftl";

    /**
     * 生成文件的路径
     */
    public static final String OUTPUT_DIRECTORY = "out";
}
