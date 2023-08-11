package com.melis.todoProject.task.boundary.controller;

import com.melis.todoProject.task.control.service.TaskService;
import com.melis.todoProject.task.control.service.TaskServiceImp;
import com.melis.todoProject.task.entity.dto.TaskAndToDoListsDTO;
import com.melis.todoProject.task.entity.model.TaskModel;
import com.melis.todoProject.todolist.control.service.ToDoListService;
import com.melis.todoProject.todolist.control.service.ToDoListServiceImpl;
import com.melis.todoProject.user.control.service.UserService;
import com.melis.todoProject.user.control.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    private final ToDoListService toDoListService;

    @Autowired
    public TaskController(TaskServiceImp taskService, UserServiceImp userService, ToDoListServiceImpl toDoListService) {
        this.userService = userService;
        this.taskService = taskService;
        this.toDoListService = toDoListService;
    }

    @GetMapping("/task/add")
    public String formAddTask(Model model, Authentication authentication) {
        TaskAndToDoListsDTO taskAndToDoLists = new TaskAndToDoListsDTO();
        taskAndToDoLists.setLists(toDoListService.getListNamesFromUser(authentication.getName()));
        taskAndToDoLists.setTaskModel(new TaskModel());
        model.addAttribute("task", taskAndToDoLists);
        return "addTask";
    }

    @PostMapping("/task/add")
    public String addTask(@ModelAttribute TaskAndToDoListsDTO task) {
        task.getTaskModel().setTaskEndTime(taskService.convertStringToTimestamp(task.getSelectedEndTime()));
        taskService.addTask(task);
        return "redirect:/list/get";
    }

}
