package dev.thihup.piranha.rest.template;

import cloud.piranha.embedded.EmbeddedPiranha;
import cloud.piranha.embedded.EmbeddedPiranhaBuilder;
import cloud.piranha.extension.herring.HerringExtension;
import cloud.piranha.extension.weld.WeldInitializer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {

    private static final Runtime RUNTIME = Runtime.getRuntime();

    public static void main(String[] args) {
        Supplier<EmbeddedPiranha> startup = () -> new EmbeddedPiranhaBuilder()
            .initializers(RestInitializer.class, WeldInitializer.class)
            .extensions(HerringExtension.class)
            .buildAndStart();

        Consumer<EmbeddedPiranha> shutdownHook = piranha -> RUNTIME.addShutdownHook(
            Thread.ofPlatform().unstarted(() -> piranha.stop().destroy()));

        new Thread(() -> CompletableFuture.supplyAsync(startup, Executors.newSingleThreadExecutor(Thread.ofPlatform()
            .name("Piranha-Startup")
            .daemon(false)
            .factory())).thenAccept(shutdownHook)).start();
    }
}
