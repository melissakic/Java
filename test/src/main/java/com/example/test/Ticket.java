package com.example.test;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name="Ticket.getAll",query = "SELECT t FROM Ticket t")
@NamedQuery(name="Ticket.deleteAll",query = "DELETE FROM Ticket t")
public class Ticket {
    @Id
    private String ticket;

    public Ticket(String ticket){
        this.ticket=ticket;
    }
    public Ticket(){}

    public String getTicket() {
        return ticket;
    }

}
