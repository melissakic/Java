package com.melis.todoProject.todolist.boundary.controller;

import com.melis.todoProject.todolist.control.service.ToDoListService;
import com.melis.todoProject.todolist.control.service.ToDoListServiceImpl;
import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ToDoListGetController {
    private final ToDoListService toDoListService;

    @Autowired
    public ToDoListGetController(ToDoListServiceImpl toDoListService) {
        this.toDoListService = toDoListService;
    }

    @GetMapping("list/get")
    public String getAllLists(Authentication authentication, Model model) {
        List<ToDoListModel> lists = toDoListService.getListFromUser(authentication.getName());
        model.addAttribute("lists", lists);
        return "getLists";
    }

    @GetMapping("list/get/{id}")
    public String getListById(@PathVariable(value = "id") Integer id, Model model, Authentication authentication) {
        ToDoListModel list = toDoListService.getListById(id);
        if (list == null || toDoListService.checkToDoListAuthorisation(authentication.getName(), list.getId()))
            return "redirect:/list/get";
        model.addAttribute("list", list.getTask());
        return "getOneList";
    }

    @GetMapping("list/add")
    public String formNewList(Model model, Authentication authentication) {
        model.addAttribute("fetchedLists", toDoListService.getSimpleListsFromUser(authentication.getName()));
        model.addAttribute("todoList", new ToDoListModel());
        return "addList";
    }
}
