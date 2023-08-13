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
import org.springframework.web.bind.annotation.*;

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
    public String addTaskForm(Model model, Authentication authentication) {
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

    @GetMapping("/task/edit/{id}")
    public String editTaskForm(@PathVariable(name = "id") Integer id, Authentication authentication, Model model) {
        TaskModel task = taskService.getTaskById(id);
        if (task == null || taskService.checkIfUserCanAccess(authentication.getName(), id))
            return "redirect:/list/get";
        model.addAttribute("task", task);
        return "editTask";
    }

    @PostMapping("/task/edit/{id}")
    public String editTask(@ModelAttribute TaskModel taskModel, @PathVariable(name = "id") Integer id, Authentication authentication) {
        TaskModel task = taskService.getTaskById(id);
        if (task == null || taskService.checkIfUserCanAccess(authentication.getName(), id) || !taskModel.getId().equals(task.getId()))
            return "redirect:/list/get";
        taskService.editTask(taskModel);
        return "redirect:/list/get";
    }
//    @PostMapping("/task/finish/{id}")
//    public String setTaskToDone(@PathVariable(name = "id") Integer id, Authentication authentication) {
//        TaskModel task = taskService.getTaskById(id);
//        if (task == null || taskService.checkIfUserCanAcces(authentication.getName(), id))
//            return "redirect:/list/get";
//        taskService.setTaskToDone(id);
//        return "redirect:/list/get";
//    }

    @GetMapping("task/finish/{id}")
    public String addTask(@PathVariable(name = "id") Integer id, Authentication authentication) {
        TaskModel task = taskService.getTaskById(id);
        if (task == null || taskService.checkIfUserCanAccess(authentication.getName(), id))
            return "redirect:/list/get";
        taskService.setTaskToDone(id);
        return "redirect:/list/get";
    }

    @DeleteMapping("task/delete/{id}")
    public String deleteTask(@PathVariable Integer id, Authentication authentication) {
        TaskModel task = taskService.getTaskById(id);
        if (task == null || taskService.checkIfUserCanAccess(authentication.getName(), id))
            return "redirect:/list/get";
        taskService.deleteTask(id, authentication.getName());
        return "redirect:/list/get";
    }

}
