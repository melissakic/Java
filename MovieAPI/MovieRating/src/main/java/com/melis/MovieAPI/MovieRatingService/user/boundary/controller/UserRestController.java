package com.melis.MovieAPI.MovieRatingService.user.boundary.controller;

import com.melis.MovieAPI.MovieRatingService.user.control.service.UserService;
import com.melis.MovieAPI.MovieRatingService.user.control.service.UserServiceImp;
import com.melis.MovieAPI.MovieRatingService.user.entity.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserServiceImp userServiceImp) {
        this.userService = userServiceImp;
    }

    @PostMapping("/user")
    public void addUser(@RequestBody UserModel userModel) {
        userService.addUser(userModel);
    }

    @GetMapping("/ratings")
    public Map<String, Double> getRatings(@RequestBody Integer userId) {
        Map<String, Double> ratings = userService.getRatings(userId);
        return ratings;
    }
}
