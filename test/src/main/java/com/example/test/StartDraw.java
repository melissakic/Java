package com.example.test;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/start-draw")
public class StartDraw {

    @Inject
    Repository repo;

    @Inject
    Loto loto;

    @GET
    @Produces("text/plain")
    public String startGame(){
        if(repo.getNumberOfTickets()<3) return "To start game you must have at least 3 tickets you have " + repo.getNumberOfTickets();
        String flow="Starting game...\n";
        flow+=loto.generateGameNumbers()+"\n";
        flow+=loto.getUserTickets();
        flow+=loto.startGame()+"\n";
        return flow;
    };
}
