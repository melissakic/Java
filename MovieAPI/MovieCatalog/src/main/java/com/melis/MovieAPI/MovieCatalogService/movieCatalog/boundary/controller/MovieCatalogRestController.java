package com.melis.MovieAPI.MovieCatalogService.movieCatalog.boundary.controller;

import com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service.MovieCatalogService;
import com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service.MovieCatalogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MovieCatalogRestController {

    private final MovieCatalogService movieCatalogService;

    @Autowired
    public MovieCatalogRestController(MovieCatalogServiceImp movieCatalogService) {
        this.movieCatalogService = movieCatalogService;
    }

    @GetMapping("/movie")
    public void getMovie(@RequestBody Integer userId) {
        Map<String, Double> ratings = movieCatalogService.getRatingsFromUser(userId);
    }

}
