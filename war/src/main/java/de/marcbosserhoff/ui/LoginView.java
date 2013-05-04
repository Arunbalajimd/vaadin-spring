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

@Component
@Scope("prototype")
public class LoginView extends Window implements View {

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
        initUI();
    }

    private void initUI() {
        setCaption("Login with your Credentials");
        setModal(true);

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
        VerticalLayout mainLayout = new VerticalLayout(loginForm, buttonLayout);
        mainLayout.setMargin(true);
        buttonLayout.setComponentAlignment(loginButton, Alignment.MIDDLE_LEFT);
        buttonLayout.setComponentAlignment(cancelButton, Alignment.MIDDLE_RIGHT);

        setContent(mainLayout);
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
                closeWindow();
            }
        });
    }

    private void closeWindow() {
        close();
    }

    private void tryLogin() {
        String user = username.getValue();
        String pass = password.getValue();

        log.info("Logging in user: {}", user);

        boolean success = authentication.login(user, pass);
        if (success) {
            log.info("Successfully logged in with user: {}", user);
            closeWindow();
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
