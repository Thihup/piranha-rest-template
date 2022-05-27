open module dev.thihup.piranha.rest.template {
    // APIs

    // MicroProfile
    requires microprofile.config.api;

    // Jakarta
    requires jakarta.activation;
    requires jakarta.cdi;
    requires jakarta.el;
    requires jakarta.inject;
    requires jakarta.json.bind;
    requires jakarta.validation;
    requires jakarta.ws.rs;

    // Implementations

    // Piranha
    requires cloud.piranha.embedded;
    requires cloud.piranha.http.impl;
    requires cloud.piranha.http.webapp;
    requires cloud.piranha.extension.herring;
    requires cloud.piranha.extension.weld;


    // Jersey
    requires jersey.container.servlet;

    // Smallrye
    requires smallrye.config;
    requires smallrye.config.common;
    requires smallrye.config.core;

    // Bytebuddy - Workaround Jakarta Validation API bug
    requires java.instrument;
    requires net.bytebuddy.agent;
    requires jdk.attach;

}