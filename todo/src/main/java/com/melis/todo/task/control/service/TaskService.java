package com.melis.todo.task.control.service;

import com.melis.todo.task.entity.model.TaskModel;

public interface TaskService {
    public TaskModel addTask(TaskModel task);

    public void deleteTask(TaskModel task);

    public TaskModel setAsFinished(TaskModel task);
}
