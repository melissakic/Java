package com.melis.MovieAPI.MovieRatingService.movieRating.boundary.controller;

import com.melis.MovieAPI.MovieRatingService.movieRating.control.service.MovieRatingService;
import com.melis.MovieAPI.MovieRatingService.movieRating.control.service.MovieRatingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MovieRatingRestController {

    private final MovieRatingService movieRatingService;

    @Autowired
    public MovieRatingRestController(MovieRatingServiceImp movieRatingService) {
        this.movieRatingService = movieRatingService;
    }

    @GetMapping("/ratings")
    public Map<String, Double> getRatings(@RequestParam Integer userId) {
        return movieRatingService.getRatings(userId);
    }
}
