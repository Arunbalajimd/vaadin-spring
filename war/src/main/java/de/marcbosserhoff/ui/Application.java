package de.marcbosserhoff.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Title;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import de.marcbosserhoff.spring.logging.Log;
import de.marcbosserhoff.ui.event.LoginEvent;
import de.marcbosserhoff.ui.event.LogoutEvent;
import de.marcbosserhoff.ui.event.ReloadEntriesEvent;
import de.marcbosserhoff.util.EventSystem;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.DiscoveryNavigator;

/**
 * The Application's "main" class
 */
@Component
@Scope("prototype")
@Title("Example Vaadin Application")
@PreserveOnRefresh
public class Application extends UI /* implements Page.UriFragmentChangedListener */ {

    @Log
    private Logger log;

    @Autowired
    private EventSystem eventSystem;

    private DiscoveryNavigator navigator;

    public Application() {
    }

    @Override
    protected void init(VaadinRequest request) {
        log.info("Initializing UI");
        navigator = new DiscoveryNavigator(this, this);
//        getPage().addUriFragmentChangedListener(this);
        setNavigator(navigator);

        configureGlobalEvents();
    }

//    @Override
//    public void uriFragmentChanged(Page.UriFragmentChangedEvent event) {
//        String fragment = UI.getCurrent().getPage().getUriFragment();
//        log.info("Fragment changed... Navigating to fragment: {}", fragment);
//
//        navigator.navigateTo(fragment);
//    }

    private void configureGlobalEvents() {
        eventSystem.registerEvent(ReloadEntriesEvent.ReloadEntriesListener.class, ReloadEntriesEvent.class);
        eventSystem.registerEvent(LoginEvent.LoginListener.class, LoginEvent.class);
        eventSystem.registerEvent(LogoutEvent.LogoutListener.class, LogoutEvent.class);
    }

}
