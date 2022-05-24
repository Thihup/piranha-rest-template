package dev.thihup.piranha.rest.template.model;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.QueryParam;

public class MyModel {

    @QueryParam("foo")
    @NotNull
    private String foo;
    @QueryParam("bar")
    private String bar;

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }
}
