package dev.thihup.piranha.rest.template.config;

import cloud.piranha.core.api.WebApplication;
import cloud.piranha.embedded.EmbeddedPiranha;
import cloud.piranha.http.api.HttpServer;
import cloud.piranha.http.webapp.HttpWebApplicationServerProcessor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Destroyed;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.enterprise.inject.spi.Unmanaged;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import java.util.Optional;
import java.util.ServiceLoader;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class HttpServerConfigurator {

    private final Optional<Class<HttpServer>> httpServerClass;
    private final BeanManager beanManager;
    private HttpServer httpServer;

    @Inject
    public HttpServerConfigurator(
        @ConfigProperty(name = "http.server.class") Optional<Class<HttpServer>> httpServerClass,
        BeanManager beanManager) {
        this.httpServerClass = httpServerClass;
        this.beanManager = beanManager;
    }

    private <T extends HttpServer> T create(Class<T> x) {
        Unmanaged<T> tUnmanaged = new Unmanaged<>(beanManager, x);
        return tUnmanaged.newInstance().produce().inject().postConstruct().get();
//        try {
//            return x.getDeclaredConstructor().newInstance();
//        } catch (InstantiationException | IllegalAccessException |
//                 InvocationTargetException | NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
    }

    public HttpServer configure(
        @Observes @Initialized(ApplicationScoped.class) ServletContext piranhaContext) {
        EmbeddedPiranha embeddedPiranha = new EmbeddedPiranha((WebApplication) piranhaContext);
        var httpProcessor = new HttpWebApplicationServerProcessor(embeddedPiranha);

        httpServer = httpServerClass
            .map(this::create)
            .orElseGet(() -> ServiceLoader.load(HttpServer.class).findFirst().orElseThrow());

        httpServer.setHttpServerProcessor(httpProcessor);
        httpServer.start();
        return httpServer;
    }


    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object ignored) {
        httpServer.stop();
    }
}
