package de.marcbosserhoff.ui;

import com.github.wolfie.blackboard.Blackboard;
import com.vaadin.annotations.Title;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import de.marcbosserhoff.model.ExampleEntity;
import de.marcbosserhoff.repositories.ExampleRepository;
import de.marcbosserhoff.ui.event.ReloadEntriesEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class MyVaadinUI extends UI implements ReloadEntriesEvent.ReloadEntriesListener {

    private Logger log = LoggerFactory.getLogger(MyVaadinUI.class);

    private Blackboard blackboard = new Blackboard();

    @Autowired
    private ExampleContainer exampleContainer;

    @Autowired
    private ExampleRepository exampleRepository;

    private Table entityTable;
    private Long selectedId;

    @Autowired
    private ExampleForm editForm;

    @Override
    protected void init(VaadinRequest request) {
        initData();
        initLayout();
        registerEvents();
    }

    private void initData() {
        List<ExampleEntity> all = exampleRepository.findAll();
        exampleContainer.removeAllItems();
        exampleContainer.addAll(all);
    }

    private void initLayout() {
        final VerticalLayout layout = new VerticalLayout();
        layout.setWidth("400px");

        entityTable = new Table();
        entityTable.setContainerDataSource(exampleContainer);
        entityTable.setVisibleColumns(ExampleContainer.PROPERTIES);
        entityTable.setColumnHeaders(ExampleContainer.HEADERS);
        entityTable.setSelectable(true);
        entityTable.setWidth("100%");
        entityTable.setHeight("300px");

        entityTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                selectedId = (Long) event.getItemId();

                log.info("Selected item id {}", selectedId);
            }
        });

        final AbstractLayout buttonBar = initButtonBar();
        buttonBar.setWidth("100%");

        editForm.setBlackBoard(blackboard);
        editForm.setVisible(false);

        layout.addComponent(entityTable);
        layout.addComponent(buttonBar);
        layout.addComponent(editForm);

        layout.setMargin(true);
        setContent(layout);
    }

    private AbstractLayout initButtonBar() {
        final HorizontalLayout buttonBar = new HorizontalLayout();

        buttonBar.setSpacing(true);

        final Button addButton = new Button("Add entry");
        final Button deleteButton = new Button("Delete entry");

        buttonBar.addComponent(addButton);
        buttonBar.addComponent(deleteButton);

        buttonBar.setComponentAlignment(addButton, Alignment.MIDDLE_LEFT);
        buttonBar.setComponentAlignment(deleteButton, Alignment.MIDDLE_RIGHT);

        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                editForm.setVisible(true);
            }
        });
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                removeSelectedEntry();
            }
        });

        return buttonBar;
    }

    private void removeSelectedEntry() {
        if (selectedId != null) {
            exampleRepository.delete(selectedId);
            blackboard.fire(new ReloadEntriesEvent());
        }
    }

    private void registerEvents() {
        blackboard.enableLogging();
        blackboard.register(ReloadEntriesEvent.ReloadEntriesListener.class, ReloadEntriesEvent.class);
        blackboard.addListener(this);
    }

    @Override
    public void reloadEntries(ReloadEntriesEvent event) {
        log.info("Received reload event. Refreshing entry table!");
        initData();
        entityTable.markAsDirty();
    }
}
