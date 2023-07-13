package com.example.test;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/quick-pick")
public class QuickPick {

    @Inject
    Repository repo;
    @GET
    @Produces("text/plain")
    public String createTicket() {
        Ticket ticket=new Ticket(Generator.generateFiveNumbers());
        repo.insert(ticket);
        return "Your ticket is: " + ticket.getTicket();
    }
}