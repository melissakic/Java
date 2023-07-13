package com.example.test;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Singleton
public class Repository {

    @PersistenceContext
    EntityManager manager;

    public void insert(Ticket ticket){
        manager.persist(ticket);
    }

    public Long getNumberOfTickets(){
        return (Long)manager.createQuery("SELECT COUNT(t) FROM Ticket t").getSingleResult();
    }

    public List<Ticket> fetchTickets(){
        return manager.createNamedQuery("Ticket.getAll",Ticket.class).getResultList();
    }

    public void deleteTickets(){
          manager.createNamedQuery("Ticket.deleteAll").executeUpdate();
    }

}
