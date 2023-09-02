package com.melis.MovieAPI.MovieCatalogService.user.control.service;

import com.melis.MovieAPI.MovieCatalogService.user.control.repository.UserRepository;
import com.melis.MovieAPI.MovieCatalogService.user.entity.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Map<String, Double> getRatings(Integer userId) {
        Optional<UserModel> userModel = userRepository.findById(userId);
        return userModel.map(UserModel::getRatings).orElse(null);
    }

    @Override
    public void addUser(UserModel userModel) {
        userRepository.save(userModel);
    }
}
