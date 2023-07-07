package com.example.test;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class Repository {

    @PersistenceContext
    EntityManager manager;

    public void insert(){
        Student melis=new Student(20129,"Melis","Sakic");
        manager.merge(melis);
    }
}
