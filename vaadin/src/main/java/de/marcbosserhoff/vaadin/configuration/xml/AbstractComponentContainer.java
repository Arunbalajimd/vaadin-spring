package de.marcbosserhoff.vaadin.configuration.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(namespace = Namespaces.UI_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class AbstractComponentContainer extends Component {

    @XmlElement
    private List<Component> components;

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
