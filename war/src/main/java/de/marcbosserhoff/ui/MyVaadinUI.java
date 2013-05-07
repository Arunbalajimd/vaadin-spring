package de.marcbosserhoff.ui;

import com.vaadin.annotations.Title;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.BaseTheme;
import de.marcbosserhoff.authentication.SpringAuthentication;
import de.marcbosserhoff.model.ExampleEntity;
import de.marcbosserhoff.repositories.ExampleRepository;
import de.marcbosserhoff.services.ExampleService;
import de.marcbosserhoff.ui.event.LoginEvent;
import de.marcbosserhoff.ui.event.LogoutEvent;
import de.marcbosserhoff.ui.event.ReloadEntriesEvent;
import de.marcbosserhoff.util.EventSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Component
@Scope("session")
@Title("Example Vaadin Application")
public class MyVaadinUI extends UI implements ReloadEntriesEvent.ReloadEntriesListener, LoginEvent.LoginListener, LogoutEvent.LogoutListener {

    private Logger log = LoggerFactory.getLogger(MyVaadinUI.class);

    @Autowired
    private ExampleContainer exampleContainer;

    @Autowired
    private ExampleService exampleService;

    @Autowired
    private ExampleRepository exampleRepository;

    private Table entityTable;
    private Long selectedId;
    private Button loginButton;
    private Button logoutButton;

    private boolean isLoggedIn;

    @Autowired
    private ExampleForm editForm;

    @Autowired
    private LoginView loginView;

    @Autowired
    private EventSystem eventSystem;

    @Autowired
    private SpringAuthentication authentication;

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

        loginButton = new Button("Login");
        loginButton.setStyleName(BaseTheme.BUTTON_LINK);
        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                doLogin();
            }
        });

        logoutButton = new Button("Logout");
        logoutButton.setStyleName(BaseTheme.BUTTON_LINK);
        logoutButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                doLogout();
            }
        });

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

        editForm.setVisible(false);

        layout.addComponent(loginButton);
        layout.addComponent(entityTable);
        layout.addComponent(buttonBar);
        layout.addComponent(editForm);

        layout.setComponentAlignment(loginButton, Alignment.TOP_LEFT);

        layout.setMargin(true);
        setContent(layout);
    }

    private void doLogout() {
        authentication.logout();
        eventSystem.fire(new LogoutEvent());
    }

    private void doLogin() {
        addWindow(loginView);
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

    @Secured("ROLE_ADMIN")
    private void removeSelectedEntry() {
        if (selectedId != null) {
            exampleService.delete(selectedId);
            eventSystem.fire(new ReloadEntriesEvent());
        }
    }

    private void registerEvents() {
        eventSystem.addListener(this);
    }

    @Override
    public void reloadEntries(ReloadEntriesEvent event) {
        log.info("Received reload event. Refreshing entry table!");
        initData();
        entityTable.markAsDirty();
    }

    @Override
    public void login(LoginEvent event) {
        log.info("Received login event");
        isLoggedIn = true;
        handleButtonVisibility();
    }

    @Override
    public void logout(LogoutEvent event) {
        log.info("Received logout event");
        isLoggedIn = false;
        handleButtonVisibility();
    }

    private void handleButtonVisibility() {
        loginButton.setVisible(!isLoggedIn);
        logoutButton.setVisible(isLoggedIn);
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

}
