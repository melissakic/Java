package com.example.test.loto.control.service;

import com.example.test.NotEnoughTicketsException;
import com.example.test.loto.entity.dto.PreviousPrizeModel;
import com.example.test.loto.entity.model.PrizeModel;
import com.example.test.ticket.control.service.TicketService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class LotoService {
    @Inject
    TicketService ticketService;
    @Inject
    LotoEngine lotoEngine;
    @Inject
    PreviousPrizeModel previousPrizeModel;

    public PrizeModel startGame() throws NotEnoughTicketsException {
        if (ticketService.numberOfTickets() < 3) {
            throw new NotEnoughTicketsException();
        } else {
            previousPrizeModel.setPreviousPrize(lotoEngine.startGame());
            ticketService.deleteTickets();
            return previousPrizeModel.getPreviousPrize();
        }
    }

    public PrizeModel getPreviousResults() {
        return previousPrizeModel.getPreviousPrize();
    }
}
