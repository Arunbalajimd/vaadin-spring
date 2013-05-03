package de.marcbosserhoff.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Window;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LoginView extends Window implements View {

    public LoginView() {

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
