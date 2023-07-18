package com.example.test.ticket.control.repository;

import com.example.test.ticket.entity.model.TicketModel;
import jakarta.ejb.Singleton;
import jakarta.persistence.*;

import java.util.List;

@Singleton
public class TicketRepository {

    @PersistenceContext
    EntityManager manager;

    public void insert(TicketModel ticket) {
        manager.persist(ticket);
    }

    public Long getNumberOfTickets() {
        return (Long) manager.createQuery("SELECT COUNT(t) FROM TicketModel t").getSingleResult();
    }

    public List<TicketModel> fetchTickets() {
        return manager.createNamedQuery("Ticket.getAll", TicketModel.class).getResultList();
    }

    public void deleteTickets() {
        manager.createNamedQuery("Ticket.deleteAll").executeUpdate();
    }

}
