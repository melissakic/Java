package com.melis.todoProject.user.boundary.controller;

import com.melis.todoProject.user.control.service.UserService;
import com.melis.todoProject.user.control.service.UserServiceImp;
import com.melis.todoProject.user.entity.model.UserModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(names = "user")
@Controller
public class UserPostController {
    private final UserService userService;

    @Autowired
    public UserPostController(UserServiceImp userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUserAccount(
            @ModelAttribute @Valid UserModel user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.registerUser(user);
        return "redirect:/login";
    }
}
