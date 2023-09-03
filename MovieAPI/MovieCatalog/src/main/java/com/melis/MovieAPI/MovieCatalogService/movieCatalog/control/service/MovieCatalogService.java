package com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service;

import com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model.ResultModel;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface MovieCatalogService {

    Map<String, Double> getRatingsFromUser(Integer userId);

    Mono<ResultModel> getInfoFromUser(Integer movieId);
}
