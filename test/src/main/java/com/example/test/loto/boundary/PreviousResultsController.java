package com.example.test.loto.boundary;

import com.example.test.loto.control.service.LotoService;
import com.example.test.loto.entity.model.PrizeModel;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/previous-results")
public class PreviousResultsController {
    @Inject
    LotoService lotoService;

    @GET
    @Produces("application/json")
    public PrizeModel getPreviousResults() {
        return lotoService.getPreviousResults();
    }

}
