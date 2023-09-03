package com.melis.MovieAPI.MovieRatingService.movieRating.control.service;

import java.util.Map;

public interface MovieRatingService {

    Map<String, Double> getRatings(Integer userId);
}
