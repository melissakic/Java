package com.melis.MovieAPI.MovieInfoService.movieInfo.control.service;

import com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model.MovieResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieInfoServiceImp implements MovieInfoService {

    private final WebClient webClient;
    private final APIKey apiKey;

    @Autowired
    public MovieInfoServiceImp(@Qualifier("movieAPIClient") WebClient webClient, APIKey apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    @Override
    public MovieResultModel getMovieInfo(Integer movieId) {
        MovieResultModel movieResultModel = webClient
                .get()
                .uri(movieId + apiKey.getKey())
                .retrieve()
                .bodyToMono(MovieResultModel.class).block();
        if (movieResultModel != null) {
            movieResultModel.setMovieId(String.valueOf(movieId));
        }
        return movieResultModel;
    }
}
