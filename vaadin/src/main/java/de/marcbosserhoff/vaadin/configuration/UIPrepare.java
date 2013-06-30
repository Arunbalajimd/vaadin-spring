package de.marcbosserhoff.vaadin.configuration;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UIPrepare {
}
