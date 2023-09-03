package com.melis.MovieAPI.MovieRatingService.user.control.service;

import com.melis.MovieAPI.MovieRatingService.user.control.repository.UserRepository;
import com.melis.MovieAPI.MovieRatingService.user.entity.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
}
