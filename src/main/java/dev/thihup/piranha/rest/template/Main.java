package dev.thihup.piranha.rest.template;

import cloud.piranha.embedded.EmbeddedPiranha;
import cloud.piranha.embedded.EmbeddedPiranhaBuilder;
import cloud.piranha.extension.herring.HerringExtension;
import cloud.piranha.extension.weld.WeldInitializer;
import cloud.piranha.http.impl.DefaultHttpServer;
import cloud.piranha.http.webapp.HttpWebApplicationServerProcessor;

public final class Main {

    public static void main(String[] args) {
        EmbeddedPiranha piranha = new EmbeddedPiranhaBuilder()
            .initializers(RestInitializer.class, WeldInitializer.class)
            .extensions(HerringExtension.class)
            .buildAndStart();
        var httpProcessor = new HttpWebApplicationServerProcessor(piranha);
        var httpServer = new DefaultHttpServer(8080, httpProcessor, false);
        httpServer.start();
    }
}
