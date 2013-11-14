package de.marcbosserhoff;

import de.marcbosserhoff.model.ExampleEntity;
import de.marcbosserhoff.services.ExampleService;
import de.marcbosserhoff.test.SpringJUnit4ClassRunnerWithLog4J;
import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("classpath*:test-context.xml")
@RunWith(SpringJUnit4ClassRunnerWithLog4J.class)
@TransactionConfiguration
public class ExampleTest {

    @Autowired
    ExampleService exampleService;

    @Test
    public void exampleTest() {
        String name = "TESTUSER";
        String address = "TESTADDRESS";

        // Create new entity and check its existence
        ExampleEntity user = exampleService.createUserWithNameAndAdress(name, address);
        assertEquals(exampleService.count(), 1L);

        // Load entity and check if its equal to local values
        ExampleEntity userInDb = exampleService.findUserById(user.getId());
        assertEquals(user.getId(), userInDb.getId());
        assertEquals(user.getName(), userInDb.getName());
        assertEquals(user.getAddress(), userInDb.getAddress());

        final List<ExampleEntity> users = IteratorUtils.toList(exampleService.findByName(name).iterator());
        assertEquals(1, users.size());
        final ExampleEntity entity = users.get(0);
        assertEquals(user.getId(), entity.getId());
        assertEquals(user.getName(), entity.getName());
        assertEquals(user.getAddress(), entity.getAddress());
    }
}
