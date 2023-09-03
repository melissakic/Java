package com.melis.MovieAPI.MovieRatingService.movieRating.control.service;

import com.melis.MovieAPI.MovieRatingService.user.control.service.UserService;
import com.melis.MovieAPI.MovieRatingService.user.control.service.UserServiceImp;
import com.melis.MovieAPI.MovieRatingService.user.entity.model.UserModel;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MovieRatingServiceImp implements MovieRatingService {

    private final UserService userService;

    @Autowired
    public MovieRatingServiceImp(UserServiceImp userService) {
        this.userService = userService;
    }

    @Transactional
    @Override
    public Map<String, Double> getRatings(Integer userId) {
        UserModel userModel = userService.findUserById(userId);
        Hibernate.initialize(userModel.getRatings());
        return userModel.getRatings();
    }
}
