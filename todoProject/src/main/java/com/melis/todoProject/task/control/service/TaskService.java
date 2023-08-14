package com.melis.todoProject.task.control.service;


import com.melis.todoProject.task.entity.dto.TaskAndToDoListsDTO;
import com.melis.todoProject.task.entity.model.TaskModel;

public interface TaskService {
    public void addTask(TaskAndToDoListsDTO task);

    public TaskModel getTaskById(Integer id);

    public void setTaskToDone(Integer taskId);

    public void deleteTask(Integer taskId, String username);

    public boolean checkTaskAuthorisation(String username, Integer taskId);

    public void editTask(TaskModel task);
}
