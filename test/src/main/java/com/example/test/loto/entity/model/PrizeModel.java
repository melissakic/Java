package com.example.test.loto.entity.model;

import com.example.test.ticket.entity.model.TicketModel;
import jakarta.ejb.Stateless;

import java.io.Serializable;
import java.util.List;

@Stateless
public class PrizeModel implements Serializable {
    private TicketModel gameNumbers;
    private List<TicketModel> clientTickets;
    private int thirdPrize;
    private int secondPrize;
    private int jackpot;

    public PrizeModel() {
    }

    public PrizeModel(int a, int b, int c) {
        thirdPrize = a;
        secondPrize = b;
        jackpot = c;
    }

    public List<TicketModel> getClientTickets() {
        return clientTickets;
    }

    public void setClientTickets(List<TicketModel> clientTickets) {
        this.clientTickets = clientTickets;
    }

    public TicketModel getGameNumbers() {
        return gameNumbers;
    }

    public void setGameNumbers(TicketModel gameNumbers) {
        this.gameNumbers = gameNumbers;
    }

    public int getThirdPrize() {
        return thirdPrize;
    }

    public void setThirdPrize(int thirdPrize) {
        this.thirdPrize = thirdPrize;
    }

    public int getSecondPrize() {
        return secondPrize;
    }

    public void setSecondPrize(int secondPrize) {
        this.secondPrize = secondPrize;
    }

    public int getJackpot() {
        return jackpot;
    }

    public void setJackpot(int jackpot) {
        this.jackpot = jackpot;
    }

    public void incrementThird() {
        thirdPrize++;
    }

    public void incrementSecond() {
        secondPrize++;
    }

    public void incrementFirst() {
        jackpot++;
    }
}
