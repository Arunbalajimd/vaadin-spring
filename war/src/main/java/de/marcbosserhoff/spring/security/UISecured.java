package de.marcbosserhoff.spring.security;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface UISecured {
    /**
     * Returns the list of security configuration attributes (e.g. ROLE_USER, ROLE_ADMIN).
     *
     * @return String[] The secure method attributes
     */
    public String[] value();
}
