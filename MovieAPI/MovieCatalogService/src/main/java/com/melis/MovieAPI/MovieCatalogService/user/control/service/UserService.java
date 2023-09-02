package com.melis.MovieAPI.MovieCatalogService.user.control.service;

import com.melis.MovieAPI.MovieCatalogService.user.entity.model.UserModel;

import java.util.Map;

public interface UserService {
    Map<String, Double> getRatings(Integer userId);

    void addUser(UserModel userModel);
}
