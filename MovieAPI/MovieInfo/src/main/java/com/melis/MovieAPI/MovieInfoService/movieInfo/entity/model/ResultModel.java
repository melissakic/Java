package com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model;

import lombok.Data;

@Data
public class ResultModel {
    private String userId;
    private String movieId;
    private String title;
    private String overview;
}
