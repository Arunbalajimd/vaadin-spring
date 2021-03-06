package de.marcbosserhoff.repositories;

import de.marcbosserhoff.model.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ExampleRepository extends JpaRepository<ExampleEntity, Long>, QueryDslPredicateExecutor<ExampleEntity> {

    /**
     * Gets an entity by name.
     *
     * @param name The name of the person
     * @return An entity with the person with the given name
     */
    ExampleEntity findByName(String name);

}
