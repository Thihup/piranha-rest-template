package dev.thihup.piranha.rest.template;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.util.Set;
import org.glassfish.jersey.servlet.init.JerseyServletContainerInitializer;

public class RestInitializer implements ServletContainerInitializer {

    private final ServletContainerInitializer restInitializer = new JerseyServletContainerInitializer();

    @Override
    public void onStartup(Set<Class<?>> classes, ServletContext servletContext)
        throws ServletException {
        restInitializer.onStartup(Set.of(RestApplication.class), servletContext);
    }
}
