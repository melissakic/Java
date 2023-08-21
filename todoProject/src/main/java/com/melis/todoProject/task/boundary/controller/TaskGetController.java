package com.melis.todoProject.task.boundary.controller;

import com.melis.todoProject.task.control.service.TaskService;
import com.melis.todoProject.task.control.service.TaskServiceImp;
import com.melis.todoProject.task.entity.dto.TaskAndToDoListsDTO;
import com.melis.todoProject.task.entity.model.TaskModel;
import com.melis.todoProject.todolist.control.service.ToDoListService;
import com.melis.todoProject.todolist.control.service.ToDoListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TaskGetController {
    private final TaskService taskService;
    private final ToDoListService toDoListService;

    @Autowired
    public TaskGetController(TaskServiceImp taskService, ToDoListServiceImpl toDoListService) {
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


    @GetMapping("/task/edit/{id}")
    public String editTaskForm(@PathVariable(name = "id") Integer id, Authentication authentication, Model model) {
        TaskModel task = taskService.getTaskById(id);
        if (task == null || taskService.checkTaskAuthorisation(authentication.getName(), id))
            return "redirect:/task/unfinished";
        model.addAttribute("task", task);
        return "editTask";
    }

    @GetMapping("task/finish/{id}")
    public String finishTask(@PathVariable(name = "id") Integer id, Authentication authentication) {
        TaskModel task = taskService.getTaskById(id);
        if (task == null || taskService.checkTaskAuthorisation(authentication.getName(), id))
            return "redirect:/task/unfinished";
        taskService.setTaskToDone(id);
        return "redirect:/task/unfinished";
    }

    @GetMapping("task/changeStatus/{id}")
    public String changeStatus(@PathVariable(name = "id") Integer id, Authentication authentication) {
        TaskModel task = taskService.getTaskById(id);
        if (task != null || !taskService.checkTaskAuthorisation(authentication.getName(), id)) {
            taskService.changeStatus(id);
        }
        return "redirect:/list/get";
    }

    @GetMapping("/task/unfinished")
    public String unfinishedTasks() {
        return "unfinishedTasks";
    }

}
