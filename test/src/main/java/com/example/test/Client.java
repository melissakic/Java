package com.example.test;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/loto")
public class Client extends Application {
    @GET
    @Produces("text/plain")
    public String home(){
        return "Welcome to loto game to generate ticket go to /quick-pick to start go to /start-draw";
    }
}