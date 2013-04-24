package de.marcbosserhoff;

import de.marcbosserhoff.repositories.ExampleRepository;
import de.marcbosserhoff.services.ExampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath*:applicationContext-test.xml")
@RunWith(SpringJUnit4ClassRunnerWithLog4J.class)
public class ExampleTest {

    @Autowired
    ExampleService exampleService;

    @Autowired
    ExampleRepository exampleRepository;

    @Test
    public void exampleTest() {

    }
}
