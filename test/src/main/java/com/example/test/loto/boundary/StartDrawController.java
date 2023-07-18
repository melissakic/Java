package com.example.test.loto.boundary;

import com.example.test.NotEnoughTicketsException;
import com.example.test.loto.control.service.LotoService;
import com.example.test.loto.entity.model.PrizeModel;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/start-draw")
public class StartDrawController {

    @Inject
    LotoService lotoService;

    @GET
    @Produces("application/json")
    public PrizeModel startGame() {
        try {
            return lotoService.startGame();
        } catch (NotEnoughTicketsException notEnoughTicketsException) {
            return new PrizeModel();
        }
    }
}
