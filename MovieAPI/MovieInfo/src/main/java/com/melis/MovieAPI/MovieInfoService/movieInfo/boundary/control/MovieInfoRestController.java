package com.melis.MovieAPI.MovieInfoService.movieInfo.boundary.control;

import com.melis.MovieAPI.MovieInfoService.movieInfo.control.service.MovieInfoService;
import com.melis.MovieAPI.MovieInfoService.movieInfo.control.service.MovieInfoServiceImp;
import com.melis.MovieAPI.MovieInfoService.movieInfo.entity.model.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieInfoRestController {

    private final MovieInfoService movieInfoService;

    @Autowired
    public MovieInfoRestController(MovieInfoServiceImp movieInfoService) {
        this.movieInfoService = movieInfoService;
    }

    @GetMapping("/info")
    public ResultModel getMovieInfo(@RequestParam Integer movieId) {
        return movieInfoService.getMovieInfo(movieId);
    }
}
