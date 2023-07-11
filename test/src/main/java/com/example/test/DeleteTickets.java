package com.example.test;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/delete-tickets")
public class DeleteTickets {
    @Inject
    Repository repo;

    @GET
    @Produces("text/plain")
    public String delete(){
        repo.deleteTickets();
        return "Tickets are deleted";
    }
}
