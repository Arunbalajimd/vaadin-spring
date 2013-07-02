package de.marcbosserhoff.vaadin.configuration;

/**
 * Configuration implementation for vaadin components. Defaults for propagation to vaadin components can be loaded
 * from property files or injected via UIPrepare annotation. The properties are dynamically applied via
 * reflection.
 */
public class VaadinConfigurator {

    public static configureClass(Class<?> instance) {
    }
}
