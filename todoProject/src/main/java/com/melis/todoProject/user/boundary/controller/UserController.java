package com.melis.todoProject.user.boundary.controller;

import com.melis.todoProject.user.control.service.UserService;
import com.melis.todoProject.user.control.service.UserServiceImp;
import com.melis.todoProject.user.entity.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistration(Model model) {
        model.addAttribute("user", new UserModel());
        return "register";
    }

    @PostMapping("/register")
    public String registerUserAccount(
            @ModelAttribute UserModel user) {
        userService.registerUser(user);
        return "redirect:/" + "login";
    }
}
