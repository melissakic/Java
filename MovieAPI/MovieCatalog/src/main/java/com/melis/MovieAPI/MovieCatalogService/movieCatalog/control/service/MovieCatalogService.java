package com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service;

import org.melis.model.MovieResultModel;

import java.util.ArrayList;

public interface MovieCatalogService {
    ArrayList<MovieResultModel> getInfo(Integer userId);
}
