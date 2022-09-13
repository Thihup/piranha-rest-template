package dev.thihup.piranha.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/helloworld")
@RequestScoped
public class HelloWorldBean {

    @GET
    public String helloWorld() {
        return "Hello World!";
    }
}