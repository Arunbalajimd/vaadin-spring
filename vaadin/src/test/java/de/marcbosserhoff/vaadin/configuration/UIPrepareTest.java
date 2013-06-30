package de.marcbosserhoff.vaadin.configuration;

import de.marcbosserhoff.test.SpringJUnit4ClassRunnerWithLog4J;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath*:test-context.xml")
@RunWith(SpringJUnit4ClassRunnerWithLog4J.class)
public class UIPrepareTest {

    @Test
    public void prepareTest() {

    }
}
