package de.marcbosserhoff.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import de.marcbosserhoff.services.ExampleService;
import de.marcbosserhoff.ui.event.ReloadEntriesEvent;
import de.marcbosserhoff.util.EventSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ExampleForm extends FormLayout {

    private Logger log = LoggerFactory.getLogger(ExampleForm.class);

    @Autowired
    private ExampleService exampleService;

    @Autowired
    private EventSystem eventSystem;

    private TextField name = new TextField("Name:");

    private TextField address = new TextField("Address:");

    public ExampleForm() {
        initForm();
    }

    private void initForm() {
        addComponent(name);
        addComponent(address);

        final Button commit = new Button("Commit");
        final Button cancel = new Button("Cancel");

        cancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                clearAndHide();
            }
        });
        commit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                commitForm();
                fireCommitEvent();
                clearAndHide();
            }
        });

        final HorizontalLayout buttonBar = new HorizontalLayout();

        buttonBar.addComponent(commit);
        buttonBar.addComponent(cancel);

        addComponent(buttonBar);
    }

    private void commitForm() {
        log.info("Creating user with name {} and address {}", name.getValue(), address.getValue());
        exampleService.createUserWithNameAndAdress(name.getValue(), address.getValue());
    }

    private void clearAndHide() {
        name.setValue("");
        address.setValue("");
        setVisible(false);
    }

    private void fireCommitEvent() {
        log.info("Fire commit event!");
        eventSystem.fire(new ReloadEntriesEvent());
    }
}
