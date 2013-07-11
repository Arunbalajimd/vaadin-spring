package de.marcbosserhoff.vaadin.configuration.xml;

import javax.xml.bind.annotation.*;

/**
 * A component is a base class for every vaadin component configuration. Each vaadin element and its properties are
 * mapped to one of these xml annotated java classes. Plugin developers can write their own configuration POJOs
 * and generate the XSD scheme to be used in their application for configuring plugin-specific settings.
 */
@XmlType(namespace = Namespaces.UI_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class Component {

    @XmlAttribute
    private Boolean visible;

    @XmlAttribute
    private Boolean readonly;

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }
}
