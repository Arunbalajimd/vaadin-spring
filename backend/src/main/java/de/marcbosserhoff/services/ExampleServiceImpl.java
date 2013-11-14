package de.marcbosserhoff.services;

import de.marcbosserhoff.model.ExampleEntity;
import de.marcbosserhoff.model.QExampleEntity;
import de.marcbosserhoff.repositories.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    @Transactional
    public ExampleEntity createUserWithNameAndAdress(String name, String address) {
        ExampleEntity user = new ExampleEntity();
        user.setName(name);
        user.setAddress(address);
        return exampleRepository.saveAndFlush(user);
    }

    @Transactional
    public void delete(Long id) {
        exampleRepository.delete(id);
    }

    @Override
    public List<ExampleEntity> findAll() {
        return exampleRepository.findAll();
    }

    @Override
    public Iterable<ExampleEntity> findByName(String name) {
        final QExampleEntity entity = QExampleEntity.exampleEntity;
        return exampleRepository.findAll(entity.name.eq(name));
    }

    @Override
    public long count() {
        return exampleRepository.count();
    }

    @Override
    public ExampleEntity findUserById(Long id) {
        return exampleRepository.findOne(id);
    }
}
