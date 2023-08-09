package com.melis.todoProject.task.control.service;


import com.melis.todoProject.task.entity.model.TaskModel;

public interface TaskService {
    public TaskModel addTask(TaskModel task);

    public void deleteTask(TaskModel task);

    public TaskModel setAsFinished(TaskModel task);
}
