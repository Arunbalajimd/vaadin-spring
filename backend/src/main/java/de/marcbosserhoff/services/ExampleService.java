package de.marcbosserhoff.services;

import de.marcbosserhoff.model.ExampleEntity;

public interface ExampleService {

    public ExampleEntity createUserWithNameAndAdress(String name, String address);

    public void delete(Long id);
}
