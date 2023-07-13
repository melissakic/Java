package com.example.test;

import jakarta.persistence.*;

@Entity
@NamedQuery(name="Ticket.getAll",query = "SELECT t FROM Ticket t")
@NamedQuery(name="Ticket.deleteAll",query = "DELETE FROM Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticket;



    public Ticket(String ticket){
        this.ticket=ticket;
    }
    public Ticket(){}

    public String getTicket() {
        return ticket;
    }

}
