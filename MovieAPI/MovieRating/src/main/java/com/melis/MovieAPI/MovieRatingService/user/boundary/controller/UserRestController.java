package com.melis.MovieAPI.MovieRatingService.user.boundary.controller;

import com.melis.MovieAPI.MovieRatingService.user.control.service.UserService;
import com.melis.MovieAPI.MovieRatingService.user.control.service.UserServiceImp;
import com.melis.MovieAPI.MovieRatingService.user.entity.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
