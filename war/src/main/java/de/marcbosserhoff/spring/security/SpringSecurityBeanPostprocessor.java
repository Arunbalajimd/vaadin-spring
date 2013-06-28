package de.marcbosserhoff.spring.security;

import com.vaadin.ui.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class SpringSecurityBeanPostprocessor implements BeanPostProcessor {

    private Logger logger = LoggerFactory.getLogger(SpringSecurityBeanPostprocessor.class);

    @Autowired
    private SpringAuthentication authentication;

    @Override
    public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                if (field.isAnnotationPresent(UISecured.class)) {
                    UISecured secured = field.getAnnotation(UISecured.class);
                    if (field.getType().isAssignableFrom(Component.class)) {
                        logger.debug("Checking current roles for vaadin component: " + field.getName() + " in class " + bean.getClass().getName() + " if user has role " + secured.value());
                    }
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
