package de.marcbosserhoff.ui;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.marcbosserhoff.model.ExampleEntity;
import de.marcbosserhoff.repositories.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Component
@Scope("prototype")
@Title("Example Vaadin Application")
public class MyVaadinUI extends UI {

    @Autowired
    private ExampleContainer exampleContainer;

    @Autowired
    private ExampleRepository exampleRepository;

    @Override
    protected void init(VaadinRequest request) {
        initData();
        initLayout();
    }

    private void initData() {
        List<ExampleEntity> all = exampleRepository.findAll();
        exampleContainer.addAll(all);
    }

    private void initLayout() {
        final VerticalLayout layout = new VerticalLayout();

        final Table entityTable = new Table();
        entityTable.setContainerDataSource(exampleContainer);

        entityTable.setVisibleColumns(ExampleContainer.PROPERTIES);
        entityTable.setColumnHeaders(ExampleContainer.HEADERS);

        final ExampleForm editForm = new ExampleForm();

        layout.addComponent(entityTable);
        layout.addComponent(editForm);

        layout.setMargin(true);
        setContent(layout);
    }


}
