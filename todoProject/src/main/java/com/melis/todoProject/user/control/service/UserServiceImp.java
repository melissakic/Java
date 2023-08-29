package com.melis.todoProject.user.control.service;


import com.melis.todoProject.user.control.repository.UserRepository;
import com.melis.todoProject.user.entity.dto.SimpleUserModel;
import com.melis.todoProject.user.entity.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserModel getUser(String username) {
        return userRepository.findByUsernameNative(username);
    }

    @Override
    public void saveUser(UserModel user) {
        userRepository.save(user);
    }

    @Override
    public List<SimpleUserModel> getAllUsernames() {
        return userRepository.findAllUsernames();
    }
}
