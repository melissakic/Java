package com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service;

import com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model.ResultModel;

import java.util.ArrayList;

public interface MovieCatalogService {

    ArrayList<ResultModel> getMovieInfo(Integer userId);
}
