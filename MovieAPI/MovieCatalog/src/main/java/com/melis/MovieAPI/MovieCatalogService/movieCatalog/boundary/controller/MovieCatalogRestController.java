package com.melis.MovieAPI.MovieCatalogService.movieCatalog.boundary.controller;

import com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service.MovieCatalogService;
import com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service.MovieCatalogServiceImp;
import com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model.MovieResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MovieCatalogRestController {

    private final MovieCatalogService movieCatalogService;

    @Autowired
    public MovieCatalogRestController(MovieCatalogServiceImp movieCatalogService) {
        this.movieCatalogService = movieCatalogService;
    }

    @GetMapping("/movie")
    public ArrayList<MovieResultModel> getMovie(@RequestParam Integer userId) {
        return movieCatalogService.getMovieInfo(userId);
    }

}
