package de.marcbosserhoff.services;

import de.marcbosserhoff.model.ExampleEntity;

import java.util.List;

public interface ExampleService {

    public ExampleEntity createUserWithNameAndAdress(String name, String address);

    public void delete(Long id);

    public List<ExampleEntity> findAll();

    public Iterable<ExampleEntity> findByName(String name);

    long count();

    ExampleEntity findUserById(Long id);
}
