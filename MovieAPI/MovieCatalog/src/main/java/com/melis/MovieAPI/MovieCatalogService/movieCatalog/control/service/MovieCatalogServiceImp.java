package com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service;

import org.melis.api.InfoApi;
import org.melis.model.MovieResultModel;
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
    private final InfoApi infoApi;

    @Autowired
    public MovieCatalogServiceImp(@Qualifier("ratingAPIClient") WebClient ratingAPIClient, InfoAPIDelegate infoApi) {
        this.infoApi = infoApi;
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

    private MovieResultModel getInfoFromUser(Integer movieId) {
        return infoApi.getMovieInfo(movieId).getBody();
    }

    @Override
    public ArrayList<MovieResultModel> getInfo(Integer userId) {
        Map<String, Double> ratings = getRatingsFromUser(userId).block();
        ArrayList<MovieResultModel> results = new ArrayList<>();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        if (ratings != null) {
            ratings.forEach((movieId, rating) -> {
                futures.add(CompletableFuture.supplyAsync(() ->
                                getInfoFromUser(Integer.valueOf(movieId)))
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
