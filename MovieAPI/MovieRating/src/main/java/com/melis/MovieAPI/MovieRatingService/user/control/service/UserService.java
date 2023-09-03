package com.melis.MovieAPI.MovieRatingService.user.control.service;

import com.melis.MovieAPI.MovieRatingService.user.entity.model.UserModel;

public interface UserService {

    void addUser(UserModel userModel);

    UserModel findUserById(Integer userId);

}
