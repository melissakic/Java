package com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultModel implements Serializable {
    private String userId;
    private Double rating;
    private String movieId;
    private String title;
    private String overview;
}
