package com.melis.MovieAPI.MovieInfoService.movieInfo.control.service;

import com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model.MovieResultModel;

public interface MovieInfoService {

    MovieResultModel getMovieInfo(Integer movieId);
}
