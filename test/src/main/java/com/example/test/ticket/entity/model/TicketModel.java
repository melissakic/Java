package com.example.test.ticket.entity.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TICKET")
@NamedQuery(name = "Ticket.getAll", query = "SELECT t FROM TicketModel t")
@NamedQuery(name = "Ticket.deleteAll", query = "DELETE FROM TicketModel")
public class TicketModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ticket;

    public TicketModel(String ticket) {
        this.ticket = ticket;
    }

    public TicketModel() {
    }

    public String getTicket() {
        return ticket;
    }

}
