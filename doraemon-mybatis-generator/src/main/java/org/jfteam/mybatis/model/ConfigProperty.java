package org.jfteam.mybatis.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * created with IntelliJ IDEA.
 * description: 描述
 * author:      fengwenping
 * date:        2019/7/14 22:14
 */
@XmlRootElement(name = "Property")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfigProperty {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "value")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
