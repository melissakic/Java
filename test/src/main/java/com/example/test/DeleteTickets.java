package com.example.test;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/delete-tickets")
public class DeleteTickets {
    @Inject
    Repository repo;

    @GET
    @Produces("application/json")
    public Response delete(){
        repo.deleteTickets();
        return Response.ok("Deleted").build();

    }
}
