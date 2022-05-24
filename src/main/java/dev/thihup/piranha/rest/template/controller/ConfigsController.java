package dev.thihup.piranha.rest.template.controller;

import dev.thihup.piranha.rest.template.model.MyServer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Map;
import org.eclipse.microprofile.config.inject.ConfigProperties;

@Path("configs")
@RequestScoped
public class ConfigsController {

    @Inject
    @ConfigProperties
    private MyServer myServer;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> returnConfigs() {
        return Map.of(
            "host", myServer.host,
            "port", myServer.port,
            "context", myServer.getContext(),
            "location", myServer.location
        );
    }

}
