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

    private Ticket gameNumbers;
    private List<Ticket> yourTickets;
    public Loto(){
        gameNumbers=null;
        yourTickets=null;
    }

    public String generateGameNumbers(){
        String generatedNumbers=Generator.generateFiveNumbers();
        gameNumbers=new Ticket(generatedNumbers);
        return "Game ticket is:"+generatedNumbers;
    }

    public String getUserTickets(){
        String result="Your tickets are:\n";
        yourTickets=repo.fetchTickets();
        for(Ticket t:yourTickets){
            result+=t.getTicket()+"\n";
        }
        return  result;
    }


    public String startGame(){
         int prizeThird=0;
         int prizeSecond=0;
         int prizeFirst=0;
        int shots=0;
        ArrayList<Integer> game=parser.parse(gameNumbers);
        for(Ticket t:yourTickets){
        ArrayList<Integer> user=parser.parse(t);
        shots=0;
            for (int i = 0; i <game.size() ; i++) {
                if(game.get(i).equals(user.get(i)))shots++;
            }
            switch (shots) {
                case 3 -> prizeThird++;
                case 4 -> prizeSecond++;
                case 5 -> prizeFirst++;
            }
    }
        return "Number of third level prizes:"+prizeThird+"\n"+"Number of second level prizes:"+prizeSecond+
                "\n"+"Number of jackpots:"+prizeFirst;
    }
}
