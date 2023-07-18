package com.example.test.ticket.control.service;

import com.example.test.ticket.control.repository.TicketRepository;
import com.example.test.ticket.entity.model.TicketModel;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class TicketService {
    @Inject
    TicketGenerator ticketGenerator;
    @Inject
    TicketRepository ticketRepository;
    @Inject
    TicketParser ticketParser;

    public TicketModel generateNewTicket() {
        TicketModel generatedTicket = new TicketModel(ticketGenerator.generateFiveNumbers());
        ticketRepository.insert(generatedTicket);
        return generatedTicket;
    }

    public void deleteTickets() {
        ticketRepository.deleteTickets();
    }

    public long numberOfTickets() {
        return ticketRepository.getNumberOfTickets();
    }

    public ArrayList<Integer> ticketParse(TicketModel ticket) {
        return ticketParser.parse(ticket);
    }

    public List<TicketModel> fetchTickets() {
        return ticketRepository.fetchTickets();
    }
}
