package com.melis.todoProject.task.boundary.controller;

import com.melis.todoProject.task.control.service.TaskService;
import com.melis.todoProject.task.control.service.TaskServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskServiceImp taskService) {
        this.taskService = taskService;
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "hello";
    }
}
