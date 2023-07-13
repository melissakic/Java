package com.example.test;

import jakarta.ejb.Stateless;

@Stateless
public class Prize {
    private String gameNumbers;
    private String clientTickets;
    private int thirdPrize;
    private int secondPrize;

    public String getClientTickets() {
        return clientTickets;
    }

    public void setClientTickets(String clientTickets) {
        this.clientTickets = clientTickets;
    }

    private int jackpot;

    public Prize(){}

    public String getGameNumbers() {
        return gameNumbers;
    }

    public void setGameNumbers(String gameNumbers) {
        this.gameNumbers = gameNumbers;
    }

    public int getThirdPrize() {
        return thirdPrize;
    }

    public void setThirdPrize(int thirdPrize) {
        this.thirdPrize = thirdPrize;
    }

    public void setSecondPrize(int secondPrize) {
        this.secondPrize = secondPrize;
    }

    public void setJackpot(int jackpot) {
        this.jackpot = jackpot;
    }

    public int getSecondPrize() {
        return secondPrize;
    }

    public int getJackpot() {
        return jackpot;
    }

    public Prize(int a, int b, int c){
        thirdPrize=a;
        secondPrize=b;
        jackpot=c;
    }

    public void incrementThird(){
        thirdPrize++;
    }

    public void incrementSecond(){
        secondPrize++;
    }
    public void incrementFirst(){
        jackpot++;
    }
}
