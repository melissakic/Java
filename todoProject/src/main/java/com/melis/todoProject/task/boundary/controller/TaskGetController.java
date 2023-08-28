package com.melis.todoProject.task.boundary.controller;

import com.melis.todoProject.exception.customExceptions.TaskNotFoundException;
import com.melis.todoProject.exception.customExceptions.UserNotOwnerException;
import com.melis.todoProject.task.control.service.DeleteFinishedTaskEvent;
import com.melis.todoProject.task.control.service.TaskService;
import com.melis.todoProject.task.control.service.TaskServiceImp;
import com.melis.todoProject.task.entity.dto.TaskAndToDoListsDTO;
import com.melis.todoProject.task.entity.model.TaskModel;
import com.melis.todoProject.todolist.control.service.ToDoListService;
import com.melis.todoProject.todolist.control.service.ToDoListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TaskGetController {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final TaskService taskService;
    private final ToDoListService toDoListService;

    @Autowired
    public TaskGetController(ApplicationEventPublisher applicationEventPublisher, TaskServiceImp taskService, ToDoListServiceImpl toDoListService) {
        this.taskService = taskService;
        this.applicationEventPublisher = applicationEventPublisher;
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
        if (taskService.checkTaskNotExists(id)) throw new TaskNotFoundException("Task not found");
        if (taskService.checkTaskAuthorisation(authentication.getName(), id))
            throw new UserNotOwnerException("You must be owner to edit task");
        model.addAttribute("task", task);
        return "editTask";
    }

    @GetMapping("task/finish/{id}")
    public String finishTask(@PathVariable(name = "id") Integer id, Authentication authentication) {
        if (taskService.checkTaskNotExists(id)) throw new TaskNotFoundException("Task not found");
        if (taskService.checkTaskAuthorisation(authentication.getName(), id))
            throw new UserNotOwnerException("You must be owner to edit task");
        taskService.setTaskToDone(id);
        DeleteFinishedTaskEvent finishedTaskEvent = new DeleteFinishedTaskEvent(this);
        applicationEventPublisher.publishEvent(finishedTaskEvent);
        return "redirect:/task/unfinished";
    }

    @GetMapping("task/changeStatus/{id}")
    public String changeStatus(@PathVariable(name = "id") Integer id, Authentication authentication) {
        if (taskService.checkTaskNotExists(id)) throw new TaskNotFoundException("Task not found");
        if (taskService.checkTaskAuthorisation(authentication.getName(), id))
            throw new UserNotOwnerException("You must be owner to edit task");
        taskService.changeStatus(id);
        return "redirect:/list/get";
    }

    @GetMapping("/task/unfinished")
    public String unfinishedTasks() {
        return "unfinishedTasks";
    }

}
