package com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service;

import com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model.MovieResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class MovieCatalogServiceImp implements MovieCatalogService {

    private final WebClient ratingAPIClient;
    private final WebClient infoAPIClient;


    @Autowired
    public MovieCatalogServiceImp(@Qualifier("ratingAPIClient") WebClient ratingAPIClient, @Qualifier("infoAPIClient") WebClient infoAPIClient) {
        this.infoAPIClient = infoAPIClient;
        this.ratingAPIClient = ratingAPIClient;
    }

    private Mono<Map<String, Double>> getRatingsFromUser(Integer userId) {
        return ratingAPIClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/ratings").queryParam("userId", userId).build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    private Mono<MovieResultModel> getInfoFromUser(Integer movieId) {
        return infoAPIClient.get()
                .uri(uriBuilder -> uriBuilder.path("/info").queryParam("movieId", movieId).build())
                .retrieve()
                .bodyToMono(MovieResultModel.class);
    }


    @Override
    public ArrayList<MovieResultModel> getInfo(Integer userId) {
        Map<String, Double> ratings = getRatingsFromUser(userId).block();
        ArrayList<MovieResultModel> results = new ArrayList<>();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        if (ratings != null) {
            ratings.forEach((movieId, rating) -> {
                futures.add(CompletableFuture.supplyAsync(() ->
                                getInfoFromUser(Integer.valueOf(movieId)).block())
                        .thenAccept(movieResultModel -> {
                            movieResultModel.setRating(rating);
                            results.add(movieResultModel);
                        })
                );
            });
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );
        allOf.join();
        return results;
    }
}
