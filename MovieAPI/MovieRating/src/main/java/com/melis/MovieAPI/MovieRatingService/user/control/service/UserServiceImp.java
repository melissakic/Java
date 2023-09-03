package com.melis.MovieAPI.MovieRatingService.user.control.service;

import com.melis.MovieAPI.MovieRatingService.user.control.repository.UserRepository;
import com.melis.MovieAPI.MovieRatingService.user.entity.model.UserModel;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void addUser(UserModel userModel) {
        userRepository.save(userModel);
    }

    @Transactional
    @Override
    public UserModel findUserById(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    @Transactional
    @Override
    public Map<String, Double> getRatings(Integer userId) {
        UserModel userModel = userRepository.findByUserId(userId);
        Hibernate.initialize(userModel.getRatings());
        return userModel.getRatings();
    }
}
