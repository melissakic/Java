package com.example.test;

import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Singleton
public class Repository {

    @PersistenceContext
    EntityManager manager;

    public void insert(){
        Student melis=new Student(20129,"Melis","Sakic");
        manager.merge(melis);
    }
}
