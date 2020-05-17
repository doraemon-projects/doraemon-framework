package org.jfteam.mybatis.component;

import org.apache.ibatis.session.SqlSession;
import org.jfteam.mybatis.enums.DatabaseType;
import org.jfteam.mybatis.dao.TableMapper;
import org.jfteam.mybatis.model.*;
import org.jfteam.mybatis.util.*;
import org.jfteam.mybatis.vo.ColumnVO;
import org.jfteam.mybatis.vo.TableVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description: mybatis生成工具组件
 * Author:      fengwenping
 * Date:        2019/8/3 22:10
 */
public class GeneratorComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorComponent.class);

    private GeneratorComponent() {
    }

    private static GeneratorComponent instance = null;

    public static GeneratorComponent getInstance() {
        if (instance == null) {
            synchronized (GeneratorComponent.class) {
                if (instance == null) {
                    instance = new GeneratorComponent();
                }
            }
        }
        return instance;
    }

    private Properties configProperties = new Properties();

    private DatabaseType databaseType = GeneratorProperties.DEFAULT_DB_TYPE;

    private Map<String, EntityModel> models = new HashMap<>(10);

    private void init() {
        try {
            final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(GeneratorProperties.CONFIG_FILE_NAME);
            String xml = IOUtils.copyToString(inputStream, ConfigUtils.utf8Charset());
            final TableConfiguration configuration = XmlUtils.convertXml2Object(TableConfiguration.class, xml, ConfigUtils.utf8Charset());
            final List<ConfigProperty> properties = configuration.getProperties();
            properties.forEach(configProperty -> configProperties.setProperty(configProperty.getName(), configProperty.getValue()));
            this.databaseType = DatabaseType.fromString(PropertyUtils.getString(configProperties, GeneratorProperties.DB_TYPE).toLowerCase());
            final List<ConfigTable> tables = configuration.getTables();
            tables.forEach(table -> {
                final EntityModel entityModel = new EntityModel(PropertyUtils.getString(configProperties, GeneratorProperties.PACKAGE_NAME),
                        PropertyUtils.getString(configProperties, GeneratorProperties.MODEL_PREFIX) + table.getModel() + PropertyUtils.getString(configProperties, GeneratorProperties.MODEL_SUFFIX),
                        table.getName()
                        , null);
                models.put(table.getName().toUpperCase(), entityModel);
            });
        } catch (Exception e) {
            LOGGER.error("init table config failure", e);
        }
    }

    private void generate() {
        List<TableVO> tables = new ArrayList<>();
        final SqlSession sqlSession = MyBatisUtils.openSqlSession();
        try {
            final TableMapper tableMapper = MyBatisUtils.getMapper(sqlSession, TableMapper.class);
            tables = tableMapper.findTables(PropertyUtils.getString(this.configProperties, GeneratorProperties.DB_SCHEMA), new ArrayList<>(this.models.keySet()));
        } catch (Exception e) {
            LOGGER.error("查询数据库表结构失败.", e);
        } finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }
        tables.forEach(tableVO -> {
            EntityModel entityModel = this.models.get(tableVO.getTableName().toUpperCase());
            entityModel.setComment(tableVO.getTableComment());
            final List<ColumnVO> columns = tableVO.getColumns();
            columns.forEach(columnVO -> {
                FieldModel fieldModel = new FieldModel();
                fieldModel.setColumnName(columnVO.getColumnName());
                fieldModel.setName(StringHelper.underLine2Camel(columnVO.getColumnName()));
                fieldModel.setComment(columnVO.getColumnComment());
                fieldModel.setJdbcType(columnVO.getDataType());
                fieldModel.setJavaType(SqlHelper.getJavaType(this.databaseType, columnVO.getDataType()));
                entityModel.getFields().add(fieldModel);
            });
        });
        this.generateFile(GeneratorProperties.JAVA_MODEL_TEMPLATE_NAME);
        this.generateFile(GeneratorProperties.XML_MAPPER_TEMPLATE_NAME);
        this.generateFile(GeneratorProperties.JAVA_MAPPER_TEMPLATE_NAME);
    }

    private void generateFile(String templateName) {
        this.models.values().forEach(entityModel -> {
            final Map<String, Object> data = new FreemarkerUtils.DataModelBuilder().build();
            data.put("model", entityModel);
            final String html = FreemarkerUtils.renderHtml(templateName, data);
            LOGGER.info("html: {}", html);
        });
    }

    public String getOutputDirectory(String outputDirectory, String packageName) {
        if (outputDirectory == null) {
            outputDirectory = "/";
        }
        if (packageName == null) {
            packageName = "";
        }
        if (packageName.contains("/") || packageName.contains("\\")) {
            packageName = packageName.replace("/", ".").replace("\\", ".");
        }
        while (packageName.contains("..")) {
            packageName = packageName.replace("..", ".");
        }
        if (packageName.startsWith(".")) {
            packageName = packageName.substring(1, packageName.length() - 1);
        }
        if (packageName.endsWith(".")) {
            packageName = packageName.substring(0, packageName.length() - 1);
        }
        outputDirectory = outputDirectory + "/" + packageName.replace(".", "/");
        return outputDirectory.replace("\\", "/");
    }

    public void createFile(String outputDirectory, String fileName, String content) throws Exception {
        if (fileName == null || fileName.equals("")) {
            throw new IllegalArgumentException("fileName must be not null");
        }
        fileName = outputDirectory + "/" + fileName;
        File file = new File(fileName);
        if (!(file.getParentFile().exists())) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        Writer writer = new FileWriter(file, true);
        writer.write(content);
        if (writer != null) {
            writer.close();
        }
    }

    public void execute() {
        final GeneratorComponent component = GeneratorComponent.getInstance();
        component.init();
        component.generate();
    }
}
