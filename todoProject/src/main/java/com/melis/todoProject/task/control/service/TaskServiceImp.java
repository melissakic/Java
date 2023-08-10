package com.melis.todoProject.task.control.service;


import com.melis.todoProject.task.control.repository.TaskRepository;
import com.melis.todoProject.task.entity.dto.TaskAndToDoListsDTO;
import com.melis.todoProject.todolist.control.service.ToDoListService;
import com.melis.todoProject.todolist.control.service.ToDoListServiceImpl;
import com.melis.todoProject.todolist.entity.model.ToDoListModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;
    private final ToDoListService toDoListService;

    @Autowired
    public TaskServiceImp(TaskRepository taskRepository, ToDoListServiceImpl toDoListService) {
        this.taskRepository = taskRepository;
        this.toDoListService = toDoListService;
    }

    @Transactional
    @Override
    public void addTask(TaskAndToDoListsDTO task) {
        taskRepository.save(task.getTaskModel());
        ToDoListModel model = toDoListService.getListByName(task.getSelectedList());
        model.getTask().add(task.getTaskModel());
    }

    @Override
    public Timestamp convertStringToTimestamp(String time) {
        time = time + " 00:00:00.0";
        Timestamp timestamp = Timestamp.valueOf(time);
        return timestamp;
    }
}
