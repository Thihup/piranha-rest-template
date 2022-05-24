package dev.thihup.piranha.rest.template.service;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.Config;

@Singleton
public class AcmeService {

    @Inject
    private Config config;

    public Object getProperties() {
        return config;
    }

}
