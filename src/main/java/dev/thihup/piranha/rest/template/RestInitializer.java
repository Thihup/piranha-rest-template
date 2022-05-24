package dev.thihup.piranha.rest.template;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.util.Set;
import org.jboss.resteasy.plugins.servlet.ResteasyServletInitializer;

public class RestInitializer implements ServletContainerInitializer {

    private final ServletContainerInitializer resteasyServletInitializer = new ResteasyServletInitializer();

    @Override
    public void onStartup(Set<Class<?>> classes, ServletContext servletContext)
        throws ServletException {
        servletContext.setInitParameter("resteasy.injector.factory",
            "org.jboss.resteasy.cdi.CdiInjectorFactory");

        resteasyServletInitializer.onStartup(Set.of(RestApplication.class), servletContext);
    }
}
