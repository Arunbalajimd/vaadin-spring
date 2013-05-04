package de.marcbosserhoff.util;

import com.github.wolfie.blackboard.Blackboard;
import com.github.wolfie.blackboard.Event;
import com.github.wolfie.blackboard.Listener;
import de.marcbosserhoff.ui.event.LoginEvent;
import de.marcbosserhoff.ui.event.LogoutEvent;
import de.marcbosserhoff.ui.event.ReloadEntriesEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("session")
public class EventSystem implements Serializable {
    private Blackboard blackboard = new Blackboard();

    public EventSystem() {
        init();
        configureGlobalEvents();
    }

    private void init() {
        blackboard.enableLogging();
    }

    private void configureGlobalEvents() {
        registerEvent(ReloadEntriesEvent.ReloadEntriesListener.class, ReloadEntriesEvent.class);
        registerEvent(LoginEvent.LoginListener.class, LoginEvent.class);
        registerEvent(LogoutEvent.LogoutListener.class, LogoutEvent.class);
    }

    public void registerEvent(Class<? extends Listener> listenerClass, Class<? extends Event> eventClass) {
        blackboard.register(listenerClass, eventClass);
    }

    public <T extends Listener> void addListener(T listener) {
        blackboard.addListener(listener);
    }

    public <T extends Event> void fire(T event) {
        blackboard.fire(event);
    }
}
