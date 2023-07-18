package com.example.test.loto.control.service;

import com.example.test.loto.entity.model.PrizeModel;
import com.example.test.ticket.control.service.TicketService;
import com.example.test.ticket.entity.model.TicketModel;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class LotoEngine {
    @Inject
    private TicketService ticketService;
    private TicketModel gameNumbers;
    private List<TicketModel> yourTickets;

    public LotoEngine() {
        gameNumbers = null;
        yourTickets = null;
    }

    public TicketModel generateGameNumbers() {
        gameNumbers = ticketService.generateNewTicket();
        return gameNumbers;
    }

    public List<TicketModel> getUserTickets() {
        yourTickets = ticketService.fetchTickets();
        return yourTickets;
    }


    public PrizeModel startGame() {
        PrizeModel prizeModel = new PrizeModel(0, 0, 0);
        prizeModel.setClientTickets(getUserTickets());
        prizeModel.setGameNumbers(generateGameNumbers());
        int shots;
        ArrayList<Integer> game = ticketService.ticketParse(gameNumbers);
        for (TicketModel ticket : yourTickets) {
            ArrayList<Integer> user = ticketService.ticketParse(ticket);
            shots = 0;
            for (int i = 0; i < game.size(); i++) {
                if (game.get(i).equals(user.get(i))) shots++;
            }
            switch (shots) {
                case 3 -> prizeModel.incrementThird();
                case 4 -> prizeModel.incrementSecond();
                case 5 -> prizeModel.incrementFirst();
            }
        }
        return prizeModel;
    }


}
