package com.melis.MovieAPI.MovieInfoService.movieInfo.control.service;

import com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model.ResultModel;

public interface MovieInfoService {

    ResultModel getMovieInfo(Integer movieId);
}
