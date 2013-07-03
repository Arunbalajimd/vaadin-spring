package de.marcbosserhoff.vaadin.configuration.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = Namespaces.UI_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class Component {

    @XmlAttribute
    private boolean visible;

    @XmlAttribute
    private boolean readonly;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }
}
