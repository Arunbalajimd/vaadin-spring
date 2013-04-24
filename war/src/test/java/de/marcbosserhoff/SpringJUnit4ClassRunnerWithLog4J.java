package de.marcbosserhoff;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

public class SpringJUnit4ClassRunnerWithLog4J extends SpringJUnit4ClassRunner {

    static {
        try {
            Log4jConfigurer.initLogging("classpath:log4j-test.xml");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j");
        }
    }

    public SpringJUnit4ClassRunnerWithLog4J(Class<?> clazz) throws InitializationError {
        super(clazz);
    }
}
