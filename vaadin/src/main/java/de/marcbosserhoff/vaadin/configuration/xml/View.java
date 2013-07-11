package de.marcbosserhoff.vaadin.configuration.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * The view is the top level element of a vaadin configuration file.
 */
@XmlRootElement(namespace = Namespaces.UI_NAMESPACE)
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

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
