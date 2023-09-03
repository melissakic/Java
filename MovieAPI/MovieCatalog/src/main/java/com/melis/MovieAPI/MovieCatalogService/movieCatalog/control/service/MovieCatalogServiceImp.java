package com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service;

import com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Map;

@Service
public class MovieCatalogServiceImp implements MovieCatalogService {

    private final WebClient webClient;

    @Autowired
    public MovieCatalogServiceImp(@Qualifier("localAPIClient") WebClient webClient) {
        this.webClient = webClient;
    }

    private Mono<Map<String, Double>> getRatingsFromUser(Integer userId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/ratings").queryParam("userId", userId).build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    private Mono<ResultModel> getInfoFromUser(Integer movieId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/info").queryParam("movieId", movieId).build())
                .retrieve()
                .bodyToMono(ResultModel.class);
    }

    @Override
    public ArrayList<ResultModel> getMovieInfo(Integer userId) {
        Map<String, Double> ratings = getRatingsFromUser(userId).block();
        ArrayList<ResultModel> results = new ArrayList<>();
        if (ratings != null) {
            ratings.forEach((movieId, rating) -> {
                ResultModel resultModel = getInfoFromUser(Integer.valueOf(movieId)).block();
                if (resultModel != null) {
                    resultModel.setRating(rating);
                }
                results.add(resultModel);
            });
        }
        return results;
    }
}
