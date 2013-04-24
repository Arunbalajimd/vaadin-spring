package de.marcbosserhoff;

import de.marcbosserhoff.model.ExampleEntity;
import de.marcbosserhoff.repositories.ExampleRepository;
import de.marcbosserhoff.services.ExampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

@ContextConfiguration("classpath*:applicationContext-test.xml")
@RunWith(SpringJUnit4ClassRunnerWithLog4J.class)
public class ExampleTest {

    @Autowired
    ExampleService exampleService;

    @Autowired
    ExampleRepository exampleRepository;

    @Test
    public void exampleTest() {
        String name = "TESTUSER";
        String address = "TESTADDRESS";

        // Create new entity and check its existence
        ExampleEntity user = exampleService.createUserWithNameAndAdress(name, address);
        assertThat(exampleRepository.count(), is(equalTo(1l)));

        // Load entity and check if its equal to local values
        ExampleEntity userInDb = exampleRepository.findOne(user.getId());
        assertThat(user.getId(), is(equalTo(userInDb.getId())));
        assertThat(user.getName().equals(userInDb.getName()), is(true));
        assertThat(user.getAddress().equals(userInDb.getAddress()), is(true));
    }
}
