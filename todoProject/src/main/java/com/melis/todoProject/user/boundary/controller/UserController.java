package com.melis.todoProject.user.boundary.controller;

import com.melis.todoProject.user.control.service.UserService;
import com.melis.todoProject.user.control.service.UserServiceImp;
import com.melis.todoProject.user.entity.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        UserModel user = new UserModel(username, password);
        userService.registerUser(user);
        return "Success";
    }
}
