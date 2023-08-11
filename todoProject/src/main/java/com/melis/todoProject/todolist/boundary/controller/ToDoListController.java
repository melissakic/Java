package com.melis.todoProject.todolist.boundary.controller;

import com.melis.todoProject.todolist.control.service.ToDoListService;
import com.melis.todoProject.todolist.control.service.ToDoListServiceImpl;
import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ToDoListController {

    private final ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListServiceImpl toDoListService) {
        this.toDoListService = toDoListService;
    }

    @GetMapping("list/add")
    public String formNewList(Model model) {
        model.addAttribute("todoList", new ToDoListModel());
        return "addList";
    }

    @PostMapping("/list/add")
    public String addNewList(@ModelAttribute ToDoListModel toDoList, Authentication authentication) {
        toDoListService.addNewListToLoggedUser(toDoList, authentication.getName());
        return "redirect:/list/add";
    }

    
    @GetMapping("list/get")
    public String getAllLists(Authentication authentication, Model model) {
        List<ToDoListModel> lists = toDoListService.getListFromUser(authentication.getName());
        model.addAttribute("lists", lists);
        return "getLists";
    }
}
