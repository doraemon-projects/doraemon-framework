package org.jfteam.mybatis.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * created with IntelliJ IDEA.
 * description: 描述
 * author:      fengwenping
 * date:        2019/7/14 22:15
 */
@XmlRootElement(name = "Table")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfigTable {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "model")
    private String model;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
