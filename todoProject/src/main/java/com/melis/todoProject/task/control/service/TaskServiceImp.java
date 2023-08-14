package com.melis.todoProject.task.control.service;


import com.melis.todoProject.task.control.repository.TaskRepository;
import com.melis.todoProject.task.entity.dto.TaskAndToDoListsDTO;
import com.melis.todoProject.task.entity.model.TaskModel;
import com.melis.todoProject.todolist.control.service.ToDoListService;
import com.melis.todoProject.todolist.control.service.ToDoListServiceImpl;
import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import com.melis.todoProject.user.control.service.UserService;
import com.melis.todoProject.user.control.service.UserServiceImp;
import com.melis.todoProject.user.entity.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;
    private final ToDoListService toDoListService;
    private final UserService userService;

    @Autowired
    public TaskServiceImp(TaskRepository taskRepository, ToDoListServiceImpl toDoListService, UserServiceImp userService) {
        this.taskRepository = taskRepository;
        this.toDoListService = toDoListService;
        this.userService = userService;
    }

    @Transactional
    @Override
    public void addTask(TaskAndToDoListsDTO task) {
        taskRepository.save(task.getTaskModel());
        ToDoListModel model = toDoListService.getListByName(task.getSelectedList());
        model.getTask().add(task.getTaskModel());
    }

    @Transactional
    @Override
    public boolean checkTaskAuthorisation(String username, Integer taskId) {
        UserModel user = userService.getUser(username);
        for (ToDoListModel list : user.getToDoLists()) {
            for (TaskModel task : list.getTask()) {
                if (task.getId().equals(taskId)) return false;
            }
        }
        return true;
    }

    @Transactional
    @Override
    public TaskModel getTaskById(Integer id) {
        Optional<TaskModel> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    @Override
    public void setTaskToDone(Integer taskId) {
        Optional<TaskModel> task = taskRepository.findById(taskId);
        task.ifPresent(taskModel -> taskModel.setDone(true));
        taskRepository.save(task.get());
    }

    @Transactional
    @Override
    public void deleteTask(Integer taskId, String username) {
        UserModel user = userService.getUser(username);
        ToDoListModel list = new ToDoListModel();
        for (ToDoListModel lists : user.getToDoLists()) {
            for (TaskModel task : lists.getTask()) {
                if (task.getId().equals(taskId)) {
                    list = lists;
                    break;
                }
            }
        }
        ToDoListModel fetched = toDoListService.getListByName(list.getListName());
        fetched.getTask().removeIf(item -> item.getId().equals(taskId));
        taskRepository.deleteById(taskId);
    }

    @Override
    public void editTask(TaskModel task) {
        taskRepository.save(task);
    }
}
