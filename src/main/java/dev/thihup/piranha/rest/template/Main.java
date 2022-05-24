package dev.thihup.piranha.rest.template;

import cloud.piranha.embedded.EmbeddedPiranha;
import cloud.piranha.embedded.EmbeddedPiranhaBuilder;
import cloud.piranha.extension.herring.HerringExtension;
import cloud.piranha.extension.weld.WeldInitializer;
import cloud.piranha.http.impl.DefaultHttpServer;
import cloud.piranha.http.webapp.HttpWebApplicationServerProcessor;
import jakarta.validation.spi.BootstrapState;
import jakarta.validation.spi.ConfigurationState;
import jakarta.validation.spi.ValidationProvider;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.agent.ByteBuddyAgent;

public final class Main {

    public static void main(String[] args) {
        ModuleLayer.boot().findModule("jakarta.validation").ifPresent(validationModule -> {
            ByteBuddyAgent.install()
                .redefineModule(validationModule, Set.of(), Map.of(), Map.of(),
                    Set.of(ValidationProvider.class, BootstrapState.class,
                        ConfigurationState.class),
                    Map.of());
        });

        EmbeddedPiranha piranha = new EmbeddedPiranhaBuilder()
            .initializers(RestInitializer.class, WeldInitializer.class)
            .extensions(HerringExtension.class)
            .buildAndStart();
        var httpProcessor = new HttpWebApplicationServerProcessor(piranha);
        var httpServer = new DefaultHttpServer(8080, httpProcessor, false);
        httpServer.start();
    }
}
