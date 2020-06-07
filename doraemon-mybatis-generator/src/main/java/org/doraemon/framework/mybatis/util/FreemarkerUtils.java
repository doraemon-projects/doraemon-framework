package org.doraemon.framework.mybatis.util;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Description:
 *
 * @author fengwenping
 * @date 2018-10-28 下午3:55
 */
public final class FreemarkerUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FreemarkerUtils.class);

    private static final String FREEMARKER_TEMPLATE_PATH = "/template";

    private static Class[] classes = {StringHelper.class};

    private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

    static {
        configuration.setClassForTemplateLoading(FreemarkerUtils.class, FREEMARKER_TEMPLATE_PATH);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTimeZone(TimeZone.getDefault());
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    private FreemarkerUtils() {
    }

    /**
     * 获取模版
     *
     * @param templateName 模版名称
     * @return 返回获取的模版
     */
    public static Template getTemplate(String templateName) {
        Template template = null;
        try {
            template = configuration.getTemplate(templateName);
        } catch (IOException e) {
            LOGGER.error("获取freemarker模版: {} 异常.", templateName, e);
        }
        return template;
    }

    public static Map<String, Object> getStaticModel() {
        Map<String, Object> staticMap = new HashMap<>(10);
        BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_28).build();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        Arrays.stream(classes).forEach(aClass -> {
            try {
                TemplateHashModel fileStatics = (TemplateHashModel) staticModels.get(aClass.getName());
                staticMap.put(aClass.getSimpleName(), fileStatics);
            } catch (TemplateModelException e) {
                LOGGER.error("设置全局静态变量异常.", e);
            }
        });
        return staticMap;
    }

    /**
     * 根据模版和数据渲染为html内容
     *
     * @param templateName 模版名称
     * @param data         数据
     * @return 返回渲染后的html内容
     */
    public static String renderHtml(String templateName, Map<String, Object> data) {
        final Template template = FreemarkerUtils.getTemplate(templateName);
        if (template == null) {
            throw new RuntimeException("模版 " + templateName + "不存在.");
        }
        StringWriter stringWriter = new StringWriter();
        try {
            template.process(data, stringWriter);
        } catch (TemplateException e) {
            LOGGER.error("渲染模版: {}为html出现Template异常.", templateName, e);
        } catch (IOException e) {
            LOGGER.error("渲染模版: {}为html出现IO异常.", templateName, e);
        }
        return stringWriter.toString();
    }

    public static class DataModelBuilder {
        private Map<String, Object> dataModel = new HashMap<>();

        public Map<String, Object> build() {
            final Map<String, Object> staticModel = FreemarkerUtils.getStaticModel();
            dataModel.putAll(staticModel);
            return this.dataModel;
        }

        public DataModelBuilder add(String name, Object data) {
            this.dataModel.put(name, data);
            return this;
        }
    }
}
