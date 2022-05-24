package dev.thihup.piranha.rest.template.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@ApplicationScoped
@Provider
public class JsonbConfigurator implements ContextResolver<Jsonb> {

    @Override
    public Jsonb getContext(Class<?> aClass) {
        JsonbConfig jsonbConfig = new JsonbConfig()
            .withFormatting(true);
        return JsonbBuilder
            .create(jsonbConfig);
    }
}
