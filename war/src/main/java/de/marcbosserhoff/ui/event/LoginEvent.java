package de.marcbosserhoff.ui.event;

import com.github.wolfie.blackboard.Event;
import com.github.wolfie.blackboard.Listener;
import com.github.wolfie.blackboard.annotation.ListenerMethod;

public class LoginEvent implements Event {

    private final String username;
    private final String password;

    public interface LoginListener extends Listener {
        @ListenerMethod
        public void login(LoginEvent event);
    }

    public LoginEvent(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
