package de.marcbosserhoff.tools;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class ReflectionUtilsTest {

    private interface IReflection {
    }

    private class IReflectionClass implements IReflection {
    }

    @Test
    public void extendsOrImplementsTest() {
        Number longValue = Long.valueOf(1l);
        Number intValue = Integer.valueOf(2);

        // Null should implement nothing
        assertThat(ReflectionUtils.extendsOrImplements(null, Object.class), is(false));

        assertThat(ReflectionUtils.extendsOrImplements(longValue, Long.class), is(true));
        assertThat(ReflectionUtils.extendsOrImplements(longValue, Integer.class), is(false));

        // Number will not extend or implement Integer
        assertThat(ReflectionUtils.extendsOrImplements(Number.class, Integer.class), is(false));
        // But Integer extends Number
        assertThat(ReflectionUtils.extendsOrImplements(Integer.class, Number.class), is(true));
        // The have the same super type but are not assignable to each other
        assertThat(ReflectionUtils.extendsOrImplements(longValue.getClass(), intValue.getClass()), is(false));
    }

    @Test
    public void safeCastTest() {
        // Null cannot be cast to anything
        assertThat(ReflectionUtils.safeCast(null, Object.class), is(nullValue()));

        IReflectionClass classWithInterface = new IReflectionClass();

        // Casting to interface should succeed
        IReflection castSuccess = ReflectionUtils.safeCast(classWithInterface, IReflection.class);
        assertThat(castSuccess, is(notNullValue()));

        // Boolean is not a part of the class and should fail
        Boolean castFailure = ReflectionUtils.safeCast(classWithInterface, Boolean.class);
        assertThat(castFailure, is(nullValue()));

        // Casting back and forth through the hierarchy should succeed
        Object object = ReflectionUtils.safeCast(castSuccess, Object.class);
        IReflectionClass originalClass = ReflectionUtils.safeCast(object, IReflectionClass.class);
        assertThat(originalClass, is(notNullValue()));
    }
}
