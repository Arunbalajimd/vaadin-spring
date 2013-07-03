package de.marcbosserhoff.tools;

/**
 * Additional Reflection-Methods not covered by Spring or Apache Commons Lang-Library.
 */
public class ReflectionUtils {


    /**
     * Returns true, if given instance extends or implements given class or interface.
     * @param instance The instance to check
     * @param forClassOrInterface The implemented class to check for
     * @return true, if given instance extends or implements given class or interface.
     */
    public static boolean extendsOrImplements(Object instance, Class<?> forClassOrInterface) {
        if (instance != null)
            return extendsOrImplements(instance.getClass(), forClassOrInterface);

        return false;
    }

    /**
     * Returns true, if given class extends or implements given class or interface.
     * @param toCheck The class to check
     * @param forClassOrInterface The implemented class to check for
     * @return true, if given class extends or implements given class or interface.
     */
    public static boolean extendsOrImplements(Class<?> toCheck, Class<?> forClassOrInterface) {
        return (toCheck != null && forClassOrInterface != null && forClassOrInterface.isAssignableFrom(toCheck));
    }

    /**
     * Casts an instance to the given class T or returns null, if casting is not possible.
     *
     * This allow casting without catching exceptions or type checking. One just has to
     * check if object is null before using this method, as null in return also means
     * unsuccessful casting.
     *
     * @param instance An instance to cast
     * @param <T> The target type for casting
     * @param clazz A class in the target type
     * @return The casted instance or null, if a ClassCastException has occurred
     */
    public static <T> T safeCast(Object instance, Class<T> clazz) {
        if (instance != null && extendsOrImplements(instance.getClass(), clazz))
            return (T) instance;

        return null;
    }
}
