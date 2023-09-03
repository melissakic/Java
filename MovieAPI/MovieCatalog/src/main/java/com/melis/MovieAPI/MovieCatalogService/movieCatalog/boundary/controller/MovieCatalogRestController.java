package com.melis.MovieAPI.MovieCatalogService.movieCatalog.boundary.controller;

import com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service.MovieCatalogService;
import com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service.MovieCatalogServiceImp;
import com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class MovieCatalogRestController {

    private final MovieCatalogService movieCatalogService;

    @Autowired
    public MovieCatalogRestController(MovieCatalogServiceImp movieCatalogService) {
        this.movieCatalogService = movieCatalogService;
    }

    @GetMapping("/movie")
    public ArrayList<ResultModel> getMovie(@RequestBody Integer userId) {
        Map<String, Double> ratings = movieCatalogService.getRatingsFromUser(userId);
        ArrayList<ResultModel> results = new ArrayList<>();
        ratings.forEach((movieId, rating) -> {
            ResultModel resultModel = movieCatalogService.getInfoFromUser(Integer.valueOf(movieId)).block();
            resultModel.setUserId(String.valueOf(userId));
            resultModel.setRating(rating);
            results.add(resultModel);
        });
        return results;
    }

}
