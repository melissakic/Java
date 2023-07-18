package com.example.test.ticket.control.service;

import com.example.test.ticket.entity.model.TicketModel;
import jakarta.ejb.Stateless;

import java.util.ArrayList;

@Stateless
public class TicketParser {

    public ArrayList<Integer> parse(TicketModel t) {
        ArrayList<Integer> nums = new ArrayList<>();
        String[] s = t.getTicket().split(",");
        for (String num : s) {
            nums.add(Integer.parseInt(num));
        }
        return nums;
    }
}
