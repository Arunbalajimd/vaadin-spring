package de.marcbosserhoff.ui;

import com.vaadin.data.util.BeanContainer;
import de.marcbosserhoff.model.ExampleEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ExampleContainer extends BeanContainer<Long, ExampleEntity> {

    public static final String BEAN_ID = "id";

    public static final String[] PROPERTIES = {BEAN_ID, "name", "address"};

    public static final String[] HEADERS = {"ID", "Name", "Address"};

    public ExampleContainer() {
        super(ExampleEntity.class);
        setBeanIdProperty(BEAN_ID);
    }
}
