package com.melis.todoProject.user.boundary.controller;

import com.melis.todoProject.user.entity.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserGetController {

    @GetMapping("/register")
    public String showRegistration(Model model) {
        model.addAttribute("user", new UserModel());
        return "register";
    }
}
