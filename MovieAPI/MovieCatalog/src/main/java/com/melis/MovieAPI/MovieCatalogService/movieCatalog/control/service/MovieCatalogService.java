package com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service;

import java.util.Map;

public interface MovieCatalogService {

    Map<String, Double> getRatingsFromUser(Integer userId);
}
