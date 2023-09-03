package com.melis.MovieAPI.MovieRatingService.movieRating.boundary.controller;

import com.melis.MovieAPI.MovieRatingService.movieRating.control.service.MovieRatingService;
import com.melis.MovieAPI.MovieRatingService.movieRating.control.service.MovieRatingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MovieRatingRestControler {

    private final MovieRatingService movieRatingService;

    @Autowired
    public MovieRatingRestControler(MovieRatingServiceImp movieRatingService) {
        this.movieRatingService = movieRatingService;
    }

    @GetMapping("/ratings")
    public Map<String, Double> getRatings(@RequestBody Integer userId) {
        return movieRatingService.getRatings(userId);
    }
}
