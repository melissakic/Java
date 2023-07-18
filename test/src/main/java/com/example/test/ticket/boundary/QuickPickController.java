package com.example.test.ticket.boundary;

import com.example.test.ticket.control.service.TicketService;
import com.example.test.ticket.entity.model.TicketModel;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/quick-pick")
public class QuickPickController {

    @Inject
    TicketService ticketService;

    @GET
    @Produces("application/json")
    public TicketModel createTicket() {
        return ticketService.generateNewTicket();
    }
}