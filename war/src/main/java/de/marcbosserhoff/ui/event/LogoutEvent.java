package de.marcbosserhoff.ui.event;

import com.github.wolfie.blackboard.Event;
import com.github.wolfie.blackboard.Listener;
import com.github.wolfie.blackboard.annotation.ListenerMethod;

public class LogoutEvent implements Event {

    public interface LogoutListener extends Listener {
        @ListenerMethod
        public void logout(LogoutEvent event);
    }

    public LogoutEvent() {
    }
}
