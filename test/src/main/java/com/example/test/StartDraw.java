package com.example.test;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/start-draw")
public class StartDraw {

    @Inject
    Repository repo;

    @Inject
    Loto loto;

    @GET
    @Produces("application/json")
    public Response startGame(){
        if(repo.getNumberOfTickets()<3){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Not enough tickets you have "+repo.getNumberOfTickets())
                    .build();
        }
        else return Response.ok(loto.startGame()).build();

    };
}
