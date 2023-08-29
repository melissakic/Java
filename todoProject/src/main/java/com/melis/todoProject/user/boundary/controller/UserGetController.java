package com.melis.todoProject.user.boundary.controller;

import com.melis.todoProject.user.control.service.UserService;
import com.melis.todoProject.user.control.service.UserServiceImp;
import com.melis.todoProject.user.entity.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserGetController {

    private final UserService userService;


    @Autowired
    public UserGetController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistration(Model model) {
        model.addAttribute("user", new UserModel());
        return "register";
    }

    @GetMapping("/user/all")
    public String getAllUsers(Model model) {
        model.addAttribute("usernames", userService.getAllUsernames());
        return "usernames";
    }
}
