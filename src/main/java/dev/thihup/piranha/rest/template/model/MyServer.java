package dev.thihup.piranha.rest.template.model;

import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigProperties(prefix = "server")
public class MyServer {

    public String host; // maps the property name server.host
    public int port; // maps to the property name server.port
    private String context; // maps to the property name server.context
    @ConfigProperty(name = "old.location", defaultValue = "")
    public String location; // maps to the property name server.old.location

    public String getContext() {
        return context;
    }
}