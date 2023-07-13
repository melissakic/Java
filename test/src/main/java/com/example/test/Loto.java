package com.example.test;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class Loto {

    @Inject
    private Repository repo;
    @Inject
    private Parser parser;

    @Inject
    private Generator generator;

    private Ticket gameNumbers;
    private List<Ticket> yourTickets;
    public Loto(){
        gameNumbers=null;
        yourTickets=null;
    }

    public String generateGameNumbers(){
        String generatedNumbers=generator.generateFiveNumbers();
        gameNumbers=new Ticket(generatedNumbers);
        return generatedNumbers;
    }

    public String getUserTickets(){
        StringBuilder result= new StringBuilder();
        yourTickets=repo.fetchTickets();
        for(Ticket t:yourTickets){
            result.append(t.getTicket()).append("|");
        }
        return result.toString();
    }


    public Prize startGame(){
        Prize prize=new Prize(0,0,0);
        prize.setClientTickets(getUserTickets());
        prize.setGameNumbers(generateGameNumbers());
        int shots;
        ArrayList<Integer> game=parser.parse(gameNumbers);
        for(Ticket t:yourTickets){
        ArrayList<Integer> user=parser.parse(t);
        shots=0;
            for (int i = 0; i <game.size() ; i++) {
                if(game.get(i).equals(user.get(i)))shots++;
            }
            switch (shots) {
                case 3 -> prize.incrementThird();
                case 4 -> prize.incrementSecond();
                case 5 -> prize.incrementFirst();
            }
    }
        return prize;
    }
}
