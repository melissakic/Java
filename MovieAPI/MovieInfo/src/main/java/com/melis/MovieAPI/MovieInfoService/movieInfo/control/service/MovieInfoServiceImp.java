package com.melis.MovieAPI.MovieInfoService.movieInfo.control.service;

import com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

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
    public ResultModel getMovieInfo(Integer movieId) {
        Map<String, Object> response = webClient
                .get()
                .uri(movieId + apiKey.getKey())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                }).block();
        ResultModel resultModel = new ResultModel();
        resultModel.setMovieId(String.valueOf(movieId));
        assert response != null;//error null
        resultModel.setTitle(String.valueOf(response.get("title")));
        resultModel.setOverview(String.valueOf(response.get("overview")));
        return resultModel;
    }
}
