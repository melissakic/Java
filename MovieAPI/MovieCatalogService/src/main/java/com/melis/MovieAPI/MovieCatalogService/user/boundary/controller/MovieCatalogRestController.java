package com.melis.MovieAPI.MovieCatalogService.user.boundary.controller;

import com.melis.MovieAPI.MovieCatalogService.user.control.service.UserService;
import com.melis.MovieAPI.MovieCatalogService.user.control.service.UserServiceImp;
import com.melis.MovieAPI.MovieCatalogService.user.entity.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@RestController
public class MovieCatalogRestController {

    private final WebClient webClient;

    private final UserService userService;

    @Autowired
    public MovieCatalogRestController(WebClient webClient, UserServiceImp userServiceImp) {
        this.webClient = webClient;
        this.userService = userServiceImp;
    }

    @GetMapping("/movie")
    public void getMovie(@RequestBody Integer userId) {
        Map<String, Double> ratings = userService.getRatings(userId);
    }

    @PostMapping("/user")
    public void addUser(@RequestBody UserModel userModel) {
        userService.addUser(userModel);
    }
}
