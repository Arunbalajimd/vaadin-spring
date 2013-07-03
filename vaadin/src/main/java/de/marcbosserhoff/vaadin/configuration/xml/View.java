package de.marcbosserhoff.vaadin.configuration.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(namespace = Namespaces.UI_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class View {

    @XmlAttribute
    private String id;

    @XmlAttribute
    private String name;

    @XmlElement
    private List<Component> components;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
