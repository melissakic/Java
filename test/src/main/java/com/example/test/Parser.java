package com.example.test;

import jakarta.ejb.Stateless;

import java.util.ArrayList;

@Stateless
public class Parser {

    public ArrayList<Integer> parse(Ticket t){
        ArrayList<Integer> nums=new ArrayList<>();
        String[] s=t.getTicket().split(",");
        for(String num:s){
            nums.add(Integer.parseInt(num));
        }
        return nums;
    }
}
