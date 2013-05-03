package de.marcbosserhoff.services;

import de.marcbosserhoff.model.ExampleEntity;
import de.marcbosserhoff.repositories.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    @Transactional
    public ExampleEntity createUserWithNameAndAdress(String name, String address) {
        ExampleEntity user = new ExampleEntity();
        user.setName(name);
        user.setAddress(address);

        exampleRepository.save(user);
        exampleRepository.flush();

        return user;
    }

    @Transactional
    public void delete(Long id) {
        exampleRepository.delete(id);
    }
}
