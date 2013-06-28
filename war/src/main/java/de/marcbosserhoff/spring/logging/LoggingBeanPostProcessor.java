package de.marcbosserhoff.spring.logging;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Bean prostprocessor for injecting application wide logging implementation into classes.
 * Fields must annotate @Log to get the Logger and must be of type Logger.
 */
public class LoggingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                // Inject logger where Log annotation is set
                if (field.isAnnotationPresent(Log.class)) {
                    org.slf4j.Logger logger = LoggerFactory.getLogger(bean.getClass());
                    ReflectionUtils.makeAccessible(field);
                    field.set(bean, logger);
                }
            }
        });
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}