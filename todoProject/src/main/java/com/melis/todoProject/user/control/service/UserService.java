package com.melis.todoProject.user.control.service;


import com.melis.todoProject.user.entity.model.UserModel;

public interface UserService {
    void registerUser(UserModel user);

    UserModel getUser(String username);

    void saveUser(UserModel user);
}
