package com.melis.todoProject.todolist.boundary.controller;

import com.melis.todoProject.todolist.control.service.ToDoListService;
import com.melis.todoProject.todolist.control.service.ToDoListServiceImpl;
import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ToDoListDeleteController {
    private final ToDoListService toDoListService;

    @Autowired
    public ToDoListDeleteController(ToDoListServiceImpl toDoListService) {
        this.toDoListService = toDoListService;
    }

    @DeleteMapping("list/delete/{id}")
    public String deleteList(@PathVariable(value = "id") Integer id, Authentication authentication) {
        ToDoListModel list = toDoListService.getListById(id);
        if (list == null || toDoListService.checkToDoListAuthorisation(authentication.getName(), id))
            return "redirect:/list/get";
        toDoListService.deleteList(id, authentication.getName());
        return "redirect:/list/get";
    }
}
