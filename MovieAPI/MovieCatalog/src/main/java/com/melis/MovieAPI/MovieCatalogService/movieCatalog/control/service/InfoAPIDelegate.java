package com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service;

import org.melis.api.InfoApi;
import org.melis.model.MovieResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class InfoAPIDelegate implements InfoApi {

    private final WebClient infoAPIClient;

    @Autowired
    public InfoAPIDelegate(@Qualifier("infoAPIClient") WebClient infoAPIClient) {
        this.infoAPIClient = infoAPIClient;
    }

    @Override
    public ResponseEntity<MovieResultModel> getMovieInfo(Integer movieId) {
        return ResponseEntity.ok(infoAPIClient.get()
                .uri(uriBuilder -> uriBuilder.path("/info").queryParam("movieId", movieId).build())
                .retrieve()
                .bodyToMono(MovieResultModel.class).block());
    }
}
