package de.marcbosserhoff.services;

import de.marcbosserhoff.model.ExampleEntity;
import org.springframework.security.access.annotation.Secured;

public interface ExampleService {

    public ExampleEntity createUserWithNameAndAdress(String name, String address);

    @Secured("ROLE_ADMIN")
    public void delete(Long id);
}
