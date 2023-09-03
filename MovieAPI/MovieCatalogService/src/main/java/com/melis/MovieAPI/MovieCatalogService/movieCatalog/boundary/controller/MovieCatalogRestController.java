package com.melis.MovieAPI.MovieCatalogService.movieCatalog.boundary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class MovieCatalogRestController {


    private final WebClient webClient;

    @Autowired
    public MovieCatalogRestController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/movie")
    public Integer getMovie(@RequestBody Integer userId) {
        return 2;
    }
}
