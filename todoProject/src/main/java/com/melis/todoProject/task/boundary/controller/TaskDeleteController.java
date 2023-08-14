package com.melis.todoProject.task.boundary.controller;

import com.melis.todoProject.task.control.service.TaskService;
import com.melis.todoProject.task.control.service.TaskServiceImp;
import com.melis.todoProject.task.entity.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TaskDeleteController {
    private final TaskService taskService;

    @Autowired
    public TaskDeleteController(TaskServiceImp taskService) {
        this.taskService = taskService;
    }

    @DeleteMapping("task/delete/{id}")
    public String deleteTask(@PathVariable Integer id, Authentication authentication) {
        TaskModel task = taskService.getTaskById(id);
        if (task == null || taskService.checkTaskAuthorisation(authentication.getName(), id))
            return "redirect:/list/get";
        taskService.deleteTask(id, authentication.getName());
        return "redirect:/list/get";
    }
}
