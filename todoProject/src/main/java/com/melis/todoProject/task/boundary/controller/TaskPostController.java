package com.melis.todoProject.task.boundary.controller;

import com.melis.todoProject.task.control.service.StringToTimestampParser;
import com.melis.todoProject.task.control.service.TaskService;
import com.melis.todoProject.task.control.service.TaskServiceImp;
import com.melis.todoProject.task.entity.dto.TaskAndToDoListsDTO;
import com.melis.todoProject.task.entity.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskPostController {
    private final TaskService taskService;

    @Autowired
    public TaskPostController(TaskServiceImp taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task/add")
    public String addTask(@ModelAttribute TaskAndToDoListsDTO task) {
        task.getTaskModel().setTaskEndTime(StringToTimestampParser.convertStringToTimestamp(task.getSelectedEndTime()));
        taskService.addTask(task);
        return "redirect:/list/get";
    }

    @PostMapping("/task/edit/{id}")
    public String editTask(@ModelAttribute TaskModel taskModel, @PathVariable(name = "id") Integer id, Authentication authentication) {
        if (taskService.taskIsNotValid(authentication.getName(), id))
            return "redirect:/task/unfinished";
        taskService.editTask(taskModel);
        return "redirect:/task/unfinished";
    }
}
