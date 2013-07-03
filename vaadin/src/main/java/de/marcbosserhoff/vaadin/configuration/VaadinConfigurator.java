package de.marcbosserhoff.vaadin.configuration;

import com.vaadin.ui.Component;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Configuration implementation for vaadin components. Defaults for propagation to vaadin components can be loaded
 * from property files or injected via UIPrepare annotation. The properties are dynamically applied via
 * reflection.
 */
public class VaadinConfigurator {

    private static final String COMPONENT_VISIBLE_PROPERTY = "visible";


    private BaseConfiguration theme;

    public void loadTheme(String themePropertyFile) throws ConfigurationException {
        theme = new PropertiesConfiguration(themePropertyFile);
    }

    public void configure(Component instance) {
        if (instance == null)
            return;

        configureComponent(instance);
    }

    private void configureComponent(Component instance) {
        instance.setVisible(getThemeProperty("visible"));
    }

    private boolean getThemeProperty(String visible) {
        return false;
    }

}
