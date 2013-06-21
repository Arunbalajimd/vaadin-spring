package de.marcbosserhoff.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import de.marcbosserhoff.authentication.SpringAuthentication;
import de.marcbosserhoff.ui.event.LoginEvent;
import de.marcbosserhoff.util.EventSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.VaadinView;

import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
@VaadinView(LoginView.VIEW_NAME)
public class LoginView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "login";

    private Logger log = LoggerFactory.getLogger(LoginView.class);

    private TextField username;
    private TextField password;

    private Button loginButton;
    private Button cancelButton;

    @Autowired
    private SpringAuthentication authentication;

    @Autowired
    private EventSystem eventSystem;

    public LoginView() {
    }

    @PostConstruct
    private void init() {
        initUI();
    }

    private void initUI() {
        setCaption("Login with your Credentials");
        setMargin(true);

        loginButton = new Button("Login");
        cancelButton = new Button("Cancel");

        username = new TextField("Username:");
        password = new TextField("Password:");

        username.setRequired(true);
        username.setImmediate(true);
        username.setInvalidAllowed(false);
        password.setRequired(true);
        password.setImmediate(true);
        password.setInvalidAllowed(false);

        registerListeners();

        // Create layouts
        FormLayout loginForm = new FormLayout(username, password);
        HorizontalLayout buttonLayout = new HorizontalLayout(loginButton, cancelButton);
        buttonLayout.setComponentAlignment(loginButton, Alignment.MIDDLE_LEFT);
        buttonLayout.setComponentAlignment(cancelButton, Alignment.MIDDLE_RIGHT);

        addComponent(loginForm);
        addComponent(buttonLayout);
    }

    private void registerListeners() {
        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                tryLogin();
            }
        });

        cancelButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                navigateToMainView();
            }
        });
    }

    private void navigateToMainView() {
        Application.getCurrent().getNavigator().navigateTo(MainView.VIEW_NAME);
    }

    private void tryLogin() {
        String user = username.getValue();
        String pass = password.getValue();

        log.info("Logging in user: {}", user);

        boolean success = authentication.login(user, pass);
        if (success) {
            log.info("Successfully logged in with user: {}", user);
            navigateToMainView();
            LoginEvent loginEvent = new LoginEvent(user, pass);
            eventSystem.fire(loginEvent);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        clearForm();
    }

    private void clearForm() {
        username.setValue("");
        password.setValue("");
    }
}
