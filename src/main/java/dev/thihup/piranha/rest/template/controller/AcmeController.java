package dev.thihup.piranha.rest.template.controller;

import dev.thihup.piranha.rest.template.model.MyModel;
import dev.thihup.piranha.rest.template.service.AcmeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("acme/service")
@RequestScoped
public class AcmeController {

    @Inject
    private AcmeService acmeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object foo(@BeanParam @Valid MyModel model) {
        return acmeService.getProperties();
    }

}
