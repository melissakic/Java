package com.example.test;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    private int indeks;
    private String ime;
    private String prezime;

    public Student(int indeks,String ime,String prezime){
        this.ime=ime;
        this.prezime=prezime;
        this.indeks=indeks;
    }
    public Student(){}
}
