package org.doraemon.framework.core.util;

import org.doraemon.framework.core.Constants;
import org.doraemon.framework.core.util.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * created with IntelliJ IDEA.
 * description: 描述
 * author:      fengwenping
 * date:        2019/6/30 22:47
 */
public abstract class XmlUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

    private XmlUtils() {
    }

    /**
     * 读取文件流并转化为实体
     *
     * @param inputStream
     * @param tClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> getModels(InputStream inputStream, Class<T> tClass) throws Exception {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        final Document document = documentBuilder.parse(inputStream);
        final NodeList nodeList = document.getElementsByTagName(tClass.getSimpleName());
        final int length = nodeList.getLength();
        List<T> tList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            final Node item = nodeList.item(i);
            final NamedNodeMap attributes = item.getAttributes();
            final Field[] fields = tClass.getDeclaredFields();
            T t = tClass.getDeclaredConstructor().newInstance();
            final int i1 = attributes.getLength();
            for (int i2 = 0; i2 < i1; i2++) {
                for (Field field : fields) {
                    if (attributes.item(i2).getNodeName().equals(field.getName())) {
                        field.setAccessible(true);
                        field.set(t, attributes.item(i2).getNodeValue());
                        field.setAccessible(false);
                        continue;
                    }
                }
            }
            tList.add(t);
        }
        return tList;
    }

    /**
     * 读取xml文件并转化为实体
     *
     * @param fileName
     * @param tClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> getModels(String fileName, Class<T> tClass) throws Exception {
        return XmlUtils.getModels(XmlUtils.class.getClassLoader().getResourceAsStream(fileName), tClass);
    }

    /**
     * 读取xml文件并转化为实体
     *
     * @param fileName
     * @param tClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T getModel(String fileName, Class<T> tClass) throws Exception {
        final List<T> models = XmlUtils.getModels(fileName, tClass);
        if (models != null && models.size() > 0) {
            return models.get(0);
        }
        return null;
    }

    public static <T> T convertXml2Object(Class<T> clazz, String xml) {
        return convertXml2Object(clazz, xml, Constants.CharsetConfig.utf8Charset().name());
    }

    public static <T> T convertXml2Object(Class<T> clazz, String xml, String charset) {
        T object = null;
        if (xml != null && !"".equals(xml)) {
            ByteArrayInputStream byteArrayInputStream = null;
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
                byteArrayInputStream = new ByteArrayInputStream(xml.getBytes(charset));
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                JAXBElement<T> jaxbElement = unmarshaller.unmarshal(new StreamSource(byteArrayInputStream), clazz);
                object = jaxbElement.getValue();
            } catch (Exception e) {
                LOGGER.error("error when unmarshalling from a xml string", e);
            } finally {
                IOUtils.close(byteArrayInputStream);
            }
        }
        return object;
    }

    public static <T> String convertObject2Xml(T object, String charset) {
        String xml = "";
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            // 是否格式化生成xml
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // 设置编码方式
            marshaller.setProperty(Marshaller.JAXB_ENCODING, charset);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
            byteArrayOutputStream = new ByteArrayOutputStream();
            marshaller.marshal(object, byteArrayOutputStream);
            byte[] buf = byteArrayOutputStream.toByteArray();
            xml = new String(buf, 0, buf.length, charset);
        } catch (Exception e) {
            LOGGER.error("error when marshalling to a xml string", e);
        } finally {
            IOUtils.close(byteArrayOutputStream);
        }
        return xml;
    }

    public static <T> String convertObject2Xml(T object) {
        return convertObject2Xml(object, Constants.CharsetConfig.utf8Charset().name());
    }
}
