package com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service;

import com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model.MovieResultModel;

import java.util.ArrayList;

public interface MovieCatalogService {
    ArrayList<MovieResultModel> getMovieInfo(Integer userId);
}
