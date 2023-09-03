package com.melis.MovieAPI.MovieCatalogService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Beans {
    @Bean
    public WebClient localApiClient() {
        return WebClient.create("https://api.themoviedb.org/");
    }

}
