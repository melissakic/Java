package com.example.test;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/quick-pick")
public class QuickPick {

    @Inject
    Generator generator;
    @Inject
    Repository repo;
    @GET
    @Produces("application/json")
    public Response createTicket() {
        Ticket ticket=new Ticket(generator.generateFiveNumbers());
        repo.insert(ticket);
        return Response.ok(ticket).build();
    }
}