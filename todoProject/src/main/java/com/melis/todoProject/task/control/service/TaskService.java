package com.melis.todoProject.task.control.service;


import com.melis.todoProject.task.entity.dto.TaskAndToDoListsDTO;

import java.sql.Timestamp;

public interface TaskService {
    public void addTask(TaskAndToDoListsDTO task);

    public Timestamp convertStringToTimestamp(String time);
}
