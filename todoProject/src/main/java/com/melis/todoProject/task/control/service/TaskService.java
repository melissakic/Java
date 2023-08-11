package com.melis.todoProject.task.control.service;


import com.melis.todoProject.task.entity.dto.TaskAndToDoListsDTO;
import com.melis.todoProject.task.entity.model.TaskModel;

import java.sql.Timestamp;

public interface TaskService {
    public void addTask(TaskAndToDoListsDTO task);

    public Timestamp convertStringToTimestamp(String time);

    public TaskModel getTaskById(Integer id);

    public void setTaskToDone(Integer taskId);

    public void deleteTask(Integer taskId, String username);

    public boolean checkIfUserCanAcces(String username, Integer taskId);
}
