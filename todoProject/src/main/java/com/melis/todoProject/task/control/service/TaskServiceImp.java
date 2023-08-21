package com.melis.todoProject.task.control.service;


import com.fasterxml.jackson.databind.ObjectMapper;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class TaskServiceImp implements TaskService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private final SimpUserRegistry userRegistry;
    private final TaskRepository taskRepository;
    private final ToDoListService toDoListService;
    private final UserService userService;


    @Autowired
    public TaskServiceImp(SimpUserRegistry userRegistry, TaskRepository taskRepository, ToDoListServiceImpl toDoListService, UserServiceImp userService) {
        this.taskRepository = taskRepository;
        this.toDoListService = toDoListService;
        this.userService = userService;
        this.userRegistry = userRegistry;
    }

    @Override
    public void addTask(TaskAndToDoListsDTO task) {
        taskRepository.save(task.getTaskModel());
        ToDoListModel model = toDoListService.getListByName(task.getSelectedList());
        model.getTask().add(task.getTaskModel());
    }

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


    @Override
    @Scheduled(fixedDelay = 2000)
    public void scheduleDeletionFinishedTasks() {
        if (userRegistry != null) {
            List<String> users = userRegistry.getUsers().stream()
                    .map(SimpUser::getName)
                    .toList();
            if (users.size() > 0) {
                UserModel user = userService.getUser(users.get(0));
                UserModel userModel = userService.getUser(user.getUsername());
                List<TaskModel> tasks = new ArrayList<TaskModel>();
                for (ToDoListModel lists : userModel.getToDoLists()) {
                    for (TaskModel task : lists.getTask()) {
                        if (!task.isDone()) tasks.add(task);
                    }
                }
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String jsonString = objectMapper.writeValueAsString(tasks);
                    messagingTemplate.convertAndSend("/list/message", jsonString);
                } catch (Exception e) {
                    log.info("Exception with JSON stringify");
                }
            }
        }
    }
}
