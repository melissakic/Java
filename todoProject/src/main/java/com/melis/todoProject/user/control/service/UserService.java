package com.melis.todoProject.user.control.service;


import com.melis.todoProject.user.entity.dto.SimpleUserModel;
import com.melis.todoProject.user.entity.model.UserModel;

import java.util.List;

public interface UserService {
    void registerUser(UserModel user);

    UserModel getUser(String username);

    List<SimpleUserModel> getAllUsernames();

    void saveUser(UserModel user);
}
