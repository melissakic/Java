package com.example.test;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {

    @Inject
    Repository repo;
    @GET
    @Produces("text/plain")
    public String hello() {
        repo.insert();
        return "Hello, Melis!";
    }
}