package com.melis.MovieAPI.MovieInfoService.movieInfo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Bean {
    @org.springframework.context.annotation.Bean(name = "movieAPIClient")
    public WebClient movieAPIClient() {
        return WebClient.builder().baseUrl("https://api.themoviedb.org/3/movie/").build();
    }
}
