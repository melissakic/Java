package com.melis.MovieAPI.MovieCatalogService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Beans {

    @Bean(name = "ratingAPIClient")
    public WebClient ratingAPIClient() {
        return WebClient.builder().baseUrl("http://localhost:8082").build();
    }

    @Bean(name = "infoAPIClient")
    public WebClient infoAPIClient() {
        return WebClient.builder().baseUrl("http://localhost:8083").build();
    }

}
