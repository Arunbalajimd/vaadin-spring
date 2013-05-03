package de.marcbosserhoff.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Scope("prototype")
public class SpringAuthentication {

    private static Logger log = LoggerFactory.getLogger(SpringAuthentication.class);

    @Resource
    private AuthenticationManager authenticationManager;

    public boolean login(String username, String password) {
        boolean isAuthenticated = false;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            isAuthenticated = authentication.isAuthenticated();
            if (isAuthenticated)
                log.info("User {} successfully logged in", username);
        } catch (Exception e) {
            log.warn("Authentication failed for user {}", username, e);
        }
        return isAuthenticated;
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }


}
