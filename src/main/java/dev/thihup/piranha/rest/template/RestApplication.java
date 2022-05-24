package dev.thihup.piranha.rest.template;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.inject.spi.ProcessAnnotatedType;
import jakarta.enterprise.inject.spi.WithAnnotations;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.ext.Provider;
import java.util.LinkedHashSet;
import java.util.Set;

@ApplicationPath("/template")
@ApplicationScoped
public class RestApplication extends Application implements Extension {

    private final Set<Class<?>> classes = new LinkedHashSet<>();

    private void getAllRestResources(
        @Observes @WithAnnotations({Path.class, Provider.class}) ProcessAnnotatedType<?> objects) {
        classes.add(objects.getAnnotatedType().getJavaClass());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}