package com.melis.MovieAPI.MovieCatalogService.movieCatalog.control.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class MovieCatalogServiceImp implements MovieCatalogService {

    private final WebClient webClient;

    @Autowired
    public MovieCatalogServiceImp(@Qualifier("localAPIClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Map<String, Double> getRatingsFromUser(Integer userId) {
        Mono<Map<String, Double>> testResultMono = webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/ratings").queryParam("userId", userId).build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Double>>() {
                });
        return testResultMono.block();
    }
}
