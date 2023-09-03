package com.melis.MovieAPI.MovieCatalogService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Beans {
    @Bean(name = "movieAPIClient")
    public WebClient movieAPIClient() {
        return WebClient.builder().baseUrl("https://api.themoviedb.org/3/movie/").build();
    }

    @Bean(name = "localAPIClient")
    public WebClient localAPIClient() {
        return WebClient.builder().baseUrl("http://localhost:8081").build();
    }

}
