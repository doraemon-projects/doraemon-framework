package org.doraemon.framework.mybatis.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * created with IntelliJ IDEA.
 * description: 描述
 * author:      fengwenping
 * date:        2019/6/30 17:46
 */
@XmlRootElement(name = "Configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class TableConfiguration {

    @XmlElementWrapper(name = "Properties")
    @XmlElement(name = "Property")
    private List<ConfigProperty> properties = new ArrayList<>();

    @XmlElementWrapper(name = "Tables")
    @XmlElement(name = "Table")
    private List<ConfigTable> tables = new ArrayList<>();

    public List<ConfigProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ConfigProperty> properties) {
        this.properties = properties;
    }

    public List<ConfigTable> getTables() {
        return tables;
    }

    public void setTables(List<ConfigTable> tables) {
        this.tables = tables;
    }
}
